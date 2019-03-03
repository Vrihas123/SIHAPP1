package com.sih.app1.kisaanmitra.fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.model.Authrization.GenericResponse;
import com.sih.app1.kisaanmitra.model.ProductData;
import com.sih.app1.kisaanmitra.model.ProductPostData;
import com.sih.app1.kisaanmitra.restapi.ApiServices;
import com.sih.app1.kisaanmitra.restapi.AppClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment{

    private ProductData productData;
    private TextView name, quantity, sellprice, rent, period, fromDate, toDate;
    private ImageView productImage;
    private EditText editTextQ;
    private ProgressBar progressBar;
    private Button proceed;

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
        editTextQ = view.findViewById(R.id.et_quantity);
        quantity = view.findViewById(R.id.product_detail_quanitity);
        sellprice = view.findViewById(R.id.sell_price_detail);
        rent = view.findViewById(R.id.rent_price_detail);
        period = view.findViewById(R.id.period);
        productImage = view.findViewById(R.id.product_detail_img);
        fromDate = view.findViewById(R.id.from_date);
        toDate = view.findViewById(R.id.to_date);
        progressBar = view.findViewById(R.id.progress);
        proceed = view.findViewById(R.id.btn_submit);


        name.setText(productData.getName());
        quantity.setText(String.valueOf(productData.getQuantity())+ " " + productData.getQuantity_type());
        sellprice.setText(String.valueOf(productData.getSell_price())+" RS");
        rent.setText(String.valueOf(productData.getRent_price())+ " RS");
        Glide.with(getContext()).load(productData.getProduct_image()).into(productImage);


        final Calendar cFrom = Calendar.getInstance(), cTo = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener fromL = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cFrom.set(Calendar.YEAR, year);
                cFrom.set(Calendar.MONTH, month);
                cFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelFrom(cFrom);
            }
        };

        final DatePickerDialog.OnDateSetListener toL = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cTo.set(Calendar.YEAR, year);
                cTo.set(Calendar.MONTH, month);
                cTo.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelTo(cTo);
            }
        };

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), fromL, cFrom
                        .get(Calendar.YEAR), cFrom.get(Calendar.MONTH),
                        cFrom.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), toL, cTo
                        .get(Calendar.YEAR), cTo.get(Calendar.MONTH),
                        cTo.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APICall();
            }
        });
        return view;
    }

    private void APICall() {
        progressBar.setVisibility(View.VISIBLE);
        ProductPostData postData = new ProductPostData();
        postData.setProduct_id(productData.getId());
        postData.setQuantity(Integer.parseInt(editTextQ.getText().toString().trim()));
        postData.setStart(fromDate.getText().toString());
        postData.setEnd(toDate.getText().toString());
        ApiServices services = AppClient.getInstance().createServiceWithAuth(ApiServices.class, getContext());
        Call<GenericResponse> call = services.getProductAvailabilityResult(postData);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response != null){
                    GenericResponse genericResponse = response.body();
                    if (genericResponse != null){
                        if (genericResponse.getSuccess()) {
                            Toast.makeText(getContext(), genericResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getContext(), genericResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), R.string.something_went_wrong, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void updateLabelFrom(Calendar myCalendar) {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        fromDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabelTo(Calendar myCalendar) {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        toDate.setText(sdf.format(myCalendar.getTime()));
    }


    public static String toISO8601UTC(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(tz);
        return df.format(date);
    }

    public static Date fromISO8601UTC(String dateStr) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(tz);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


}
