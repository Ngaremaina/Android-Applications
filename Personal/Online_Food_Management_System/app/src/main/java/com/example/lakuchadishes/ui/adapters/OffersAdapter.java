package com.example.lakuchadishes.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lakuchadishes.R;
import com.example.lakuchadishes.ui.models.OffersModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder> {
    Context context;
    List<OffersModel> offersModelList;

    public OffersAdapter(Context context, List<OffersModel> offersModelList) {
        this.context = context;
        this.offersModelList = offersModelList;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfferViewHolder(LayoutInflater.from(context).inflate(R.layout.offer_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        Glide.with(context).load(offersModelList.get(position).getImageUrl()).into(holder.imageView);
        holder.textName.setText(offersModelList.get(position).getName());
        holder.textPrice.setText(String.valueOf(offersModelList.get(position).getPrice()));
        holder.textDiscount.setText(String.valueOf(offersModelList.get(position).getDiscount()));

    }

    @Override
    public int getItemCount() {
        return offersModelList.size();
    }

    static class OfferViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView imageView;
        MaterialTextView textName, textPrice, textDiscount;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.offerImageView);
            textName=itemView.findViewById(R.id.textOfferProductName);
            textPrice=itemView.findViewById(R.id.textOfferPrice);
            textDiscount=itemView.findViewById(R.id.textOfferDiscount);
        }
    }
}
