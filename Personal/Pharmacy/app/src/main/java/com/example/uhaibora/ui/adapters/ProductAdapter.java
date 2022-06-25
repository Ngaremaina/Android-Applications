package com.example.uhaibora.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uhaibora.R;
import com.example.uhaibora.ui.activities.DetailsActivity;
import com.example.uhaibora.ui.models.ProductModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<ProductModel> productModelList;

    public ProductAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.product_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Glide.with(context).load(productModelList.get(position).getImageUrl()).into(holder.imageView);
        holder.textName.setText(productModelList.get(position).getName());
        holder.textPrice.setText(String.valueOf(productModelList.get(position).getPrice()));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);

                intent.putExtra("image", productModelList.get(position).getImageUrl());
                intent.putExtra("name", productModelList.get(position).getName());
                intent.putExtra("price", productModelList.get(position).getPrice());
                intent.putExtra("rating", productModelList.get(position).getRating());
                intent.putExtra("description", productModelList.get(position).getDescription());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView imageView;
        MaterialTextView textName, textPrice;
        ConstraintLayout constraintLayout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.productImageView);
            textPrice=itemView.findViewById(R.id.textProductPrice);
            textName=itemView.findViewById(R.id.textProductName);
            constraintLayout=itemView.findViewById(R.id.constProductLayout);
        }
    }
}

