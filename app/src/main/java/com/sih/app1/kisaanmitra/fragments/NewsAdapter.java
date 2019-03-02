package com.sih.app1.kisaanmitra.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.model.News.ArticlesResponse;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<ArticlesResponse> articlesResponses;
    private Context context;

    public NewsAdapter(List<ArticlesResponse> articlesResponses, Context context) {
        this.articlesResponses = articlesResponses;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ArticlesResponse articlesResponse = articlesResponses.get(i);
        viewHolder.tvNewsTitle.setText(articlesResponse.getTitle());
        viewHolder.tvNewsDesc.setText(articlesResponse.getDescription());
        if(null != articlesResponse.getUrlToImage())
            Glide.with(context).load(articlesResponse.getUrlToImage()).into(viewHolder.ivNews);
        viewHolder.tvNewsDate.setText(articlesResponse.getPublishedAt().substring(0, 10));
        viewHolder.cvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsiteUrl(articlesResponse.getUrl());
            }
        });
    }

    private void openWebsiteUrl(String url) {
        if (context != null) {
            Uri uri = Uri.parse(url);
            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
            intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            intentBuilder.setStartAnimations(context, R.anim.slide_up, R.anim.slide_down);
            intentBuilder.setExitAnimations(context, android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);
            CustomTabsIntent customTabsIntent = intentBuilder.build();
            customTabsIntent.launchUrl(context, uri);
        }
    }

    @Override
    public int getItemCount() {
        return articlesResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNewsTitle, tvNewsDesc, tvNewsDate;
        public ImageView ivNews;
        public CardView cvParent;
//        public AVLoadingIndicatorView loadingIndicatorView;


        public ViewHolder(View itemView) {
            super(itemView);
            ivNews = itemView.findViewById(R.id.ivNews);
            tvNewsTitle = itemView.findViewById(R.id.tvNewsTitle);
            tvNewsDesc = itemView.findViewById(R.id.tvNewsDesc);
            tvNewsDate = itemView.findViewById(R.id.tvNewsDate);
            cvParent = itemView.findViewById(R.id.cvParent);
//            loadingIndicatorView = (AVLoadingIndicatorView) itemView.findViewById(R.id.indicator_progress_bar);

        }
    }
}
