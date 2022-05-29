package com.test.bestquality.helpers;


public class BatteryDetailsHelper {
    private String detailTitle, detailValue;

    public BatteryDetailsHelper(String detailTitle, String detailValue) {
        this.detailTitle = detailTitle;
        this.detailValue = detailValue;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public String getDetailValue() {
        return detailValue;
    }
}
