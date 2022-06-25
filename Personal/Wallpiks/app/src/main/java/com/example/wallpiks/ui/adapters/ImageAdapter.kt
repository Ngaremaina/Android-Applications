package com.example.wallpiks.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpiks.R
import com.example.wallpiks.ui.activities.DetailsActivity
import com.example.wallpiks.ui.fragments.HomeFragment
import com.example.wallpiks.ui.models.ImageModel
import com.google.android.material.textview.MaterialTextView

class ImageAdapter(private val context: Context, private val imageModelList: ArrayList<ImageModel>):
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    class ImageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView: AppCompatImageView=itemView.findViewById(R.id.imagesView)
        val constraintLayout:ConstraintLayout=itemView.findViewById(R.id.constImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.image_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context).load(imageModelList[position].imageUrl).into(holder.imageView)
        holder.constraintLayout.setOnClickListener(View.OnClickListener {
            val intent=Intent(context, DetailsActivity::class.java)
            intent.putExtra("image", imageModelList[position].imageUrl)
            context.startActivity(intent)

        })


    }

    override fun getItemCount(): Int {
       return imageModelList.size
    }
}