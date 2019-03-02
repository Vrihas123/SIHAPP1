package com.sih.app1.kisaanmitra.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.model.ProductData;

public class ProductDetailFragment extends Fragment {

    private ProductData productData;
    private TextView name, quantity, sellprice, rent, period;
    private ImageView productImage;

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    public void setProductData(ProductData productData) {
        this.productData = productData;
    }

    public static ProductDetailFragment newInstance() {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        name = view.findViewById(R.id.product_detail_name);
        quantity = view.findViewById(R.id.product_detail_quanitity);
        sellprice = view.findViewById(R.id.sell_price_detail);
        rent = view.findViewById(R.id.rent_price_detail);
        period = view.findViewById(R.id.period);
        productImage = view.findViewById(R.id.product_detail_img);

        name.setText(productData.getName());
        quantity.setText(String.valueOf(productData.getQuantity()));
        sellprice.setText(String.valueOf(productData.getSell_price()));
        rent.setText(String.valueOf(productData.getRent_price()));
        Glide.with(getContext()).load(productData.getProduct_image()).into(productImage);


        return view;
    }

}
