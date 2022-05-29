package com.test.bestquality.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.bestquality.R;
import com.test.bestquality.helpers.Utils;
import com.test.bestquality.adapters.DeviceDetailsAdapter;
import com.test.bestquality.helpers.DeviceDetailsHelper;
import com.test.bestquality.helpers.DeviceInfoHelper;

import java.util.ArrayList;


public class DeviceFragment extends Fragment {
    private final String[] detail_titles = {
            DeviceInfoHelper.MANUFACTURER_TEXT, DeviceInfoHelper.BRAND_TEXT, DeviceInfoHelper.MODEL_TEXT,
            DeviceInfoHelper.BOARD_TEXT, DeviceInfoHelper.HARDWARE_TEXT, DeviceInfoHelper.USER_TEXT,
            DeviceInfoHelper.HOST_TEXT, DeviceInfoHelper.BOOTLOADER_TEXT, DeviceInfoHelper.SERIAL_TEXT,
            DeviceInfoHelper.SCREEN_RESOLUTION_TEXT
    };
    private final String[] detail_values = {
            DeviceInfoHelper.MANUFACTURER, DeviceInfoHelper.BRAND, DeviceInfoHelper.MODEL,
            DeviceInfoHelper.BOARD, DeviceInfoHelper.HARDWARE, DeviceInfoHelper.USER,
            DeviceInfoHelper.HOST, DeviceInfoHelper.BOOTLOADER, DeviceInfoHelper.SERIAL,
            DeviceInfoHelper.SCREEN_RESOLUTION
    };
    private RecyclerView deviceDetails;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<DeviceDetailsHelper> deviceDetailsHelpers;
    private DeviceDetailsAdapter deviceDetailsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        deviceDetails = view.findViewById(R.id.device_details);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        deviceDetailsHelpers = new ArrayList<>();
        deviceDetailsAdapter = new DeviceDetailsAdapter(deviceDetailsHelpers, getActivity());
        deviceDetails.setAdapter(deviceDetailsAdapter);
        deviceDetails.setLayoutManager(linearLayoutManager);
        addDeviceInfos();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(deviceDetails.getContext(), linearLayoutManager.getOrientation());
        deviceDetails.addItemDecoration(itemDecoration);
        return view;
    }

    //add all the datas
    private void addDeviceInfos() {
        StringBuilder writeText = new StringBuilder("\n\nDevice INFO : \n");
        for (int count = 0; count < detail_titles.length; count++) {
            deviceDetailsHelpers.add(new DeviceDetailsHelper(detail_titles[count], detail_values[count]));
            writeText.append(detail_titles[count]).append(" : ").append(detail_values[count]).append("\n");
        }
        Utils.generateNoteOnSD(getContext(), writeText.toString() + "\n\n");
    }
}
