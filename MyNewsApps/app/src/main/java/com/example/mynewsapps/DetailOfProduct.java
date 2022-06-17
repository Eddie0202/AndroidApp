package com.example.mynewsapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynewsapps.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DetailOfProduct extends AppCompatActivity {

    List<Product> productList;

    TextView tv_title_detail;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_product);

        tv_title_detail = findViewById(R.id.title_detail);
        webView = findViewById(R.id.webView);

        Bundle bundle = getIntent().getExtras();;
        String url = bundle.getString("key_1");


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
                        tv_title_detail.setText(userInfo.getString("title"));
                        String unencodedHtml = "<style>img{display: inline;height: auto;max-width: 100%;}</style>"+ userInfo.getString("content");
                        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                                Base64.NO_PADDING);
//                        webView.getSettings().setLoadWithOverviewMode(true);
//                        webView.getSettings().setUseWideViewPort(true);
                        webView.loadData(encodedHtml, "text/html", "base64");

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
        RequestQueue queue = Volley.newRequestQueue(this.getApplicationContext());
        queue.add(stringRequest);




    }

}