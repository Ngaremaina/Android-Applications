package com.example.wallpiks.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpiks.databinding.FragmentHomeBinding
import com.example.wallpiks.ui.adapters.ImageAdapter
import com.example.wallpiks.ui.models.ImageModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    lateinit var imageModelList: ArrayList<ImageModel>
    lateinit var db: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db= FirebaseFirestore.getInstance()

        images()
    }

    private fun images() {
        imageModelList= arrayListOf()
        db.collection("images").addSnapshotListener(EventListener { value, error ->
            if (error!=null){
                Log.e("Firestore error", error.message!!)
                return@EventListener
            }
            else{
                for (dc:DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        val imageModel:ImageModel=dc.document.toObject(ImageModel::class.java)
                        imageModelList.add(imageModel)
                    }
                    binding.imagesRecyclerView.apply {
                        adapter=ImageAdapter(context, imageModelList)
                        layoutManager=GridLayoutManager(context, 2)
                        setHasFixedSize(true)
                    }
                }
            }
        })


    }


}