package com.test.bestquality.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.bestquality.R;
import com.test.bestquality.helpers.OSDetailsHelper;

import java.util.ArrayList;

public class OSDetailsAdapter extends RecyclerView.Adapter<OSDetailsAdapter.DataHolder> {
    private ArrayList<OSDetailsHelper> osDetailsHelpers;
    private Context context;

    public OSDetailsAdapter(ArrayList<OSDetailsHelper> osDetailsHelpers, Context context) {
        this.osDetailsHelpers = osDetailsHelpers;
        this.context = context;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_os_details_layout, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        OSDetailsHelper osDetailsHelper = osDetailsHelpers.get(position);
        holder.detailTitle.setText(osDetailsHelper.getDetailTitle());
        holder.detailValue.setText(String.valueOf(osDetailsHelper.getDetailValue()));
    }

    @Override
    public int getItemCount() {
        return osDetailsHelpers.size();
    }

    public static class DataHolder extends RecyclerView.ViewHolder {
        TextView detailTitle, detailValue;

        public DataHolder(View itemView) {
            super(itemView);
            detailTitle = itemView.findViewById(R.id.os_detail_title);
            detailValue = itemView.findViewById(R.id.os_detail_value);
        }
    }
}
