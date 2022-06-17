package com.example.mynewsapps.Adapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsapps.AddNewsActivity;
import com.example.mynewsapps.EditNews;
import com.example.mynewsapps.EditUsers;
import com.example.mynewsapps.EditUsersActivity;
import com.example.mynewsapps.HomeActivity;
import com.example.mynewsapps.IpConfig;
import com.example.mynewsapps.NewsFragment;
import com.example.mynewsapps.R;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyAdapter> {
    Context context;
    String ip = (String) new IpConfig().getIpv4();


    public AdminAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        final MyAdapter myAdapter = new MyAdapter(view);
        myAdapter.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = (String) myAdapter.text.getText();
                Bundle b = new Bundle();
                AppCompatActivity activity;
                NewsFragment newsFragment;
                EditUsers edtUsers;
//                Toast.makeText(parent.getContext(), check,
//                        Toast.LENGTH_LONG).show();
                switch (check) {
                    case "Users Management":
                        String urlForNews= ip+"/login-signup/getUsers.php";
                        Toast.makeText(parent.getContext(), check,
                                Toast.LENGTH_SHORT).show();
                        activity = (AppCompatActivity)view.getContext();
                        edtUsers = new EditUsers();
                        b.putString("url", (String) urlForNews);
                        edtUsers.setArguments(b);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,edtUsers).commit();
                        break;
                    case "News Management":
                        activity = (AppCompatActivity)view.getContext();
                        EditNews editNews = new EditNews();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,editNews).commit();
                        break;
                    case "Add News":
                        Intent intent3 = new Intent(context, AddNewsActivity.class);
                        context.startActivity(intent3);
                        break;
                    case "Analytics":
                        Intent intent4 = new Intent(context, HomeActivity.class);
                        context.startActivity(intent4);
                        break;
                }
            }
        });
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
        if(position==0)
        {
            holder.image.setImageResource(R.drawable.wrenchimg);
            holder.image1.setImageResource(R.drawable.ic_baseline_person_24);
            holder.back.setBackgroundColor(Color.parseColor("#0000FF"));
            holder.text.setText("Users Management");
        }
        if(position==1)
        {
            holder.image.setImageResource(R.drawable.wrenchimg);
            holder.image1.setImageResource(R.drawable.ic_baseline_fiber_new_24);
            holder.back.setBackgroundColor(Color.parseColor("#F236883A"));
            holder.text.setText("News Management");
        }
        if(position==2){
            holder.image.setImageResource(R.drawable.newspaper);
            holder.image1.setImageResource(R.drawable.ic_baseline_note_add_24);
            holder.back.setBackgroundColor(Color.parseColor("#F2EEAA45"));
            holder.text.setText("Add News");
        }
        if(position==3)
        {
            holder.image.setImageResource(R.drawable.newspaper);
            holder.image1.setImageResource(R.drawable.ic_baseline_analytics_24);
            holder.back.setBackgroundColor(Color.parseColor("#F2AF4576"));
            holder.text.setText("Analytics");
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
    public class MyAdapter extends RecyclerView.ViewHolder {
        ImageView image,image1;
        TextView text;
        RelativeLayout back;
        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            image1=itemView.findViewById(R.id.image1);
            text=itemView.findViewById(R.id.text);
            back=itemView.findViewById(R.id.back);
        }
    }

}
