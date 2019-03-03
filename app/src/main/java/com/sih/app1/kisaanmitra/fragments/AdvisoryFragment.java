package com.sih.app1.kisaanmitra.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.adapters.AdvisoryAdapter;
import com.sih.app1.kisaanmitra.model.AdvisoryListResponse;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdvisoryFragment extends Fragment {

    private AdvisoryAdapter advisoryAdapter;
    private RecyclerView recyclerViewBlogs;
    private ProgressBar progressBar;

    public AdvisoryFragment() {
        // Required empty public constructor
    }

    public static AdvisoryFragment newInstance() {
        AdvisoryFragment fragment = new AdvisoryFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advisory, container, false);
        recyclerViewBlogs = view.findViewById(R.id.blogs_recycler);
        advisoryAdapter = new AdvisoryAdapter(getContext());
        progressBar =  view.findViewById(R.id.progress_blogs);
        recyclerViewBlogs.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBlogs.setAdapter(advisoryAdapter);
        recyclerViewBlogs.setNestedScrollingEnabled(false);
        APICall();
        return view;
    }

    private void APICall() {
        ApiServices services = AppClient.getInstance().createService(ApiServices.class);
        Call<AdvisoryListResponse> call = services.getAdvisoryList();
        call.enqueue(new Callback<AdvisoryListResponse>() {
            @Override
            public void onResponse(Call<AdvisoryListResponse> call, Response<AdvisoryListResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                   AdvisoryListResponse advisoryListResponse = response.body();

                    if (advisoryListResponse != null) {
                        if (advisoryListResponse.getSuccess()) {
                            advisoryAdapter.setAdvisoryDataList(advisoryListResponse.getAdvisoryDataList());
                            advisoryAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AdvisoryListResponse> call, Throwable t) {
                Toast.makeText(getContext(), R.string.something_went_wrong, Toast.LENGTH_LONG).show();
            }
        });
    }
}
