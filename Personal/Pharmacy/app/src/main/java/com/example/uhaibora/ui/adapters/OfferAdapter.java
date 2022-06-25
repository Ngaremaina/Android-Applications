package com.example.uhaibora.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uhaibora.R;
import com.example.uhaibora.ui.models.OfferModel;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder>{
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

    }

    @Override
    public int getItemCount() {
        return offerModelList.size();
    }

    static class OfferViewHolder extends RecyclerView.ViewHolder{

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
