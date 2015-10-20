package com.mentobile.homzz;

import android.graphics.Bitmap;

/**
 * Created by user on 10/19/2015.
 */
public class TrendItem {

    private Bitmap imgBitmap;
    private String builderName;
    private String location;
    private String area;
    private String price;

    public TrendItem(Bitmap imgBitmap, String builderName, String location, String area, String price) {
        this.imgBitmap = imgBitmap;
        this.builderName = builderName;
        this.location = location;
        this.area = area;
        this.price = price;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }

    public String getBuilderName() {
        return builderName;
    }

    public void setBuilderName(String builderName) {
        this.builderName = builderName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
