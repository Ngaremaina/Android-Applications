package com.example.wallpiks.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpiks.R
import com.example.wallpiks.databinding.FragmentCategoryBinding
import com.example.wallpiks.ui.adapters.ImageAdapter
import com.example.wallpiks.ui.models.ImageModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore

class CategoryFragment : Fragment() {
    lateinit var binding:FragmentCategoryBinding
    lateinit var plantsList: ArrayList<ImageModel>
    lateinit var animalsList: ArrayList<ImageModel>
    lateinit var natureList: ArrayList<ImageModel>
    lateinit var landscapesList: ArrayList<ImageModel>
    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db= FirebaseFirestore.getInstance()
        nature()
        plants()
        animals()
        landscapes()

    }
    private fun nature() {
        natureList= arrayListOf()
        db.collection("nature").addSnapshotListener(EventListener { value, error ->
            if (error!=null){
                Log.e("Firestore error", error.message!!)
                return@EventListener
            }
            else{
                for (dc: DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        val imageModel: ImageModel =dc.document.toObject(ImageModel::class.java)
                        natureList.add(imageModel)
                    }
                    binding.natureRecyclerView.apply {
                        adapter= ImageAdapter(context, natureList)
                        layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        setHasFixedSize(true)
                    }
                }
            }
        })


    }
    private fun animals() {
        animalsList= arrayListOf()
        db.collection("animals").addSnapshotListener(EventListener { value, error ->
            if (error!=null){
                Log.e("Firestore error", error.message!!)
                return@EventListener
            }
            else{
                for (dc:DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        val imageModel:ImageModel=dc.document.toObject(ImageModel::class.java)
                        animalsList.add(imageModel)
                    }
                    binding.animalsRecyclerView.apply {
                        adapter=ImageAdapter(context, animalsList)
                        layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        setHasFixedSize(true)
                    }
                }
            }
        })


    }
    private fun plants() {
        plantsList= arrayListOf()
        db.collection("plants").addSnapshotListener(EventListener { value, error ->
            if (error!=null){
                Log.e("Firestore error", error.message!!)
                return@EventListener
            }
            else{
                for (dc:DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        val imageModel:ImageModel=dc.document.toObject(ImageModel::class.java)
                        plantsList.add(imageModel)
                    }
                    binding.plantsRecyclerView.apply {
                        adapter=ImageAdapter(context, plantsList)
                        layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        setHasFixedSize(true)
                    }
                }
            }
        })


    }
    private fun landscapes() {
        landscapesList= arrayListOf()
        db.collection("landscapes").addSnapshotListener(EventListener { value, error ->
            if (error!=null){
                Log.e("Firestore error", error.message!!)
                return@EventListener
            }
            else{
                for (dc:DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        val imageModel:ImageModel=dc.document.toObject(ImageModel::class.java)
                        landscapesList.add(imageModel)
                    }
                    binding.landscapeRecyclerView.apply {
                        adapter=ImageAdapter(context, landscapesList)
                        layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        setHasFixedSize(true)
                    }
                }
            }
        })


    }

}
