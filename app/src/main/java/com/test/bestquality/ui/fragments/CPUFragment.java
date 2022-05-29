package com.test.bestquality.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.test.bestquality.R;
import com.test.bestquality.helpers.Utils;

import java.io.IOException;
import java.io.InputStream;


public class CPUFragment extends Fragment {
    private TextView cpuInfo;
    private ProcessBuilder processBuilder;
    private String Holder = "";
    private String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
    private InputStream inputStream;
    private Process process;
    private byte[] byteArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cpu, container, false);
        cpuInfo = view.findViewById(R.id.cpuinfo);
        byteArray = new byte[1024];
        try {
            processBuilder = new ProcessBuilder(DATA);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            while (inputStream.read(byteArray) != -1) {
                Holder = Holder + new String(byteArray);
            }
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        cpuInfo.setText(Holder);
        Utils.generateNoteOnSD(getContext(), "\n\nCPU INFO : \n" + Holder + "\n\n");
        return view;
    }
}
