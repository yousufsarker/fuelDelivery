package com.example.fueldelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomePageActivity extends AppCompatActivity {
    private ImageButton petrolButton;
    private ImageButton dieselButton;
    private ImageButton pumpsnearme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        petrolButton = findViewById(R.id.imageButton);
        petrolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity for ordering petrol
                Intent intent = new Intent(HomePageActivity.this, PetrolOrderActivity.class);
                startActivity(intent);
            }
        });

        dieselButton = findViewById(R.id.imageButton2);
        dieselButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity for ordering petrol
                Intent intent = new Intent(HomePageActivity.this, DieselOrderActivity.class);
                startActivity(intent);
            }
        });

        pumpsnearme = findViewById(R.id.imageButton3);
        pumpsnearme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the new activity for ordering petrol
                Intent intent = new Intent(HomePageActivity.this, PumpsNearMe.class);
                startActivity(intent);
            }
        });
    }
}