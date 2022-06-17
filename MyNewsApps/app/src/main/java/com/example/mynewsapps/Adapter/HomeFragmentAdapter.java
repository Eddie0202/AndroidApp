package com.example.mynewsapps.Adapter;
import android.content.Context;
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

import com.example.mynewsapps.IpConfig;
import com.example.mynewsapps.NewsFragment;
import com.example.mynewsapps.R;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyAdapter> {
    Context context;
    String ip = (String) new IpConfig().getIpv4();
    String cUser = "Hello User";


    public HomeFragmentAdapter(Context context) {
        this.context = context;
    }

    public HomeFragmentAdapter(Context context, String cUser) {
        this.context = context;
        this.cUser = cUser;
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
//                Toast.makeText(parent.getContext(), check,
//                        Toast.LENGTH_LONG).show();
                switch (check) {
                    case "New News Today":
                        String urlForNews= ip+"/login-signup/getProducts.php";
                        Toast.makeText(parent.getContext(), check,
                                Toast.LENGTH_SHORT).show();
                         activity = (AppCompatActivity)view.getContext();
                         newsFragment = new NewsFragment();
                        b.putString("url", (String) urlForNews);
                        b.putString("username2", (String) cUser);
                        newsFragment.setArguments(b);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,newsFragment).commit();
                        break;
                    case "Game Review":
                        String urlForNewsRV= ip+"/login-signup/getProductsByRV.php";
                        Toast.makeText(parent.getContext(), check,
                                Toast.LENGTH_SHORT).show();
                         activity = (AppCompatActivity)view.getContext();
                         newsFragment = new NewsFragment();
                        b.putString("url", (String) urlForNewsRV);
                        newsFragment.setArguments(b);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,newsFragment).commit();
                        break;
                    case "Top Game To Play":
                        String urlForNewsTop= ip+"/login-signup/getProductsByRV.php";
                        Toast.makeText(parent.getContext(), check,
                                Toast.LENGTH_SHORT).show();
                        activity = (AppCompatActivity)view.getContext();
                        newsFragment = new NewsFragment();
                        b.putString("url", (String) urlForNewsTop);
                        newsFragment.setArguments(b);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,newsFragment).commit();
                        break;
                    case "Random Stuff":
                        String urlForNewsRan= ip+"/login-signup/getProductsByRV.php";
                        Toast.makeText(parent.getContext(), check,
                                Toast.LENGTH_SHORT).show();
                        activity = (AppCompatActivity)view.getContext();
                        newsFragment = new NewsFragment();
                        b.putString("url", (String) urlForNewsRan);
                        newsFragment.setArguments(b);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,newsFragment).commit();
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
            holder.image.setImageResource(R.drawable.newspaper);
            holder.image1.setImageResource(R.drawable.ic_baseline_person_24);
            holder.back.setBackgroundColor(Color.parseColor("#E6E53935"));
            holder.text.setText("New News Today");
        }
        if(position==1)
        {
            holder.image.setImageResource(R.drawable.newspaper);
            holder.image1.setImageResource(R.drawable.ic_baseline_person_24);
            holder.back.setBackgroundColor(Color.parseColor("#F236883A"));
            holder.text.setText("Game Review");
        }
        if(position==2){
            holder.image.setImageResource(R.drawable.newspaper);
            holder.image1.setImageResource(R.drawable.ic_baseline_person_24);
            holder.back.setBackgroundColor(Color.parseColor("#F2AF4576"));
            holder.text.setText("Top Game To Play");
        }
        if(position==3)
        {
            holder.image.setImageResource(R.drawable.newspaper);
            holder.image1.setImageResource(R.drawable.localeventorg);
            holder.back.setBackgroundColor(Color.parseColor("#F2EEAA45"));
            holder.text.setText("Random Stuff");
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
