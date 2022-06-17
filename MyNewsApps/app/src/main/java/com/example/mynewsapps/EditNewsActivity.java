package com.example.mynewsapps;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynewsapps.Model.Product;
import com.example.mynewsapps.Model.UserInfo;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class EditNewsActivity extends AppCompatActivity {

    List<Product> productList;

    EditText edtTitle, edtShortdesc, edtauthor, edtImg, edtDate;
    Button btnEditNews;
    TextView tvIdHolder;
    String ip = (String) new IpConfig().getIpv4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_news);


        Bundle bundle = getIntent().getExtras();
        String url = getBundleString(bundle,"key_1", "default");

        edtTitle = findViewById(R.id.edt_title_news);
        edtShortdesc = findViewById(R.id.edt_shortdesc_news);
        edtauthor = findViewById(R.id.edt_author_news);
        edtImg = findViewById(R.id.edt_img_news);
        edtDate = findViewById(R.id.edt_date_news);
        btnEditNews = findViewById(R.id.btn_edit_news_activity);
        tvIdHolder = findViewById(R.id.tv_edit_news_id_holder);

        btnEditNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = (String) tvIdHolder.getText();
                String tt = String.valueOf(edtTitle.getText());
                String sd = String.valueOf(edtShortdesc.getText());
                String at = String.valueOf(edtauthor.getText());
                String il = String.valueOf(edtImg.getText());
                String dt = String.valueOf(edtDate.getText());
                String urlEditUsers = ip+"/login-signup/editUsers.php?id="+id+"&title="+tt+
                        "&shortdesc="+sd+"&author="+at+"&image="+il+"&date="+dt;
                doPHPCode(urlEditUsers);


            }
        });



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
                        tvIdHolder.setText(String.valueOf(userInfo.getInt("id")) );
                        edtTitle.setText(userInfo.getString("title"));
                        edtShortdesc.setText(userInfo.getString("shortdesc"));
                        edtauthor.setText(userInfo.getString("author"));
                        edtImg.setText(userInfo.getString("image"));
                        edtDate.setText(userInfo.getString("date"));


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

    public String getBundleString(Bundle b, String key, String def)
    {
        String value = b.getString(key);
        if (value == null)
            value = def;
        return value;
    }

    public void doPHPCode(String url1) {

        //Starting Write and Read data with URL
        //Creating array for parameters
        String[] field = new String[2];
        field[0] = "username";
        field[1] = "password";
        //Creating array for data
        String[] data = new String[2];
        data[0] = "username";
        data[1] = "password";
        PutData putData = new PutData(url1, "GET", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }


    }

}