package com.sih.app1.kisaanmitra.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import com.sih.app1.kisaanmitra.fragments.AdvisoryDetailFragment;
import com.sih.app1.kisaanmitra.model.AdvisoryData;
import com.sih.app1.kisaanmitra.utils.ProgressDialog;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class AdvisoryAdapter extends RecyclerView.Adapter<AdvisoryAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ProgressDialog progressDialog;
    private List<AdvisoryData> advisoryDataList = new ArrayList<>();

    public AdvisoryAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setAdvisoryDataList(List<AdvisoryData> advisoryDataList) {
        this.advisoryDataList = advisoryDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.advisory_item, viewGroup, false);
        progressDialog = new ProgressDialog();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
            final AdvisoryData data = new AdvisoryData();
            holder.txtTitle.setText(data.getTitle());
        Glide.with(context).load(data.getImage_url()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.avLoadingIndicatorView.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.avLoadingIndicatorView.setVisibility(View.GONE);
                return false;
            }
        }).into(holder.imgImage);

        holder.cardViewAdvisory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity) (context);
                FragmentManager fm = activity.getSupportFragmentManager();

                AdvisoryDetailFragment advisoryDetailFragment = AdvisoryDetailFragment.newInstance();
                advisoryDetailFragment.setAdvisoryData(data);
                advisoryDetailFragment.show(fm, "Advisory Details Fragment");
            }
        });
    }

    @Override
    public int getItemCount() {
        return advisoryDataList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {


        CardView cardViewAdvisory;
        TextView txtTitle;
        ImageView imgImage;
        AVLoadingIndicatorView avLoadingIndicatorView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewAdvisory = itemView.findViewById(R.id.card_advisory);
            txtTitle = itemView.findViewById(R.id.advisory_title);
            imgImage = itemView.findViewById(R.id.advisory_image);
            avLoadingIndicatorView = itemView.findViewById(R.id.indicator_advisory_img);
        }
    }
}
