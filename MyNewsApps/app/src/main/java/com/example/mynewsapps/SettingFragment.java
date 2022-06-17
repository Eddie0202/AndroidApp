package com.example.mynewsapps;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynewsapps.Adapter.AdminAdapter;
import com.example.mynewsapps.Adapter.HomeFragmentAdapter;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SettingFragment extends Fragment {

    TextView tv_for_greeting;
    RecyclerView recyclerView1;
    AdminAdapter adapter;
    String ip = (String) new IpConfig().getIpv4();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View settingView = inflater.inflate(R.layout.fragment_setting, container, false);

        tv_for_greeting = settingView.findViewById(R.id.tv_greeting_admin);


        Bundle bundle = getArguments();
        String username = bundle.getString("username");
        String role = checkrole(username);
        if (role.equals("admin")){
            tv_for_greeting.setText("Welcome back "+ username);
        }

        recyclerView1 = settingView.findViewById(R.id.recyclerView_for_admin);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new AdminAdapter(getActivity());
        recyclerView1.setAdapter(adapter);


        return settingView;
    }

    public String checkrole(String username){
        String str = username;
        String check = "null";


                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "username";
                field[1] = "password";
                //Creating array for data
                String[] data = new String[2];
                data[0] = "username";
                data[1] = "password";
                PutData putData = new PutData(ip+"/login-signup/checkRole.php?username="+str, "GET", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        check = result;
                    }
                }
                //End Write and Read data with URL

        return check;
    }
}