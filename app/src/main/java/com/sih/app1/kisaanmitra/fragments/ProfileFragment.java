package com.sih.app1.kisaanmitra.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.adapters.AddCropsAdapter;
import com.sih.app1.kisaanmitra.adapters.MyCropsAdapter;
import com.sih.app1.kisaanmitra.model.Crop;
import com.sih.app1.kisaanmitra.model.CropsWrapper;
import com.sih.app1.kisaanmitra.model.News.NewsResponse;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;
import com.sih.app1.kisaanmitra.utils.AppConstants;
import com.sih.app1.kisaanmitra.utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private CardView cvImage, cvFarmerInfo, cvMyCrops, cvAddCrops;
    private RecyclerView rvMyCrops, rvAddCrops;
    private TextView tvName, tvContact, tvEmail;
    private ProgressBar pbMyCrops, pbAddCrops;
    private CropsWrapper cropsWrapper;
    private TinyDB db;
    private AddCropsAdapter addCropsAdapter;
    private MyCropsAdapter myCropsAdapter;

    private List<Crop> crops = new ArrayList<>();
    private List<Crop> allCrops = new ArrayList<>();

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        cvImage = view.findViewById(R.id.cvImage);
        cvFarmerInfo = view.findViewById(R.id.cvFarmerInfo);
        cvMyCrops = view.findViewById(R.id.cvMyCrops);
        cvAddCrops = view.findViewById(R.id.cvAddCrops);
        rvMyCrops = view.findViewById(R.id.rvMyCrops);
        rvAddCrops = view.findViewById(R.id.rvAddCrops);
        tvName = view.findViewById(R.id.tvName);
        tvContact = view.findViewById(R.id.tvContact);
        tvEmail = view.findViewById(R.id.tvEmail);
        pbAddCrops = view.findViewById(R.id.pbAddCrops);
        pbMyCrops = view.findViewById(R.id.pbMyCrops);

        rvMyCrops.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAddCrops.setLayoutManager(new LinearLayoutManager(getContext()));

        cropsWrapper = new CropsWrapper();
        db = new TinyDB(getContext());

        tvName.setText(db.getString(AppConstants.FIRST_NAME)+" "+db.getString(AppConstants.LAST_NAME));
        tvContact.setText(db.getString(AppConstants.CONTACT));
        tvEmail.setText(db.getString(AppConstants.EMAIL));

        cvMyCrops.setVisibility(View.VISIBLE);
        apiCallMyCrops();
        if(crops.size() >0){
            myCropsAdapter = new MyCropsAdapter(crops, getContext());
            rvMyCrops.setAdapter(myCropsAdapter);
        }
        else
            cvMyCrops.setVisibility(View.GONE);

        apiCallAllCrops();

        return view;
    }

    public void apiCallMyCrops(){
        pbMyCrops.setVisibility(View.VISIBLE);
        cvMyCrops.setVisibility(View.VISIBLE);
        ApiServices services = AppClient.getInstance().createServiceWithAuth(ApiServices.class, getContext());
        Call<CropsWrapper> call = services.getMyCrops();
        call.enqueue(new Callback<CropsWrapper>() {
            @Override
            public void onResponse(Call<CropsWrapper> call, Response<CropsWrapper> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        crops = response.body().getCrop_list();
                    }
                }
                pbMyCrops.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CropsWrapper> call, Throwable t) {
                pbMyCrops.setVisibility(View.GONE);
                cvMyCrops.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Unable to load your crops", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void apiCallAllCrops(){
        pbAddCrops.setVisibility(View.VISIBLE);
        cvAddCrops.setVisibility(View.VISIBLE);
        ApiServices services = AppClient.getInstance().createServiceWithAuth(ApiServices.class, getContext());
        Call<CropsWrapper> call = services.getAllCrops();
        call.enqueue(new Callback<CropsWrapper>() {
            @Override
            public void onResponse(Call<CropsWrapper> call, Response<CropsWrapper> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        pbAddCrops.setVisibility(View.GONE);
                        allCrops = response.body().getCrop_list();
                        addCropsAdapter = new AddCropsAdapter(allCrops, getContext());
                        rvAddCrops.setAdapter(addCropsAdapter);
                    }
                }
                pbAddCrops.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CropsWrapper> call, Throwable t) {
                pbMyCrops.setVisibility(View.GONE);
                cvMyCrops.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Unable to load your crops", Toast.LENGTH_LONG).show();
            }
        });
    }

//    public static void setData(List<Crop> cropList){
//
//        crops.clear();
//        crops.addAll(cropList);
//        myCropsAdapter.notifyDataSetChanged();
//
//    }

}
