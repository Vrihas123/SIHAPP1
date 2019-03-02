package com.sih.app1.kisaanmitra.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.model.ProductData;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private List<ProductData> productDataList = new ArrayList<>();

    public ProductFragment() {
        // Required empty public constructor
    }

    public void setProductDataList(List<ProductData> productDataList) {
        this.productDataList = productDataList;
    }

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        return  view;
    }

}
