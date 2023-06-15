package com.example.fueldelivery;

import com.google.android.gms.maps.model.LatLng;

public class PetrolStation implements Comparable<PetrolStation> {
    private String name;
    private LatLng location;

    public PetrolStation(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public LatLng getLocation() {
        return location;
    }

    @Override
    public int compareTo(PetrolStation other) {
        // Compare petrol stations based on distance
        // You need to implement your own distance calculation logic here
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}

