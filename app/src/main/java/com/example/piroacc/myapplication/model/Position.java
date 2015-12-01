package com.example.piroacc.myapplication.model;

import com.example.piroacc.myapplication.model.dto.response.parent.PositionForParentMDTOResponse;

public class Position implements java.io.Serializable {

    private double longitude;
    private double latitude;
    private String creationDate;

    public Position() {
    }

    public Position(PositionForParentMDTOResponse temp) {
        longitude=temp.getLongitude();
        latitude=temp.getLatitude();
        creationDate=temp.getCreationDate();

    }

    @Override
    public String toString() {
        return "Position{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
