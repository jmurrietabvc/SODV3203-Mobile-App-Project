package com.android.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.Dash;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    TextView txtNama, txtEmail;
    Button checkTickets, btnChange;

    AlertDialog alertDialog;
    MenuInflater inflater;

    private ArrayList<String> al_img_tour = new ArrayList<>();
    private ArrayList<String> al_name_tour = new ArrayList<>();
    private ArrayList<String> al_desc_tour = new ArrayList<>();
    private ArrayList<Integer> al_price_tour = new ArrayList<>();
    private ArrayList<String> al_location = new ArrayList<>();
    //private ArrayList<String> al_country = new ArrayList<>();



    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    //private static final String KEY_NAME_COUNTRY = "name_country";
    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";

    private String selectedCountry;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //getData();

        txtNama = findViewById(R.id.tv_fullname);
        txtEmail = findViewById(R.id.tv_email);
        checkTickets = findViewById(R.id.check_ticket);
        btnChange = findViewById(R.id.btn_change);

        selectedCountry = getIntent().getStringExtra("selected_country");

        checkTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameView = preferences.getString(KEY_NAME, null);
                String emailView = preferences.getString(KEY_EMAIL, null);
                String phoneView = preferences.getString(KEY_PHONE, null);

                //String nameCountry = preferences.getString(KEY_NAME_COUNTRY, null);
                String nameTourView = preferences.getString(KEY_NAME_TOUR, null);
                String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
                String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, null);


                if (nameView == "" || emailView == "" || phoneView == "" || totalItemsView == "" || totalPriceView == "" ||
                        nameView == null || emailView == null || phoneView == null || nameTourView == null || totalItemsView == null || totalPriceView == null) {
                    AlertDialog dialog = new AlertDialog.Builder(Dashboard.this)
                            .setTitle("Check Tickets")
                            .setMessage("\nData is Empty")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Dashboard.this, Dashboard.class);
                                    intent.putExtra("selected_country", selectedCountry);
                                    startActivity(intent);
                                    finish();
                                }
                            }).show();
                } else if (nameView == nameView || emailView == emailView || phoneView == phoneView || nameTourView == nameTourView || totalItemsView == totalItemsView || totalPriceView == totalPriceView) {
                    Intent intent = new Intent(Dashboard.this, Itinerary.class);
                    startActivity(intent);
                }
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, CountrySelectionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        preferences = getSharedPreferences("userInfo", 0);

        String nameView = preferences.getString(KEY_NAME, null);
        String emailView = preferences.getString(KEY_EMAIL, null);

        if (nameView != null || emailView != null) {
            txtNama.setText(nameView);
            txtEmail.setText(emailView);
        }

        getData();

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void getData() {
        Intent intent = getIntent();
        //if (intent != null && intent.hasExtra("selected_country")) {
            String selectedCountry = intent.getStringExtra("selected_country");
            if(selectedCountry != null) filterLocations(selectedCountry);
            setupRecyclerView();
        //}
    }

    private void filterLocations(String selectedCountry) {
        // Clear previous data
        al_img_tour.clear();
        al_name_tour.clear();
        al_desc_tour.clear();
        al_price_tour.clear();
        al_location.clear();
        //al_country.clear();

        // Populate with filtered data based on selected country
        if (selectedCountry.equals("Canada")) {
            // Attractions for Canada
            al_img_tour.add("https://cdn.britannica.com/30/94430-120-5DB7AC4A/Niagara-Falls.jpg");
            al_name_tour.add("Niagara Falls");
            al_desc_tour.add("Experience the breathtaking beauty of Niagara Falls.");
            al_price_tour.add(25);
            al_location.add("Niagara Falls, Canada");
            //al_country.add("Canada");

            al_img_tour.add("https://upload.wikimedia.org/wikipedia/commons/c/c5/Moraine_Lake_17092005.jpg");
            al_name_tour.add("Banff National Park");
            al_desc_tour.add("Explore the stunning landscapes of Banff National Park.");
            al_price_tour.add(30);
            al_location.add("Banff, Canada");
            //al_country.add("Canada");


            al_img_tour.add("https://torontolife.com/wp-content/uploads/2022/07/CNT-Exterior-Night-Lighting-201808-02.jpg");
            al_name_tour.add("CN Tower");
            al_desc_tour.add("Enjoy panoramic views from the top of the CN Tower.");
            al_price_tour.add(20);
            al_location.add("Toronto, Canada");
            //al_country.add("Canada");


            al_img_tour.add("https://healthquotes.ca/wp-content/uploads/2020/09/chateau-castle.jpg");
            al_name_tour.add("Old Quebec");
            al_desc_tour.add("Discover the historic charm of Old Quebec City.");
            al_price_tour.add(15);
            al_location.add("Quebec City, Canada");
            //al_country.add("Canada");

        } else if (selectedCountry.equals("Ecuador")) {
            // Attractions for Ecuador
            al_img_tour.add("https://www.travelandleisure.com/thmb/waC7nEboS_-sffrP8-ppPSnWPV0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-674781548-5c2121a846e0fb00011ebaec.jpg");
            al_name_tour.add("Galapagos Islands");
            al_desc_tour.add("Explore the unique wildlife of the Galapagos Islands.");
            al_price_tour.add(50);
            al_location.add("Galapagos Islands, Ecuador");
            //al_country.add("Ecuador");

            al_img_tour.add("https://example.com/ecuador_attraction2.jpg");
            al_name_tour.add("Puerto Santa Ana");
            al_desc_tour.add("Discover the incredible biodiversity of the Amazon Rainforest.");
            al_price_tour.add(40);
            al_location.add("Guayaquil, Ecuador");
            //al_country.add("Ecuador");

            al_img_tour.add("https://example.com/ecuador_attraction3.jpg");
            al_name_tour.add("Quilotoa Crater Lake");
            al_desc_tour.add("Marvel at the stunning beauty of Quilotoa Crater Lake.");
            al_price_tour.add(30);
            al_location.add("Quilotoa, Ecuador");
            //al_country.add("Ecuador");

            al_img_tour.add("https://www.liveabout.com/thmb/AtSW09j4n8ud4_vRGTFcHhnuffM=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/16259645679_58f594d231_k-5ae7eb73c5542e0039141041.jpg");
            al_name_tour.add("Chimborazo Vulcano");
            al_desc_tour.add("Experience the vibrant culture of Otavalo Market.");
            al_price_tour.add(10);
            al_location.add("Chimborazo, Ecuador");
            //al_country.add("Ecuador");
        }
        else if (selectedCountry.equals("USA")) {

        }
        else if (selectedCountry.equals("France")) {

        }
        else if (selectedCountry.equals("Italy")) {

        }
        else if (selectedCountry.equals("Greece")) {

        }
        else if (selectedCountry.equals("Japan")) {

        }
        else if (selectedCountry.equals("Australia")) {

        }
        RecycleViewAdapterProcess();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecycleViewAdapter adapter = new RecycleViewAdapter(al_img_tour, al_name_tour, al_desc_tour, al_price_tour, al_location, this, selectedCountry);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void RecycleViewAdapterProcess() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecycleViewAdapter adapter = new RecycleViewAdapter(al_img_tour, al_name_tour, al_desc_tour, al_price_tour, al_location, this, selectedCountry);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bar_call_center:
                callCenter();
                return true;
            case R.id.bar_email:
                emailCenter();
                return true;
            case R.id.bar_loc:
                getLoc();
                return true;
            case R.id.bar_edit_user:
                editUser();
                return true;
            case R.id.bar_logout:
                getLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void callCenter() {
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .setTitle("Call Center")
                .setMessage("\n1800-TRAVELLA")
                .setNeutralButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("1800-TRAVELLA");
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        intent.setData(Uri.fromParts("tel", String.valueOf(uri), null));

                        if (intent.resolveActivity(getPackageManager()) != null){
                            startActivity(intent);
                        }
                    }
                })
                .show();

    }
    private void emailCenter(){
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_email)
                .setTitle("Email")
                .setMessage("\nsupport@travella.com")
                .setNeutralButton("Go to Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_SEND );
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@travella.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT , "Send us a Message");
                        intent.putExtra(Intent.EXTRA_TEXT , "Travella App");
                        intent.setType("message/rfc822");
                        startActivity(Intent.createChooser(intent , "Choose Your Apps : "));
                    }
                })
                .show();

    }
    private void getLoc(){
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Location")
                .setMessage("\n")
                .setNeutralButton("Go to Location", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri2 = Uri.parse("geo:0,0?q=");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri2);
                        mapIntent.setPackage("com.google.android.apps.maps");

                        if(mapIntent.resolveActivity(getPackageManager()) != null){
                            startActivity(mapIntent);
                        }
                    }
                })
                .show();

    }
    private void editUser(){
        Intent intent = new Intent(Dashboard.this, EditUser.class);
        startActivity(intent);

    }
    private void getLogout(){
        Intent intent = new Intent(Dashboard.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}