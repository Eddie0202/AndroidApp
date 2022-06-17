package com.example.mynewsapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mynewsapps.Adapter.ProductsAdapter;
import com.example.mynewsapps.Model.Product;
import com.google.android.material.internal.ContextUtils;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {

    EditText EditText_username, EditText_pass;
    Button btn_LogIn;
    TextView textView_SignUp;

    String ip = (String) new IpConfig().getIpv4();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText_username = findViewById(R.id.login_username);
        EditText_pass = findViewById(R.id.login_pass);

        btn_LogIn = findViewById(R.id.sign_in_btn);
        textView_SignUp = findViewById(R.id.sign_in_text);


        textView_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        btn_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password, username;
                password = String.valueOf(EditText_pass.getText());
                username = String.valueOf(EditText_username.getText());

                if(!password.equals("") && !username.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData(ip+"/login-signup/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(), "Login Success",
                                                Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("username", data[0]);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });

                }
                else{
                    Toast.makeText(getApplicationContext(), "Cần nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}