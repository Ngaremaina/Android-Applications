package com.example.lakuchadishes.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lakuchadishes.R;
import com.example.lakuchadishes.databinding.FragmentHomeBinding;
import com.example.lakuchadishes.ui.adapters.ImageAdapter;
import com.example.lakuchadishes.ui.adapters.OffersAdapter;
import com.example.lakuchadishes.ui.adapters.ProductAdapter;
import com.example.lakuchadishes.ui.models.ImageModel;
import com.example.lakuchadishes.ui.models.OffersModel;
import com.example.lakuchadishes.ui.models.ProductModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private FirebaseFirestore db;
    private List<ImageModel> imageModelList;
    private List<ProductModel> popularList;
    private List<ProductModel> newProductList;
    private List<OffersModel> offerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db=FirebaseFirestore.getInstance();

        slidingImages();
        popular();
        offers();
        newDishes();
    }

    private void slidingImages() {
        imageModelList=new ArrayList<>();
        imageModelList.add(new ImageModel(1,"https://wallpiks.com/wp-content/uploads/2020/11/panda-768x511.jpg",""));
        imageModelList.add(new ImageModel(2,"https://wallpiks.com/wp-content/uploads/2020/11/panda-768x511.jpg",""));
        imageModelList.add(new ImageModel(3,"https://wallpiks.com/wp-content/uploads/2020/11/panda-768x511.jpg",""));

        ImageAdapter imageAdapter=new ImageAdapter(getContext(), imageModelList);
        binding.viewPager.setAdapter(imageAdapter);

        Timer sliderImage=new Timer();
        sliderImage.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                HomeFragment.this.requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (binding.viewPager.getCurrentItem()<imageModelList.size()-1){
                            binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
                        }
                        else {
                            binding.viewPager.setCurrentItem(0);
                        }

                    }
                });
            }
        }, 2200, 6000);

    }

    private void popular() {
        popularList=new ArrayList<>();
        db.collection("popular").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error", error.getMessage());
                }
                else {
                    assert value != null;
                    for (DocumentChange doc : value.getDocumentChanges()){
                        if (doc.getType().equals(DocumentChange.Type.ADDED)){
                            ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                            popularList.add(productModel);

                        }
                        LinearLayoutManager popularLinearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        binding.popularRecyclerView.setHasFixedSize(true);
                        binding.popularRecyclerView.setLayoutManager(popularLinearLayoutManager);
                        binding.popularRecyclerView.setAdapter(new ProductAdapter(getContext(), popularList));
                    }
                }
            }
        });
    }

    private void offers() {
        offerList=new ArrayList<>();
        db.collection("offers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error", error.getMessage());
                }
                else {
                    assert value != null;
                    for (DocumentChange doc : value.getDocumentChanges()){
                        if (doc.getType().equals(DocumentChange.Type.ADDED)){
                            OffersModel offersModel=doc.getDocument().toObject(OffersModel.class);
                            offerList.add(offersModel);
                        }
                        LinearLayoutManager offerLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        binding.offerRecyclerView.setHasFixedSize(true);
                        binding.offerRecyclerView.setLayoutManager(offerLayoutManager);
                        binding.offerRecyclerView.setAdapter(new OffersAdapter(getContext(), offerList));

                    }
                }
            }
        });
    }

    private void newDishes() {
        newProductList=new ArrayList<>();
        db.collection("new Dishes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.d("Firestore error:",error.getMessage());
                }
                else{
                    assert value != null;
                    for (DocumentChange doc : value.getDocumentChanges()){
                        if (doc.getType().equals(DocumentChange.Type.ADDED)){
                            ProductModel productModel=doc.getDocument().toObject(ProductModel.class);
                            newProductList.add(productModel);
                        }
                        LinearLayoutManager newLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        binding.newProductRecyclerView.setHasFixedSize(true);
                        binding.newProductRecyclerView.setLayoutManager(newLayoutManager);
                        binding.newProductRecyclerView.setAdapter(new ProductAdapter(getContext(), newProductList));
                    }
                }

            }
        });
    }

}