package com.example.fueldelivery;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.libraries.places.api.model.Place;

import java.util.Comparator;

public class PlaceDistanceComparator implements Comparator<Place> {

    public LatLng currentLatLng;

    public PlaceDistanceComparator(LatLng currentLatLng) {
        this.currentLatLng = currentLatLng;
    }

    @Override
    public int compare(Place place1, Place place2) {

        if (place1 == null || place1.getLatLng() == null || place2 == null || place2.getLatLng() == null) {
            // Handle null objects appropriately (e.g., return a default value or throw an exception)
            return 0;
        }

        float[] results1 = new float[1];
        float[] results2 = new float[1];

        LatLng latLng1 = place1.getLatLng();
        LatLng latLng2 = place2.getLatLng();

        // Calculate the distances between each place and the current location
        android.location.Location.distanceBetween(
                currentLatLng.latitude, currentLatLng.longitude,
                latLng1.latitude, latLng1.longitude, results1);

        android.location.Location.distanceBetween(
                currentLatLng.latitude, currentLatLng.longitude,
                latLng2.latitude, latLng2.longitude, results2);

        // Compare the distances
        return Float.compare(results1[0], results2[0]);
    }
}

