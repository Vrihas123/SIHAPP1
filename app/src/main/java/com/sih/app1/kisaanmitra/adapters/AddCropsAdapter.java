package com.sih.app1.kisaanmitra.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.fragments.ProfileFragment;
import com.sih.app1.kisaanmitra.model.Crop;
import com.sih.app1.kisaanmitra.model.CropsWrapper;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCropsAdapter extends RecyclerView.Adapter<AddCropsAdapter.ViewHolder>{

    private List<Crop> crops;
    private Context context;

    public AddCropsAdapter(List<Crop> crops, Context context) {
        this.crops = crops;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.addcrops_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Crop crop = crops.get(i);
        viewHolder.tvCrop.setText(crop.getProduct_name());
//        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //api call for deleting a crop
//                addCrop(crop.getProduct_id());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return crops.size();
    }

//    public void addCrop(Integer product_id){
//        ApiServices services = AppClient.getInstance().createServiceWithAuth(ApiServices.class, context);
//        Call<CropsWrapper> call = services.addCrops(product_id);
//        call.enqueue(new Callback<CropsWrapper>() {
//            @Override
//            public void onResponse(Call<CropsWrapper> call, Response<CropsWrapper> response) {
//                if(response.isSuccessful()){
//                    if(null!=response.body()){
//                        if(response.body().getSuccess()){
//                            ProfileFragment.setData(response.body().getCrop_list());
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CropsWrapper> call, Throwable t) {
//
//            }
//        });
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCrop;
        public ImageButton btnAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCrop = itemView.findViewById(R.id.tvCrop);
            btnAdd = itemView.findViewById(R.id.btnAddCrop);
        }
    }
}

