package com.example.mynewsapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynewsapps.DetailOfProduct;
import com.example.mynewsapps.HomeActivity;
import com.example.mynewsapps.IpConfig;
import com.example.mynewsapps.Model.Product;
import com.example.mynewsapps.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;
    String ip = (String) new IpConfig().getIpv4();
    String cUser = "hello world";
    public NewsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    public NewsAdapter(Context mCtx, List<Product> productList, String cUser) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.cUser = cUser;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.products_list_item_layout, null);

        final ProductViewHolder myAdapter = new ProductViewHolder(view);
        myAdapter.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = (String) myAdapter.textViewID.getText();
                String url = ip+"/login-signup/newsDetail.php?id="+check;
                Toast.makeText(parent.getContext(), check,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, DetailOfProduct.class);
                Bundle bundle = new Bundle();
                bundle.putString("key_1",url);
                intent.putExtras(bundle);
                mCtx.startActivity(intent);
            }
        });

        myAdapter.imageViewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check1 = (String) myAdapter.textViewID.getText();
                String url1 = ip+"/login-signup/addFav.php?username="+cUser+"&idmovies="+check1;
                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[2];
                        field[0] = "username";
                        field[1] = "idmovies";
                        //Creating array for data
                        String[] data = new String[2];
                        data[0] = cUser;
                        data[1] = check1;
                        PutData putData = new PutData(ip+"/login-signup/addFav.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                Toast.makeText(parent.getContext(), result, Toast.LENGTH_SHORT).show();
                            }
                        }
                        //End Write and Read data with URL
                    }
                });
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

        holder.textViewTitle.setText(product.getTitle());
        holder.textViewDesc.setText(product.getShortdesc());
        holder.textViewID.setText(String.valueOf(product.getId()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewDesc, textViewID;
        ImageView imageView;
        ImageView imageViewFav;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.title_product);
            textViewDesc = itemView.findViewById(R.id.desc_product);
            imageView = itemView.findViewById(R.id.img_product);
            imageViewFav = itemView.findViewById(R.id.image_fav);
            textViewID = itemView.findViewById(R.id.tv_id_holder);
        }
    }
}
