package com.test.bestquality.ui.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.test.bestquality.R;
import com.test.bestquality.helpers.Utils;
import com.test.bestquality.adapters.BatteryDetailsAdapter;
import com.test.bestquality.helpers.BatteryDetailsHelper;
import com.test.bestquality.helpers.BatteryInfoHelper;

import java.util.ArrayList;


public class BatteryFragment extends Fragment {
    private final String[] detailTitles = {
            BatteryInfoHelper.BATTERY_LEVEL_TEXT, BatteryInfoHelper.BATTERY_TYPE_TEXT,
            BatteryInfoHelper.POWER_SOURCE_TEXT, BatteryInfoHelper.BATTERY_TEMPERATURE_TEXT,
            BatteryInfoHelper.BATTERY_VOLTAGE_TEXT, BatteryInfoHelper.BATTERY_STATUS
    };
    private RecyclerView batteryInfo;
    private TextView batteryTitle;
    private ArrayList<BatteryDetailsHelper> batteryDetailsHelpers;
    private BatteryReceiver batteryReceiver;
    private CircleProgressBar circleProgressBar;
    private String batteryType, batteryLevel, batteryPowerSource,
            batteryTemperature, batteryVoltage, batteryStatus;

    public BatteryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battery, container, false);

        batteryInfo = view.findViewById(R.id.battery_info);
        circleProgressBar = view.findViewById(R.id.progress);
        batteryTitle = view.findViewById(R.id.battery);

        batteryReceiver = new BatteryReceiver();
        getActivity().registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        return view;
    }

    //add all the battery infos
    private void addBatteryInfos() {
        StringBuilder writeText = new StringBuilder("\n\nBattery Info : \n");
        for (int count = 0; count < detailTitles.length; count++) {
            if (count == 0) {
                writeText.append(detailTitles[count] + " : " + batteryLevel + "\n");
                batteryDetailsHelpers.add(new BatteryDetailsHelper(detailTitles[count], batteryLevel));
            } else if (count == 1) {
                writeText.append(detailTitles[count] + " : " + batteryType + "\n");
                batteryDetailsHelpers.add(new BatteryDetailsHelper(detailTitles[count], batteryType));
            } else if (count == 2) {
                writeText.append(detailTitles[count] + " : " + batteryPowerSource + "\n");
                batteryDetailsHelpers.add(new BatteryDetailsHelper(detailTitles[count], batteryPowerSource));
            } else if (count == 3) {
                writeText.append(detailTitles[count] + " : " + batteryTemperature + "\n");
                batteryDetailsHelpers.add(new BatteryDetailsHelper(detailTitles[count], batteryTemperature));
            } else if (count == 4) {
                writeText.append(detailTitles[count] + " : " + batteryVoltage + "\n");
                batteryDetailsHelpers.add(new BatteryDetailsHelper(detailTitles[count], batteryVoltage));
            } else if (count == 5) {
                writeText.append(detailTitles[count] + " : " + batteryStatus + "\n");
                batteryDetailsHelpers.add(new BatteryDetailsHelper(detailTitles[count], batteryStatus));
            }
//            batteryDetailsHelpers.add(new BatteryDetailsHelper(detailTitles[count], detailValues[count]));
        }
        Utils.generateNoteOnSD(getContext(), writeText.toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(batteryReceiver);
    }

    // determining battery power source
    private String powerSource(int plug) {
        switch (plug) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                return "AC";
            case BatteryManager.BATTERY_PLUGGED_USB:
                return "USB";
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                return "Wireless";
            default:
                return "Battery";
        }
    }

    //determining battery status
    private String getBatteryStatus(int status) {
        switch (status) {
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                return "Discharging";
            case BatteryManager.BATTERY_STATUS_CHARGING:
                return "Charging";
            case BatteryManager.BATTERY_STATUS_FULL:
                return "Full";
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                return "Unknown";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                return "Not charging";
            default:
                return "Unknown";
        }
    }

    class BatteryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float percent = level / (float) scale * 100;
            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            float temperature = (float) (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)) / 10;
            float firehnhite = (temperature * (9 / 5)) + 32;
            float voltage = (float) (intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) * 0.001);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

            circleProgressBar.setProgressTextSize(50);
            circleProgressBar.setProgress((int) percent);
            circleProgressBar.setBackgroundColor(Color.WHITE);
            circleProgressBar.setProgressBackgroundColor(getResources().getColor(R.color.purple_500));
            circleProgressBar.setProgressTextColor(getResources().getColor(R.color.purple_500));

            batteryLevel = percent + " %";
            batteryType = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY) + " Battery";
            batteryTitle.setText(batteryType);
            batteryPowerSource = powerSource(chargePlug);
            batteryTemperature = temperature + " " + (char) 0x00B0 + "C" + " / " + firehnhite + " " + (char) 0x00B0 + "F";
            batteryVoltage = voltage + " v";
            batteryStatus = getBatteryStatus(status);


            batteryDetailsHelpers = new ArrayList<>();
            BatteryDetailsAdapter batteryDetailsAdapter = new BatteryDetailsAdapter(batteryDetailsHelpers, getActivity());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            batteryInfo.setAdapter(batteryDetailsAdapter);
            batteryInfo.setLayoutManager(linearLayoutManager);
            addBatteryInfos();
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(batteryInfo.getContext(), linearLayoutManager.getOrientation());
            batteryInfo.addItemDecoration(itemDecoration);
        }
    }
}
