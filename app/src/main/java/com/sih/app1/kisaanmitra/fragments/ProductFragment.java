package com.sih.app1.kisaanmitra.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.adapters.ProductAdapter;
import com.sih.app1.kisaanmitra.model.ProductData;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private String title;
    private List<ProductData> productDataList = new ArrayList<>();
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private TextView txtHeader;

    public ProductFragment() {
        // Required empty public constructor
    }



    public void setProductDataList(List<ProductData> productDataList) {
        this.productDataList = productDataList;
    }

    public static ProductFragment newInstance(String param1) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString("string1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            title = getArguments().getString("string1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        recyclerViewProducts = view.findViewById(R.id.recycler_product);
        txtHeader = view.findViewById(R.id.product_heading);
        txtHeader.setText(title);
        productAdapter = new ProductAdapter(getContext());
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProducts.setAdapter(productAdapter);
        if (productDataList != null){
            productAdapter.setProductDataList(productDataList);
            productAdapter.notifyDataSetChanged();
        }
        return  view;
    }

}
