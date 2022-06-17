package com.example.mynewsapps.Adapter;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;


import com.example.mynewsapps.EditUsersActivity;
import com.example.mynewsapps.IpConfig;
import com.example.mynewsapps.Model.UserInfo;
import com.example.mynewsapps.R;

import java.util.List;

public class EditUserAdapter extends RecyclerView.Adapter<EditUserAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<UserInfo> userInfoList;
    String ip = (String) new IpConfig().getIpv4();

    public EditUserAdapter(Context mCtx, List<UserInfo> userInfoList) {
        this.mCtx = mCtx;
        this.userInfoList = userInfoList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.row_edit_users, null);

        final ProductViewHolder myAdapter = new ProductViewHolder(view);

        myAdapter.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = (String) myAdapter.tvID.getText();
                String url = ip+"/login-signup/getUsers.php?id="+check;
                Toast.makeText(parent.getContext(), check,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, EditUsersActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key_1",url);
                intent.putExtras(bundle);
                mCtx.startActivity(intent);

            }
        });

//        myAdapter.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String check = (String) myAdapter.textViewID.getText();
//                String url = "http://192.168.1.4:8080/login-signup/newsDetail.php?id="+check;
//                Toast.makeText(parent.getContext(), check,
//                        Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mCtx, DetailOfProduct.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("key_1",url);
//                intent.putExtras(bundle);
//                mCtx.startActivity(intent);
//            }
//        });

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        UserInfo userInfo = userInfoList.get(position);


        holder.tvID.setText(String.valueOf(userInfo.getId()));
        holder.tvFullname.setText(userInfo.getFullname());
        holder.tvUsername.setText(userInfo.getUsername());
        holder.tvEmail.setText(userInfo.getEmail());
        holder.tvPassword.setText(userInfo.getPassword());
        holder.tvRole.setText(userInfo.getRole());
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tvID, tvFullname, tvUsername, tvEmail, tvPassword, tvRole;
        Button btn_edit;

        public ProductViewHolder(View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tv_id_users);
            tvFullname = itemView.findViewById(R.id.tv_full_name_users);
            tvUsername = itemView.findViewById(R.id.tv_username_users);
            tvEmail = itemView.findViewById(R.id.tv_email_users);
            tvPassword = itemView.findViewById(R.id.tv_password_users);
            tvRole = itemView.findViewById(R.id.tv_role_users);
            btn_edit = itemView.findViewById(R.id.btn_edit_users);
        }
    }
}
