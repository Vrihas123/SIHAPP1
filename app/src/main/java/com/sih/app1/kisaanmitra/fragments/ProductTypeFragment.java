package com.sih.app1.kisaanmitra.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.activities.MainActivity;
import com.sih.app1.kisaanmitra.model.ProductData;
import com.sih.app1.kisaanmitra.model.ProductListResponse;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;
import com.sih.app1.kisaanmitra.utils.ProgressDialog;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductTypeFragment extends Fragment {


    private CardView cardSeeds, cardManure, cardVehicle, cardEquipment, cardOther;
    private ImageView seedImg, manureImg, vehicleImg, equipmentImg, otherImg;
    private AVLoadingIndicatorView seedI, manureI, vehicleI, equipmentI, othersI;
    private ProgressDialog progressDialog;
    private List<ProductData> dataList = new ArrayList<>();
    private List<ProductData> filterDataList = new ArrayList<>();
    private int i;

    public ProductTypeFragment() {
        // Required empty public constructor
    }

    public static ProductTypeFragment newInstance(String param1, String param2) {
        ProductTypeFragment fragment = new ProductTypeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_product_type, container, false);
        progressDialog = new ProgressDialog();
        cardSeeds = view.findViewById(R.id.card_seeds);
        cardManure = view.findViewById(R.id.card_manure);
        cardVehicle = view.findViewById(R.id.card_vehicles);
        cardEquipment = view.findViewById(R.id.card_equipments);
        cardOther = view.findViewById(R.id.card_others);

        seedImg = view.findViewById(R.id.seed_img);
        manureImg = view.findViewById(R.id.manure_img);
        vehicleImg = view.findViewById(R.id.vehicles_img);
        equipmentImg = view.findViewById(R.id.equipments_img);
        otherImg = view.findViewById(R.id.others_img);

        seedI = view.findViewById(R.id.seed_loader);
        manureI = view.findViewById(R.id.manure_loader);
        vehicleI = view.findViewById(R.id.vehicle_loader);
        equipmentI = view.findViewById(R.id.equipment_loader);
        othersI = view.findViewById(R.id.others_loader);

        Glide.with(getContext()).load("https://cdn.pixabay.com/photo/2016/03/23/15/15/flax-seed-1274944_960_720.jpg").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                seedI.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                seedI.setVisibility(View.GONE);
                return false;
            }
        }).into(seedImg);

        Glide.with(getContext()).load("https://cdn.pixabay.com/photo/2016/04/02/11/17/gullefa-1302596_960_720.jpg").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                manureI.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                manureI.setVisibility(View.GONE);
                return false;
            }
        }).into(manureImg);

        Glide.with(getContext()).load("https://cdn.pixabay.com/photo/2014/07/06/17/20/tractor-385681_960_720.jpg").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                vehicleI.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                vehicleI.setVisibility(View.GONE);
                return false;
            }
        }).into(vehicleImg);

        Glide.with(getContext()).load("https://cdn.pixabay.com/photo/2016/09/30/19/26/red-tractor-1706144_960_720.jpg").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                equipmentI.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                equipmentI.setVisibility(View.GONE);
                return false;
            }
        }).into(equipmentImg);

        Glide.with(getContext()).load("https://cdn.pixabay.com/photo/2017/09/16/12/33/haymaking-2755356_960_720.jpg").listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                othersI.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                othersI.setVisibility(View.GONE);
                return false;
            }
        }).into(otherImg);

        APICall();

        cardSeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i=0; i<dataList.size(); i++) {
                    if (dataList.get(i).getProduct_type().equals("SEED")) {
                        filterDataList.add(dataList.get(i));
                    }
                }
                ProductFragment productFragment = ProductFragment.newInstance("Seeds");
                productFragment.setProductDataList(filterDataList);
                ((MainActivity)getActivity()).createFragment(productFragment, "ProductFragment", true);
            }


        });
        cardManure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i=0; i<dataList.size(); i++) {
                    if (dataList.get(i).getProduct_type().equals("MANURE")) {
                        filterDataList.add(dataList.get(i));
                    }
                }

                ProductFragment productFragment = ProductFragment.newInstance("Manures");
                productFragment.setProductDataList(filterDataList);
                ((MainActivity)getActivity()).createFragment(productFragment, "ProductFragment", true);
            }


        });
        cardVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i=0; i<dataList.size(); i++) {
                    if (dataList.get(i).getProduct_type().equals("VEHICLE")) {
                        filterDataList.add(dataList.get(i));
                    }
                }

                ProductFragment productFragment = ProductFragment.newInstance("Vehicles");
                productFragment.setProductDataList(filterDataList);
                ((MainActivity)getActivity()).createFragment(productFragment, "ProductFragment", true);
            }


        });
        cardEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i=0; i<dataList.size(); i++) {
                    if (dataList.get(i).getProduct_type().equals("EQUIPMENT")) {
                        filterDataList.add(dataList.get(i));
                    }
                }

                ProductFragment productFragment = ProductFragment.newInstance("Equipments");
                productFragment.setProductDataList(filterDataList);
                ((MainActivity)getActivity()).createFragment(productFragment, "ProductFragment", true);
            }


        });
        cardOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (i=0; i<dataList.size(); i++) {
                    if (dataList.get(i).getProduct_type().equals("OTHER")) {
                        filterDataList.add(dataList.get(i));
                    }
                }

                ProductFragment productFragment = ProductFragment.newInstance("Miscellaneous");
                productFragment.setProductDataList(filterDataList);
                ((MainActivity)getActivity()).createFragment(productFragment, "ProductFragment", true);
            }


        });
        return view;
    }

    private void APICall(){
        progressDialog.showDialog("Loading the products...", getContext());
        ApiServices services = AppClient.getInstance().createServiceWithAuth(ApiServices.class, getContext());
        Call<ProductListResponse> call = services.getProductList();
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                progressDialog.hideDialog();
                if (response.isSuccessful()) {
                    ProductListResponse productListResponse = response.body();
                    if (productListResponse != null){
                        if (productListResponse.getSuccess()) {
                            dataList.clear();
                            dataList.addAll(productListResponse.getProductDataList());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                progressDialog.hideDialog();
            }
        });
    }

}
