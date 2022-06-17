package com.example.mynewsapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynewsapps.DetailOfProduct;
import com.example.mynewsapps.EditNewsActivity;
import com.example.mynewsapps.EditUsersActivity;
import com.example.mynewsapps.HomeActivity;
import com.example.mynewsapps.IpConfig;
import com.example.mynewsapps.Model.Product;
import com.example.mynewsapps.R;

import java.util.List;

public class EditNewsAdapter extends RecyclerView.Adapter<EditNewsAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;
    String ip = (String) new IpConfig().getIpv4();

    public EditNewsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.products_list_item_edit, null);

        final ProductViewHolder myAdapter = new ProductViewHolder(view);
        myAdapter.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = (String) myAdapter.tv_id.getText();
                String url = ip+"/login-signup/getProducts.php?id="+check;
                Toast.makeText(parent.getContext(), check,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, DetailOfProduct.class);
                Bundle bundle = new Bundle();
                bundle.putString("key_1",url);
                intent.putExtras(bundle);
                mCtx.startActivity(intent);
            }
        });

        myAdapter.btn_edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = (String) myAdapter.tv_id.getText();
                String url = ip+"/login-signup/getProducts.php?id="+check;
                Toast.makeText(parent.getContext(), check,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, EditNewsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key_1",url);
                intent.putExtras(bundle);
                mCtx.startActivity(intent);
            }
        });


        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);

        holder.tv_title.setText("Title:"+product.getTitle());
        holder.tv_desc.setText("Desc:"+product.getShortdesc());
        holder.tv_author.setText("Author:"+product.getAuthor());
        holder.tv_date.setText("Date:"+product.getDate());
        holder.tv_id.setText(String.valueOf(product.getId()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_author;
        TextView tv_date;

        ImageView imageView;

        Button btn_edit_info;
        Button btn_edit_content;

        public ProductViewHolder(View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_news_id);
            tv_title = itemView.findViewById(R.id.tv_news_desc);
            tv_desc = itemView.findViewById(R.id.tv_news_desc);
            tv_author = itemView.findViewById(R.id.tv_news_author);
            tv_date = itemView.findViewById(R.id.tv_news_date);
            imageView = itemView.findViewById(R.id.img_news_product);

            btn_edit_info = itemView.findViewById(R.id.btn_edit_news);
            btn_edit_content = itemView.findViewById(R.id.btn_edit_news_content);
        }
    }
}
