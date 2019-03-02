package com.sih.app1.kisaanmitra.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.model.News.ArticlesResponse;
import com.sih.app1.kisaanmitra.model.News.NewsResponse;

public class NewsFragment extends Fragment {

    private static NewsResponse newsResponse;

    private CardView cvMain;
    private ImageView ivNewsMain;
    private TextView tvNewsTitle, tvNewsDesc;
    private RecyclerView rvNews;
    private NewsAdapter newsAdapter;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(NewsResponse newsResponses) {
        NewsFragment fragment = new NewsFragment();
        newsResponse = newsResponses;
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        cvMain = view.findViewById(R.id.cvMainNews);
        ivNewsMain = view.findViewById(R.id.ivMainNews);
        tvNewsTitle = view.findViewById(R.id.tvNewsTitle);
        tvNewsDesc = view.findViewById(R.id.tvNewsDesc);
        rvNews = view.findViewById(R.id.rvNews);

        final ArticlesResponse articlesResponse = newsResponse.getArticlesResponse().get(0);
        Glide.with(getContext()).load(articlesResponse.getUrlToImage())
                .into(ivNewsMain);
        tvNewsTitle.setText(articlesResponse.getTitle());
        tvNewsDesc.setText(articlesResponse.getDescription());
        cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsiteUrl(articlesResponse.getUrl());
            }
        });
        newsResponse.getArticlesResponse().remove(0);
        newsAdapter = new NewsAdapter(newsResponse.getArticlesResponse(), getContext());
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNews.setAdapter(newsAdapter);

        return view;
    }

    private void openWebsiteUrl(String url) {
        if (getContext() != null) {
            Uri uri = Uri.parse(url);
            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            intentBuilder.setToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            intentBuilder.setStartAnimations(getContext(), R.anim.slide_up, R.anim.slide_down);
            intentBuilder.setExitAnimations(getContext(), android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);
            CustomTabsIntent customTabsIntent = intentBuilder.build();
            customTabsIntent.launchUrl(getContext(), uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
