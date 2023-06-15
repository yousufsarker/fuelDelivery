package com.example.fueldelivery;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PumpsNearMe extends ListActivity {

    private ListView listView;
    private PetrolPump[] petrolPumps = {
            new PetrolPump("Oman Oil Service Station - SQUare", "6.6km away", "https://goo.gl/maps/nfEge362j33K5CJS7"),
            new PetrolPump("Oman Oil Service Station - Al Rusayl Express Highway", "9.0km away", "https://goo.gl/maps/d12AMDn1nGxGS6mc9"),
            new PetrolPump("Oman Oil Service Station - Mawaleh Town", "9.2km away", "https://goo.gl/maps/q6LZKz12oGaDHYYAA"),
            new PetrolPump("Oman Oil Service Station - Burj Al Sahwa", "13.8km away", "https://goo.gl/maps/5BytxWceLDmC44eV9"),
            new PetrolPump("Oman Oil Service Station - Mazoon Street", "8.8 km away", "https://goo.gl/maps/H2YBVrg7QM4xXLUT6")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the ListView
        listView = getListView();
        PetrolPumpAdapter adapter = new PetrolPumpAdapter();
        listView.setAdapter(adapter);

        // Set item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PetrolPump selectedPump = petrolPumps[position];
                openGoogleMaps(selectedPump.getUrl());
            }
        });
    }

    private void openGoogleMaps(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private class PetrolPump {
        private String name;
        private String address;
        private String url;

        public PetrolPump(String name, String address, String url) {
            this.name = name;
            this.address = address;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getUrl() {
            return url;
        }
    }

    private class PetrolPumpAdapter extends ArrayAdapter<PetrolPump> {
        public PetrolPumpAdapter() {
            super(PumpsNearMe.this, R.layout.list_item_pump, petrolPumps);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                LayoutInflater inflater = getLayoutInflater();
                itemView = inflater.inflate(R.layout.list_item_pump, parent, false);
            }

            PetrolPump currentPump = petrolPumps[position];

            TextView nameTextView = itemView.findViewById(R.id.text_pump_name);
            nameTextView.setText(currentPump.getName());

            TextView addressTextView = itemView.findViewById(R.id.text_pump_address);
            addressTextView.setText(currentPump.getAddress());

            return itemView;
        }
    }
}
