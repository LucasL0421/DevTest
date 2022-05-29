package com.test.bestquality.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.bestquality.R;
import com.test.bestquality.helpers.Utils;
import com.test.bestquality.adapters.OSDetailsAdapter;
import com.test.bestquality.helpers.OSDetailsHelper;
import com.test.bestquality.helpers.OSInfoHelper;

import java.util.ArrayList;

public class OSFragment extends Fragment {
    private final String[] detail_titles = {
            OSInfoHelper.VERSION_TEXT, OSInfoHelper.VERSION_NAME, OSInfoHelper.VERSION_API,
            OSInfoHelper.BUILD_ID_TEXT, OSInfoHelper.KERNEL_TEXT, OSInfoHelper.FINGERPRINT_TEXT
    };
    private final String[] detail_values = {
            OSInfoHelper.OS_VERSION, OSInfoHelper.OS_NAME, String.valueOf(OSInfoHelper.OS_API),
            OSInfoHelper.OS_BUILD_ID, OSInfoHelper.OS_KERNEL, OSInfoHelper.OS_FINGERPRINT
    };
    private ArrayList<OSDetailsHelper> osDetailsHelpers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_os, container, false);
        RecyclerView osDetails = view.findViewById(R.id.os_details);
        ImageView osImage = view.findViewById(R.id.os_img);
        TextView osName = view.findViewById(R.id.os_title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        osDetailsHelpers = new ArrayList<>();
        OSDetailsAdapter osDetailsAdapter = new OSDetailsAdapter(osDetailsHelpers, getActivity());
        osDetails.setAdapter(osDetailsAdapter);
        osDetails.setLayoutManager(linearLayoutManager);
        addOSInfos();
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(osDetails.getContext(), linearLayoutManager.getOrientation());
        osDetails.addItemDecoration(itemDecoration);
        osName.setText(OSInfoHelper.PRINT_OS_NAME_VERSION);
        osImage.setImageResource(OSInfoHelper.fetchOSImg());
        return view;
    }

    //add all the datas
    private void addOSInfos() {
        StringBuilder writeText = new StringBuilder();
        writeText.append("OS INFO\n");
        for (int count = 0; count < detail_titles.length; count++) {
            writeText.append(detail_titles[count]).append(" : ").append(detail_values[count]).append("\n");
            osDetailsHelpers.add(new OSDetailsHelper(detail_titles[count], detail_values[count]));
        }
        writeText.append("\n\n");
        Utils.generateNoteOnSD(getContext(), writeText.toString());
    }
}
