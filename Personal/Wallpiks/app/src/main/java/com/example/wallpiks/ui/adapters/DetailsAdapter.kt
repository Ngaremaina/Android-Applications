package com.example.wallpiks.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpiks.R
import com.example.wallpiks.ui.models.ImageModel

class DetailsAdapter(private val context: Context, private val detailList:ArrayList<ImageModel>):RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView=itemView.findViewById(R.id.imageDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_layout, parent, false))
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        Glide.with(context).load(detailList[position].imageUrl).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return detailList.size
    }

}