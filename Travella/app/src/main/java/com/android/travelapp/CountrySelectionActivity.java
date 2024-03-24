package com.android.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CountrySelectionActivity extends AppCompatActivity {

    Spinner countrySpinner;
    Spinner citySpinner;
    Button selectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);

        countrySpinner = findViewById(R.id.country_spinner);
        citySpinner = findViewById(R.id.city_spinner);
        selectButton = findViewById(R.id.select_button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCountry = (String) adapterView.getItemAtPosition(i);
                ArrayAdapter<CharSequence> cityAdapter = getCityAdapter(selectedCountry);
                citySpinner.setAdapter(cityAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCountry = (String) countrySpinner.getSelectedItem();
                String selectedCity = (String) citySpinner.getSelectedItem();
                Intent intent = new Intent();
                intent.putExtra("selected_country", selectedCountry);
                intent.putExtra("selected_city", selectedCity);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private ArrayAdapter<CharSequence> getCityAdapter(String country) {
        ArrayAdapter<CharSequence> adapter;
        switch (country) {
            case "Canada":
                adapter = ArrayAdapter.createFromResource(this, R.array.cities_canada,
                        android.R.layout.simple_spinner_item);
                break;
            case "Ecuador":
                adapter = ArrayAdapter.createFromResource(this, R.array.cities_ecuador,
                        android.R.layout.simple_spinner_item);
                break;
            case "USA":
                adapter = ArrayAdapter.createFromResource(this, R.array.cities_usa,
                        android.R.layout.simple_spinner_item);
                break;
            case "France":
                adapter = ArrayAdapter.createFromResource(this, R.array.cities_france,
                        android.R.layout.simple_spinner_item);
                break;
            case "Italy":
                adapter = ArrayAdapter.createFromResource(this, R.array.cities_italy,
                        android.R.layout.simple_spinner_item);
                break;
            case "Greece":
                adapter = ArrayAdapter.createFromResource(this, R.array.cities_greece,
                        android.R.layout.simple_spinner_item);
                break;
            case "Japan":
                adapter = ArrayAdapter.createFromResource(this, R.array.cities_japan,
                        android.R.layout.simple_spinner_item);
                break;
            default:
                adapter = ArrayAdapter.createFromResource(this, R.array.cities_default,
                        android.R.layout.simple_spinner_item);
                break;
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
