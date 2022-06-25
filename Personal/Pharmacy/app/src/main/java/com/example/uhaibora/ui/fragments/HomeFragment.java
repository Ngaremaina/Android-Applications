package com.example.uhaibora.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uhaibora.databinding.FragmentHomeBinding;
import com.example.uhaibora.ui.adapters.ImageAdapter;
import com.example.uhaibora.ui.adapters.OfferAdapter;
import com.example.uhaibora.ui.adapters.ProductAdapter;
import com.example.uhaibora.ui.models.ImageModel;
import com.example.uhaibora.ui.models.OfferModel;
import com.example.uhaibora.ui.models.ProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    List<ImageModel> imageModelList;
    List<ProductModel> productList;
    List<OfferModel> offerList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sliderImages();
        popular();
        offers();
    }
    private void sliderImages() {
        imageModelList=new ArrayList<>();
        imageModelList.add(new ImageModel(1,"","Delivery"));
        imageModelList.add(new ImageModel(2,"","Doctor"));
        imageModelList.add(new ImageModel(3,"","Assistant"));

        ImageAdapter imageAdapter=new ImageAdapter(getContext(), imageModelList);
        binding.viewPager.setAdapter(imageAdapter);

        Timer sliderImage=new Timer();
        sliderImage.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                HomeFragment.this.requireActivity().runOnUiThread(() -> {
                    if (binding.viewPager.getCurrentItem()<imageModelList.size()-1){
                        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
                    }
                    else{
                        binding.viewPager.setCurrentItem(0);
                    }
                });

            }
        }, 2200,6000);
    }

    private void popular () {
        productList=new ArrayList<>();
        final String BASE_URL = "http://192.168.100.53/pharmacy/popular.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                response -> {

                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);

                            String image = object.getString("image");
                            String name = object.getString("name");
                            int price = object.getInt("price");
                            ProductModel productModel=new ProductModel(image,name, price);
                            productList.add(productModel);
                        }

                    }catch (Exception e){
                        Log.d("Database error", e.getMessage());

                    }
                    LinearLayoutManager popularLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.popularRecyclerView.setLayoutManager(popularLayoutManager);
                    binding.popularRecyclerView.setAdapter(new ProductAdapter(getContext(), productList));
                    binding.popularRecyclerView.setHasFixedSize(true);

                    /*productAdapter = new ProductAdapter(getContext(), productList);
                    binding.popularRecyclerView.setAdapter(productAdapter);*/


                }, error -> Toast.makeText(getContext(), error.toString(),Toast.LENGTH_LONG).show());

        Volley.newRequestQueue(requireContext()).add(stringRequest);

    }

    private void offers () {
        offerList=new ArrayList<>();
        final String BASE_URL = "http://192.168.100.53/pharmacy/popular.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL,
                response -> {

                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i<array.length(); i++){

                            JSONObject object = array.getJSONObject(i);

                            String image = object.getString("image");
                            String name = object.getString("name");
                            int price = object.getInt("price");
                            int discount=object.getInt("discount");

                            OfferModel offerModel=new OfferModel(image,name, price, discount);
                            offerList.add(offerModel);
                        }

                    }catch (Exception e){
                        Log.d("Database error", e.getMessage());

                    }
                    LinearLayoutManager offerLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.offerRecyclerView.setLayoutManager(offerLayoutManager);
                    binding.offerRecyclerView.setAdapter(new OfferAdapter(getContext(), offerList));
                    binding.offerRecyclerView.setHasFixedSize(true);

                    /*productAdapter = new ProductAdapter(getContext(), productList);
                    binding.popularRecyclerView.setAdapter(productAdapter);*/


                }, error -> Toast.makeText(getContext(), error.toString(),Toast.LENGTH_LONG).show());

        Volley.newRequestQueue(requireContext()).add(stringRequest);

    }


}