package com.mentobile.homzz;

/**
 * Created by user on 10/29/2015.
 */
public class ProjectDetailItem {

    private int    ProjectId;
    private String DeveloperName ;
    private String DeveloperType ;
    private String DeveloperPhone ;
    private String ProjectName ;
    private String Type ;
    private String Bedrooms;
    private String ConstructionStatus;
    private String PlotArea;
    private String CoverArea ;
    private String Price ;
    private String Location;
    private String City ;
    private String State ;
    private String PinCode ;
    private String Country ;

    public ProjectDetailItem(int projectId, String country, String developerName, String developerType, String developerPhone, String projectName, String type, String bedrooms, String constructionStatus, String plotArea, String coverArea, String price, String location, String city, String state, String pinCode) {
        ProjectId = projectId;
        Country = country;
        DeveloperName = developerName;
        DeveloperType = developerType;
        DeveloperPhone = developerPhone;
        ProjectName = projectName;
        Type = type;
        Bedrooms = bedrooms;
        ConstructionStatus = constructionStatus;
        PlotArea = plotArea;
        CoverArea = coverArea;
        Price = price;
        Location = location;
        City = city;
        State = state;
        PinCode = pinCode;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public String getDeveloperName() {
        return DeveloperName;
    }

    public void setDeveloperName(String developerName) {
        DeveloperName = developerName;
    }

    public String getDeveloperType() {
        return DeveloperType;
    }

    public void setDeveloperType(String developerType) {
        DeveloperType = developerType;
    }

    public String getDeveloperPhone() {
        return DeveloperPhone;
    }

    public void setDeveloperPhone(String developerPhone) {
        DeveloperPhone = developerPhone;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getBedrooms() {
        return Bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        Bedrooms = bedrooms;
    }

    public String getConstructionStatus() {
        return ConstructionStatus;
    }

    public void setConstructionStatus(String constructionStatus) {
        ConstructionStatus = constructionStatus;
    }

    public String getPlotArea() {
        return PlotArea;
    }

    public void setPlotArea(String plotArea) {
        PlotArea = plotArea;
    }

    public String getCoverArea() {
        return CoverArea;
    }

    public void setCoverArea(String coverArea) {
        CoverArea = coverArea;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
