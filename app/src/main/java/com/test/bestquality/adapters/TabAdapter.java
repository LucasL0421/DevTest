package com.test.bestquality.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.test.bestquality.ui.fragments.BatteryFragment;
import com.test.bestquality.ui.fragments.CPUFragment;
import com.test.bestquality.ui.fragments.DeviceFragment;
import com.test.bestquality.ui.fragments.DisplayFragment;
import com.test.bestquality.ui.fragments.OSFragment;
import com.test.bestquality.ui.fragments.SensorsFragment;


public class TabAdapter extends FragmentPagerAdapter {
    private int tabCount;

    public TabAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            default:
                return new OSFragment();
            case 1:
                return new DeviceFragment();
            case 2:
                return new CPUFragment();
            case 3:
                return new BatteryFragment();
            case 4:
                return new SensorsFragment();
            case 5:
                return new DisplayFragment();
//            case 4:
//                return new StorageFragment();
//            case 5:
//                return new CameraFragment();
//            case 6:
//                return new NetworkFragment();
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}