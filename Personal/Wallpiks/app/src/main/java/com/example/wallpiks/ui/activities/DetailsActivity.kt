package com.example.wallpiks.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.wallpiks.R
import com.example.wallpiks.ui.adapters.DetailsAdapter
import com.example.wallpiks.ui.models.ImageModel

class DetailsActivity : AppCompatActivity() {
    lateinit var detailsAdapter: DetailsAdapter
    lateinit var detailList:ArrayList<ImageModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        detailList= arrayListOf()
        detailsAdapter= DetailsAdapter(this, detailList)

        val intent=intent

        val viewPager: ViewPager2=findViewById(R.id.viewPager)

        viewPager.adapter=detailsAdapter
    }
}