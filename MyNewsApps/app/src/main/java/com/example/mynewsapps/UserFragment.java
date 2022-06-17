package com.example.mynewsapps;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynewsapps.Adapter.ProductsAdapter;
import com.example.mynewsapps.Model.Product;
import com.example.mynewsapps.Model.UserInfo;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class UserFragment extends Fragment {

    List<UserInfo> userInfoList;

    TextView tv_full_name;
    TextView tv_user_name;
    TextView tv_password;
    TextView tv_email;
    TextView tv_role;
    TextView tv_id_holder;
    Button btn_log_out,btn_edit_users;

    String ip = (String) new IpConfig().getIpv4();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        Bundle bundle = getArguments();
        String username = bundle.getString("username");
        tv_user_name = rootView.findViewById(R.id.tv_user_name);
        tv_full_name = rootView.findViewById(R.id.tv_full_name);
        tv_email = rootView.findViewById(R.id.tv_email);
        tv_password = rootView.findViewById(R.id.tv_password);
        tv_role = rootView.findViewById(R.id.tv_role_profile_user);
        tv_id_holder = rootView.findViewById(R.id.tv_idholder);
        btn_log_out = rootView.findViewById(R.id.btn_log_out);
        btn_edit_users = rootView.findViewById(R.id.btn_edit_users_profile);

        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LogIn.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btn_edit_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = String.valueOf(tv_id_holder.getText());
                String url = ip+"/login-signup/getUsers.php?id="+check;
                Intent intent1 = new Intent(getActivity(), EditUsersActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key_1",url);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
//        tv_user_name.setText(username);

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        String url = ip+"/login-signup/getProfile.php?username="+username;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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
                        tv_id_holder.setText(String.valueOf(userInfo.getInt("id")));
                        tv_full_name.setText(userInfo.getString("fullname"));
                        tv_user_name.setText(userInfo.getString("username"));
                        tv_password.setText(userInfo.getString("password"));
                        tv_email.setText(userInfo.getString("email"));
                        tv_role.setText(userInfo.getString("role"));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //adding our stringrequest to queue
//        Volley.newRequestQueue(this).add(stringRequest);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(stringRequest);
        //this is a cmt




        return rootView;
    }



}