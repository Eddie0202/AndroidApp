package com.example.mynewsapps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUp extends AppCompatActivity {

    EditText EditText_fullname, EditText_email, EditText_username, EditText_pass;
    Button btn_signup;
    TextView textView_LogIn;
    String ip = (String) new IpConfig().getIpv4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText_fullname = findViewById(R.id.sign_up_fullname);
        EditText_email = findViewById(R.id.sign_up_email);
        EditText_username = findViewById(R.id.sign_up_username);
        EditText_pass = findViewById(R.id.sign_up_pass);

        btn_signup = findViewById(R.id.sign_up_btn);
        textView_LogIn = findViewById(R.id.sign_in_text);

        textView_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname, email, password, username;
                fullname = String.valueOf(EditText_fullname.getText());
                email = String.valueOf(EditText_email.getText());
                password = String.valueOf(EditText_pass.getText());
                username = String.valueOf(EditText_username.getText());

                if(!fullname.equals("") && !email.equals("") && !password.equals("") && !username.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData(ip+"/login-signup/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Intent intent = new Intent(getApplicationContext(), LogIn.class);
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