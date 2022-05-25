package com.cookandroid.capstone_front_android.location.model;

public class LocationResponse {

    private String title;
    private String address;
    private String areaName;
    private String contentName;
    private String firstImage;
    private double mapX;
    private double mapY;

    public LocationResponse(String title, String address, String areaName, String contentName, String firstImage, double mapX, double mapY) {
        this.title = title;
        this.address = address;
        this.areaName = areaName;
        this.contentName = contentName;
        this.firstImage = firstImage;
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public double getMapX() {
        return mapX;
    }

    public void setMapX(double mapX) {
        this.mapX = mapX;
    }

    public double getMapY() {
        return mapY;
    }

    public void setMapY(double mapY) {
        this.mapY = mapY;
    }
}
