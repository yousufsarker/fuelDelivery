package com.example.fueldelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PetrolOrderActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText carNameEditText;
    private EditText carModelEditText;
    private EditText plateNumberEditText;
    private EditText plateLetterEditText;
    private EditText quantityEditText;
    private Button orderButton;
    private RadioGroup paymentRadioGroup;
    private Button selectLocationButton;
    private GoogleMap googleMap;
    private LatLng selectedLocation;

    private boolean isOnlinePaymentSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_order);

        carNameEditText = findViewById(R.id.carNameEditText);
        carModelEditText = findViewById(R.id.carModelEditText);
        plateNumberEditText = findViewById(R.id.plateNumberEditText);
        plateLetterEditText = findViewById(R.id.plateLetterEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        orderButton = findViewById(R.id.orderButton);
        paymentRadioGroup = findViewById(R.id.paymentRadioGroup);
        selectLocationButton = findViewById(R.id.selectLocationButton);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        selectLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a map dialog or open a separate activity to select the location
                // Once the user selects the location, update the selectedLocation variable with the latitude and longitude

                // Example: Use a map dialog fragment
                MapDialogFragment dialogFragment = new MapDialogFragment();
                dialogFragment.setOnLocationSelectedListener(new MapDialogFragment.OnLocationSelectedListener() {
                    @Override
                    public void onLocationSelected(LatLng location) {
                        selectedLocation = location;
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(location));
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "map_dialog");
            }
        });


        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carName = carNameEditText.getText().toString();
                String carModel = carModelEditText.getText().toString();
                String plateNumber = plateNumberEditText.getText().toString();
                String plateLetter = plateLetterEditText.getText().toString();
                String quantity = quantityEditText.getText().toString();

                int selectedPaymentId = paymentRadioGroup.getCheckedRadioButtonId();
                if (selectedPaymentId == R.id.onlinePaymentRadioButton) {
                    isOnlinePaymentSelected = true;
                } else if (selectedPaymentId == R.id.cashPaymentRadioButton) {
                    isOnlinePaymentSelected = false;
                }

                // Save the data to the database
                DBPetrol databaseHelper = new DBPetrol(PetrolOrderActivity.this);
                long orderId = databaseHelper.insertOrder(carName, carModel, plateNumber, plateLetter, quantity, isOnlinePaymentSelected, selectedLocation);

                if (isOnlinePaymentSelected) {
                    // Go to the "Online feature coming soon!" page
                    Intent intent = new Intent(PetrolOrderActivity.this, OnlinePaymentActivity.class);
                    startActivity(intent);
                } else {
                    // Display a toast message for successful order placement
                    Toast.makeText(PetrolOrderActivity.this, "Order placed successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Update the selectedLocation variable with the clicked location
                selectedLocation = latLng;
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }
}
