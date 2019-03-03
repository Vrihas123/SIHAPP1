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
import com.sih.app1.kisaanmitra.adapters.RentAdapter;
import com.sih.app1.kisaanmitra.model.RentResponse;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RentFragment extends Fragment {

    private RecyclerView recyclerViewRent;
    private ProgressBar progressBarRent;
    private RentAdapter rentAdapter;

    public RentFragment() {
        // Required empty public constructor
    }
    public static RentFragment newInstance(String param1, String param2) {
        RentFragment fragment = new RentFragment();
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
        View view = inflater.inflate(R.layout.fragment_rent, container, false);
        recyclerViewRent = view.findViewById(R.id.recycler_rent);
        progressBarRent = view.findViewById(R.id.progress_rennt);

        rentAdapter = new RentAdapter(getContext());
        recyclerViewRent.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRent.setAdapter(rentAdapter);
        APICall();
        return view;
    }

    private void APICall() {
        ApiServices services = AppClient.getInstance().createServiceWithAuth(ApiServices.class, getContext());
        Call<RentResponse> call = services.getPreviousRentLists();
        call.enqueue(new Callback<RentResponse>() {
            @Override
            public void onResponse(Call<RentResponse> call, Response<RentResponse> response) {
                progressBarRent.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    RentResponse rentResponse = response.body();
                    if (rentResponse != null){
                        if (rentResponse.getSuccess()) {
                            rentAdapter.setRentDataList(rentResponse.getRentDataList());
                            rentAdapter.notifyDataSetChanged();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<RentResponse> call, Throwable t) {
                progressBarRent.setVisibility(View.GONE);
                Toast.makeText(getContext(), R.string.something_went_wrong,Toast.LENGTH_LONG).show();
            }
        });
    }

}
