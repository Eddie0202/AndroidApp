package com.example.mynewsapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.vishnusivadas.advanced_httpurlconnection.PutData;



public class AddNewsActivity extends AppCompatActivity {

    EditText edtTitle, edtShortdesc, edtauthor, edtImg, edtDate;
    Button btnEditNews;
    TextView tvIdHolder,tvEditNews;
    String ip = (String) new IpConfig().getIpv4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_news);


        edtTitle = findViewById(R.id.edt_title_news);
        edtShortdesc = findViewById(R.id.edt_shortdesc_news);
        edtauthor = findViewById(R.id.edt_author_news);
        edtImg = findViewById(R.id.edt_img_news);
        edtDate = findViewById(R.id.edt_date_news);
        btnEditNews = findViewById(R.id.btn_edit_news_activity);
        tvIdHolder = findViewById(R.id.tv_edit_news_id_holder);
        tvEditNews = findViewById(R.id.tv_edit_news);
        tvEditNews.setText("Adding News");
        tvIdHolder.setVisibility(View.INVISIBLE);
        edtDate.setVisibility(View.INVISIBLE);

        btnEditNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title, shortdesc, author, image, date;
                title = String.valueOf(edtTitle.getText());
                shortdesc = String.valueOf(edtShortdesc.getText());
                author = String.valueOf(edtauthor.getText());
                image = String.valueOf(edtImg.getText());

                if(!title.equals("") && !shortdesc.equals("") && !author.equals("") && !image.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "title";
                            field[1] = "shortdesc";
                            field[2] = "author";
                            field[3] = "image";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = title;
                            data[1] = shortdesc;
                            data[2] = author;
                            data[3] = image;
                            PutData putData = new PutData(ip+"/login-signup/addFunction.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Add News Success")){
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
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

//    public String getBundleString(Bundle b, String key, String def)
//    {
//        String value = b.getString(key);
//        if (value == null)
//            value = def;
//        return value;
//    }

//    public void doPHPCode(String url1) {
//
//        //Starting Write and Read data with URL
//        //Creating array for parameters
//        String[] field = new String[2];
//        field[0] = "username";
//        field[1] = "password";
//        //Creating array for data
//        String[] data = new String[2];
//        data[0] = "username";
//        data[1] = "password";
//        PutData putData = new PutData(url1, "GET", field, data);
//        if (putData.startPut()) {
//            if (putData.onComplete()) {
//                String result = putData.getResult();
//                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
//            }
//        }
//
//
//    }

}