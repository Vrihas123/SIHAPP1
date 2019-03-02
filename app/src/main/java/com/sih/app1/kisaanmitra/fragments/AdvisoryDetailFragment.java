package com.sih.app1.kisaanmitra.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.model.AdvisoryData;

public class AdvisoryDetailFragment extends DialogFragment {


    private AdvisoryData  advisoryData;
    private ImageView arrowBack, img;
    private TextView title, desc;

    public void setAdvisoryData(AdvisoryData advisoryData) {
        this.advisoryData = advisoryData;
    }

    public AdvisoryDetailFragment() {
        // Required empty public constructor
    }



    public static AdvisoryDetailFragment newInstance() {
        AdvisoryDetailFragment fragment = new AdvisoryDetailFragment();
        return fragment;
    }



    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
            d.getWindow().setWindowAnimations(R.style.DialogAnimation);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.app.DialogFragment.STYLE_NO_FRAME, R.style.AppTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advisory_detail, container, false);
        title = view.findViewById(R.id.txt_detail_title);
        desc = view.findViewById(R.id.txt_detail_desc);
        img = view.findViewById(R.id.img_detail_image);
        arrowBack = view.findViewById(R.id.arrow_dismiss_advisory_details);
        if (advisoryData != null) {
            title.setText(Html.fromHtml(advisoryData.getTitle()));
            desc.setText(Html.fromHtml(advisoryData.getDescription()));
            Glide.with(getContext()).load(advisoryData.getImage_url()).into(img);
        }
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvisoryDetailFragment.this.dismiss();
            }
        });
        return view;
    }

}
