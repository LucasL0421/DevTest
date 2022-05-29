package com.test.bestquality.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.bestquality.R;
import com.test.bestquality.helpers.DeviceDetailsHelper;

import java.util.ArrayList;

public class DeviceDetailsAdapter extends RecyclerView.Adapter<DeviceDetailsAdapter.DataHolder> {
    private ArrayList<DeviceDetailsHelper> deviceDetailsHelpers;
    private Context context;

    public DeviceDetailsAdapter(ArrayList<DeviceDetailsHelper> deviceDetailsHelpers, Context context) {
        this.deviceDetailsHelpers = deviceDetailsHelpers;
        this.context = context;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_device_details_layout, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        DeviceDetailsHelper deviceDetailsHelper = deviceDetailsHelpers.get(position);
        holder.detailTitle.setText(deviceDetailsHelper.getDetailTitle());
        holder.detailValue.setText(String.valueOf(deviceDetailsHelper.getDetailValue()));
    }

    @Override
    public int getItemCount() {
        return deviceDetailsHelpers.size();
    }

    public static class DataHolder extends RecyclerView.ViewHolder {
        TextView detailTitle, detailValue;

        public DataHolder(View itemView) {
            super(itemView);
            detailTitle = itemView.findViewById(R.id.device_detail_title);
            detailValue = itemView.findViewById(R.id.device_detail_value);
        }
    }
}
