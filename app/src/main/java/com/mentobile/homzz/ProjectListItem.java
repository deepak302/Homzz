package com.mentobile.homzz;

import android.graphics.Bitmap;

/**
 * Created by user on 10/19/2015.
 */
public class ProjectListItem {

    private int projectID;
    private String imageName;
    private String builderName;
    private String location;
    private String areaBHK;
    private String area;
    private String price;
    private boolean isLike ;

    public ProjectListItem(int projectID, String imageName, String builderName, String location, String areaBHK, String area, String price, boolean isLike) {
        this.projectID = projectID;
        this.imageName = imageName;
        this.builderName = builderName;
        this.location = location;
        this.areaBHK = areaBHK ;
        this.area = area;
        this.price = price;
        this.isLike = isLike ;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public String getAreaBHK() {
        return areaBHK;
    }

    public void setAreaBHK(String areaBHK) {
        this.areaBHK = areaBHK;
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

    public boolean isLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }
}
