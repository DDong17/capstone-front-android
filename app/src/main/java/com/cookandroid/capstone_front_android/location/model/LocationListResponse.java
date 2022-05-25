package com.cookandroid.capstone_front_android.location.model;

import java.util.List;

public class LocationListResponse {
    private List<LocationResponse> locations;

    public List<LocationResponse> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationResponse> locations) {
        this.locations = locations;
    }

    public LocationListResponse(List<LocationResponse> locations) {
        this.locations = locations;
    }
}
