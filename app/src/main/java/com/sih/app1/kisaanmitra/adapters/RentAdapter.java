package com.sih.app1.kisaanmitra.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sih.app1.kisaanmitra.R;
import com.sih.app1.kisaanmitra.model.RentData;
import com.sih.app1.kisaanmitra.utils.ProgressDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RentAdapter extends RecyclerView.Adapter<RentAdapter.MyViewHolder> {


    private Context context;
    private LayoutInflater layoutInflater;
    private ProgressDialog progressDialog;
    private List<RentData> rentDataList = new ArrayList<>();

    public RentAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setRentDataList(List<RentData> rentDataList) {
        this.rentDataList = rentDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.rent_item, viewGroup, false);
        progressDialog = new ProgressDialog();
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        final RentData data = rentDataList.get(i);
        holder.name.setText(data.getProduct_name());
        holder.price.setText(String.valueOf(data.getPrice())+ " RS");
        holder.quantity.setText(String.valueOf(data.getQuantity()));
        holder.start.setText(dateToString(fromISO8601UTC(data.getDuration_start())));
        holder.end.setText(dateToString(fromISO8601UTC(data.getDuration_end())));
        if (data.isStatus()){
            holder.status.setText("Rent is in duration");
        }else {
            holder.status.setText("Rent is completed");
        }
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

    public String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    @Override
    public int getItemCount() {
        return rentDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, quantity, start, end, status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.rent_name);
            price = itemView.findViewById(R.id.rent_price);
            quantity = itemView.findViewById(R.id.rent_quantity);
            start = itemView.findViewById(R.id.rent_start);
            end = itemView.findViewById(R.id.rent_end);
            status = itemView.findViewById(R.id.rent_status);
        }
    }
}
