package com.android.travelapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Itinerary extends AppCompatActivity {
    LinearLayout toursContainer;
    Button btnBack;
    AlertDialog dialog;
    private static ArrayList<Tour> allTours = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        toursContainer = findViewById(R.id.toursContainer);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(Itinerary.this, CountrySelectionActivity.class);
            startActivity(intent);
            finish();
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("tour")) {
            Tour receivedTour = intent.getParcelableExtra("tour");
            allTours.add(receivedTour);
        }

        displayTours();
    }

    private void displayTours() {
        toursContainer.removeAllViews();
        //String selectedCountry = getIntent().getStringExtra("selected_country");

        if (allTours != null && !allTours.isEmpty()) {
            for (Tour tour : allTours) {
                View tourView = getLayoutInflater().inflate(R.layout.tour_item_layout, null);
                TextView nameCountry = tourView.findViewById(R.id.name_country);
                ImageView imageView = tourView.findViewById(R.id.image_tour);
                TextView nameTour = tourView.findViewById(R.id.name_tour);
                TextView totalPeople = tourView.findViewById(R.id.total_people);
                TextView totalPrice = tourView.findViewById(R.id.total_price);

                //String selectedCountry = getIntent().getStringExtra("selected_country");

                Glide.with(this)
                        .load(tour.getImageUrl()) // Assuming getImageUrl() returns a URL or resource identifier
                        .into(imageView);

                nameCountry.setText(tour.getCountry());
                nameTour.setText(tour.getName());
                totalPeople.setText(String.valueOf(tour.getTotalItems()));
                totalPrice.setText("CAD" + tour.getTotalPrice());

                Button btnDeleteTour = tourView.findViewById(R.id.btnDeleteTour);
                btnDeleteTour.setOnClickListener(v -> {
                    // Handle deletion of the tour
                    allTours.remove(tour);
                    displayTours(); // Refresh UI after deletion
                });


                toursContainer.addView(tourView);
            }
        } else {
            dialog = new AlertDialog.Builder(Itinerary.this)
                    .setTitle("Check Tickets")
                    .setMessage("Data is Empty")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        Intent intent = new Intent(Itinerary.this, CountrySelectionActivity.class);
                        startActivity(intent);
                        finish();
                    }).show();
        }
    }
}
