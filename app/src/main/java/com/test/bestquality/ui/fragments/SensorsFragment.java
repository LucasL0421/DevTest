package com.test.bestquality.ui.fragments;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.bestquality.R;
import com.test.bestquality.helpers.Utils;

import java.util.List;


public class SensorsFragment extends Fragment {
    private TextView tvSensorDetails;

    public SensorsFragment() {
        Log.e("Sensor : ", "Sensor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sensors, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            tvSensorDetails = view.findViewById(R.id.tvSensorDetails);
            SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
            List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_ALL);

            for (Sensor sensor : list) {
                tvSensorDetails.append(sensor.toString() + "\n\n");
            }
            Utils.generateNoteOnSD(getContext(), "\n\nSensor Details : \n" + tvSensorDetails.getText().toString() + "\n\n");
        }
    }
}
