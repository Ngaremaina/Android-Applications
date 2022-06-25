package com.example.uhaibora.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uhaibora.R;
import com.example.uhaibora.ui.models.ProductModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListViewHolder>{
    Context context;
    List<ProductModel> productModelList;
    int quantity=1;

    public CartListAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartListViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        Glide.with(context).load(productModelList.get(position).getImageUrl()).into(holder.imageView);
        holder.textName.setText(productModelList.get(position).getName());
        holder.textPrice.setText(productModelList.get(position).getPrice());
        
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               quantity=quantity+1;
               holder.textViewNumber.setText(String.valueOf(quantity));
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity>1){
                    quantity=quantity-1;
                }
                holder.textViewNumber.setText(String.valueOf(quantity));
            }
        });


    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    static class CartListViewHolder extends RecyclerView.ViewHolder{
        MaterialTextView textName, textPrice, textViewNumber;
        AppCompatImageView imageView, plus, minus;

        public CartListViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageViewCart);
            textName=itemView.findViewById(R.id.textCartName);
            textPrice=itemView.findViewById(R.id.textItemPrice);
            plus=itemView.findViewById(R.id.btnPlus);
            minus=itemView.findViewById(R.id.btnMinus);
            textViewNumber=itemView.findViewById(R.id.textitemQuantity);
        }
    }
}
