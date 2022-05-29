package com.test.bestquality.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.bestquality.R;
import com.test.bestquality.helpers.BatteryDetailsHelper;

import java.util.ArrayList;

public class BatteryDetailsAdapter extends RecyclerView.Adapter<BatteryDetailsAdapter.DataHolder> {
    private ArrayList<BatteryDetailsHelper> batteryDetailsHelpers;
    private Context context;

    public BatteryDetailsAdapter(ArrayList<BatteryDetailsHelper> batteryDetailsHelpers, Context context) {
        this.batteryDetailsHelpers = batteryDetailsHelpers;
        this.context = context;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_battery_details_layout, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        BatteryDetailsHelper batteryDetailsHelper = batteryDetailsHelpers.get(position);
        holder.detailTitle.setText(batteryDetailsHelper.getDetailTitle());
        holder.detailValue.setText(String.valueOf(batteryDetailsHelper.getDetailValue()));
    }

    @Override
    public int getItemCount() {
        return batteryDetailsHelpers.size();
    }

    public static class DataHolder extends RecyclerView.ViewHolder {
        TextView detailTitle, detailValue;

        public DataHolder(View itemView) {
            super(itemView);
            detailTitle = itemView.findViewById(R.id.battery_detail_title);
            detailValue = itemView.findViewById(R.id.battery_detail_value);
        }
    }
}
