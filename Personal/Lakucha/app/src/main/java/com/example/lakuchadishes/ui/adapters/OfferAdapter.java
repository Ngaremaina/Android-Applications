package com.example.lakuchadishes.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lakuchadishes.R;
import com.example.lakuchadishes.ui.models.OfferModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    Context context;
    List<OfferModel> offerModelList;

    public OfferAdapter(Context context, List<OfferModel> offerModelList) {
        this.context = context;
        this.offerModelList = offerModelList;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfferViewHolder(LayoutInflater.from(context).inflate(R.layout.offer_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        Glide.with(context).load(offerModelList.get(position).getImageUrl()).into(holder.imageView);
        holder.textName.setText(offerModelList.get(position).getName());
        holder.textPrice.setText(String.valueOf(offerModelList.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return offerModelList.size();
    }

    static class OfferViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textName, textPrice;
        CardView cardView;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.offerImageView);
            textName=itemView.findViewById(R.id.textOfferProductName);
            textPrice=itemView.findViewById(R.id.textOfferPrice);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
