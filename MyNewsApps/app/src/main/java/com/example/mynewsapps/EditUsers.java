package com.example.mynewsapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynewsapps.Adapter.EditUserAdapter;
import com.example.mynewsapps.Adapter.NewsAdapter;
import com.example.mynewsapps.Adapter.ProductsAdapter;
import com.example.mynewsapps.Model.Product;
import com.example.mynewsapps.Model.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class EditUsers extends Fragment {

    List<UserInfo> userInfoList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View editView = inflater.inflate(R.layout.fragment_edit_users, container, false);
        Bundle b = getArguments();
        String url = getBundleString(b, "url", "default");

        //getting the recyclerview from xml
        recyclerView = editView.findViewById(R.id.recyclerView_for_edit_users);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //initializing the productlist
        userInfoList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts(url);



        return editView;
    }

    public String getBundleString(Bundle b, String key, String def)
    {
        String value = b.getString(key);
        if (value == null)
            value = def;
        return value;
    }

    private void loadProducts(String url) {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject userInfo = array.getJSONObject(i);

                                //adding the product to product list
                                userInfoList.add(new UserInfo(
                                        userInfo.getInt("id"),
                                        userInfo.getString("fullname"),
                                        userInfo.getString("username"),
                                        userInfo.getString("password"),
                                        userInfo.getString("email"),
                                        userInfo.getString("role")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            EditUserAdapter adapter = new EditUserAdapter(getActivity(), userInfoList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(stringRequest);
    }
}