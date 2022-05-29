package com.test.bestquality.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.bestquality.R;
import com.test.bestquality.helpers.Utils;

public class DisplayFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvDisplay = view.findViewById(R.id.tvDisplay);
        int width = getActivity().getResources().getDisplayMetrics().widthPixels;
        int height = getActivity().getResources().getDisplayMetrics().heightPixels;
        DisplayMetrics metrics = getActivity().getResources().getDisplayMetrics();
        int densityDpi = (int) (metrics.density * 160f);

        tvDisplay.setText("DISPLAY : " + Build.DISPLAY + "\n\n"
                + "WIDTH : " + width + "\n\n"
                + "HEIGHT : " + height + "\n\n"
                + "DENSITY : " + metrics.density + "\n\n"
                + "DENSITYDPI: " + densityDpi + "\n");

        Utils.generateNoteOnSD(getContext(), "\n\nDISPLAY INFO : \n" + tvDisplay.getText().toString() + "\n\n");
    }
}