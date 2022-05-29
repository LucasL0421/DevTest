package com.test.bestquality.ui.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.test.bestquality.R;
import com.test.bestquality.adapters.TabAdapter;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        setStatusBarColor();
        setTitle(getString(R.string.app_name));
        tabLayout = findViewById(R.id.tabLayout);
        addTabs();
        viewPager = findViewById(R.id.viewPager);
        tabEvents();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //adding all the tab layout events
    private void tabEvents() {
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //change the status bar color according to the theme
    public void setStatusBarColor() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.purple_700));
    }

    //add all the tabs
    private void addTabs() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_android).setText(R.string.os_tab_text));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_device).setText(R.string.device_tab_text));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_cpu).setText(R.string.cpu_tab_text));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_battery_charging_full).setText(R.string.battery_tab_text));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_storage).setText(R.string.storage_tab_text));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_camera).setText(R.string.camera_tab_text));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_network_wifi).setText(R.string.network_tab_text));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_sensor).setText(R.string.sensor_tab_text));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_display_black).setText(R.string.display_tab_text));
    }
}
