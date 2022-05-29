package com.test.bestquality.ui.activities;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.test.bestquality.R;
import com.test.bestquality.helpers.Utils;

public class HomeActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private final Runnable runnable = () -> {
//        btnClick.setEnabled(true);
//        btnClick.setVisibility(View.VISIBLE);
//        pbLoading.setVisibility(View.GONE);
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    };
    private Button btnClick;
    private ProgressBar pbLoading;

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkPermission();
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                requestPermission();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission()) {
                requestPermission();
            }
        } else {
            Utils.generateNotesFile(this);
        }

        pbLoading = findViewById(R.id.pbLoading);
        btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(view -> {
            if (checkPermission()) {
                Utils.deleteNotesFile(view.getContext());
                Utils.generateNotesFile(view.getContext());

                btnClick.setEnabled(false);
                btnClick.setVisibility(View.GONE);
                pbLoading.setVisibility(View.VISIBLE);
                handler.postDelayed(runnable, 2500);
            } else {
                Toast.makeText(view.getContext(), "Please allow storage permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
                startActivityForResult(intent, 2296);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2296);
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE}, 13);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }
}