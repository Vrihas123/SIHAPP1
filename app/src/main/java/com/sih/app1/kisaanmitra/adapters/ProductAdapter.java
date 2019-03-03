package com.sih.app1.kisaanmitra.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.activities.MainActivity;
import com.sih.app1.kisaanmitra.fragments.ProductDetailFragment;
import com.sih.app1.kisaanmitra.model.ProductData;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<ProductData> productDataList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public ProductAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setProductDataList(List<ProductData> productDataList) {
        this.productDataList = productDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.product_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
        final ProductData data = productDataList.get(i);
        holder.productName.setText(data.getName());
        holder.productQuantity.setText(String.valueOf(data.getQuantity()) +" " +data.getQuantity_type());
        holder.sellPrice.setText(String.valueOf(data.getSell_price())+ " RS");
        if (data.getSell_type().equals("RENT")){
            holder.rentPrice.setText(String.valueOf(data.getRent_price())+" RS");
        }else {
            holder.rentPrice.setVisibility(View.GONE);
        }
        Glide.with(context).load(data.getProduct_image()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.indicatorView.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.indicatorView.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.productImage);

        holder.cardProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailFragment productDetailFragment = ProductDetailFragment.newInstance();
                productDetailFragment.setProductData(data);
                ((MainActivity)context).createFragment(productDetailFragment, "ProductDetailFragment",true);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("size====", productDataList.size()+"");
        return productDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardProduct;
        ImageView productImage;
        TextView sellPrice, rentPrice, productName, productQuantity;
        AVLoadingIndicatorView indicatorView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardProduct = itemView.findViewById(R.id.card_product);
            productImage = itemView.findViewById(R.id.product_img);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productName = itemView.findViewById(R.id.product_name);
            sellPrice = itemView.findViewById(R.id.sell_price);
            rentPrice = itemView.findViewById(R.id.rent_price);
            indicatorView = itemView.findViewById(R.id.product_loader);
        }
    }
}
