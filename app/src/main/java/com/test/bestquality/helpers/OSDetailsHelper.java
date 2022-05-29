package com.test.bestquality.helpers;



public class OSDetailsHelper {
    private String detailTitle,detailValue;

    public OSDetailsHelper(String detailTitle, String detailValue) {
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
