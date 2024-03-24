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

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    TextView txtNama, txtEmail;
    Button checkTickets;

    AlertDialog alertDialog;
    MenuInflater inflater;

    private ArrayList<String> al_img_tour = new ArrayList<>();
    private ArrayList<String> al_name_tour = new ArrayList<>();
    private ArrayList<String> al_desc_tour = new ArrayList<>();
    private ArrayList<Integer> al_price_tour = new ArrayList<>();
    private ArrayList<String> al_location = new ArrayList<>();

    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtNama = findViewById(R.id.tv_fullname);
        txtEmail = findViewById(R.id.tv_email);
        checkTickets = findViewById(R.id.check_ticket);

        checkTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameView = preferences.getString(KEY_NAME, null);
                String emailView = preferences.getString(KEY_EMAIL, null);
                String phoneView = preferences.getString(KEY_PHONE, null);

                String nameTourView = preferences.getString(KEY_NAME_TOUR, null);
                String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
                String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, null);

                if (nameView == "" || emailView == "" || phoneView == "" || nameTourView == "" || totalItemsView == "" || totalPriceView == "" ||
                        nameView == null || emailView == null || phoneView == null || nameTourView == null || totalItemsView == null || totalPriceView == null) {
                    AlertDialog dialog = new AlertDialog.Builder(Dashboard.this)
                            .setTitle("Check Tickets")
                            .setMessage("\nData is Empty")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Dashboard.this, Dashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).show();
                }else if (nameView == nameView || emailView == emailView || phoneView == phoneView || nameTourView == nameTourView || totalItemsView == totalItemsView || totalPriceView == totalPriceView){
                    Intent intent = new Intent(Dashboard.this, Itinerary.class);
                    startActivity(intent);
                }
            }
        });

        preferences = getSharedPreferences("userInfo", 0);

        String namaView = preferences.getString(KEY_NAME, null);
        String emailView = preferences.getString(KEY_EMAIL, null);

        if (namaView != null || emailView != null){
            txtNama.setText(namaView);
            txtEmail.setText(emailView);
        }

        getData();

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void getData() {
        // Attractions for Canada
        al_img_tour.add("https://cdn.britannica.com/30/94430-120-5DB7AC4A/Niagara-Falls.jpg");
        al_name_tour.add("Niagara Falls");
        al_desc_tour.add("Experience the breathtaking beauty of Niagara Falls.");
        al_price_tour.add(25);
        al_location.add("Niagara Falls, Canada");

        al_img_tour.add("https://example.com/canada_attraction2.jpg");
        al_name_tour.add("Banff National Park");
        al_desc_tour.add("Explore the stunning landscapes of Banff National Park.");
        al_price_tour.add(30);
        al_location.add("Banff, Canada");

        al_img_tour.add("https://example.com/canada_attraction3.jpg");
        al_name_tour.add("CN Tower");
        al_desc_tour.add("Enjoy panoramic views from the top of the CN Tower.");
        al_price_tour.add(20);
        al_location.add("Toronto, Canada");

        al_img_tour.add("https://example.com/canada_attraction4.jpg");
        al_name_tour.add("Old Quebec");
        al_desc_tour.add("Discover the historic charm of Old Quebec City.");
        al_price_tour.add(15);
        al_location.add("Quebec City, Canada");

        // Attractions for Ecuador
        al_img_tour.add("https://example.com/ecuador_attraction1.jpg");
        al_name_tour.add("Galapagos Islands");
        al_desc_tour.add("Explore the unique wildlife of the Galapagos Islands.");
        al_price_tour.add(50);
        al_location.add("Galapagos Islands, Ecuador");

        al_img_tour.add("https://example.com/ecuador_attraction2.jpg");
        al_name_tour.add("Amazon Rainforest");
        al_desc_tour.add("Discover the incredible biodiversity of the Amazon Rainforest.");
        al_price_tour.add(40);
        al_location.add("Amazon Rainforest, Ecuador");

        al_img_tour.add("https://example.com/ecuador_attraction3.jpg");
        al_name_tour.add("Quilotoa Crater Lake");
        al_desc_tour.add("Marvel at the stunning beauty of Quilotoa Crater Lake.");
        al_price_tour.add(30);
        al_location.add("Quilotoa, Ecuador");

        al_img_tour.add("https://example.com/ecuador_attraction4.jpg");
        al_name_tour.add("Otavalo Market");
        al_desc_tour.add("Experience the vibrant culture of Otavalo Market.");
        al_price_tour.add(10);
        al_location.add("Otavalo, Ecuador");

        // Attractions for USA
        al_img_tour.add("https://example.com/usa_attraction1.jpg");
        al_name_tour.add("Grand Canyon");
        al_desc_tour.add("Marvel at the awe-inspiring beauty of the Grand Canyon.");
        al_price_tour.add(35);
        al_location.add("Arizona, USA");

        al_img_tour.add("https://example.com/usa_attraction2.jpg");
        al_name_tour.add("Statue of Liberty");
        al_desc_tour.add("Visit the iconic Statue of Liberty in New York Harbor.");
        al_price_tour.add(25);
        al_location.add("New York City, USA");

        al_img_tour.add("https://example.com/usa_attraction3.jpg");
        al_name_tour.add("Yellowstone National Park");
        al_desc_tour.add("Explore the natural wonders of Yellowstone National Park.");
        al_price_tour.add(40);
        al_location.add("Wyoming, USA");

        al_img_tour.add("https://example.com/usa_attraction4.jpg");
        al_name_tour.add("Disneyland");
        al_desc_tour.add("Experience the magic of Disneyland.");
        al_price_tour.add(50);
        al_location.add("California, USA");

        // Attractions for France
        al_img_tour.add("https://example.com/france_attraction1.jpg");
        al_name_tour.add("Eiffel Tower");
        al_desc_tour.add("Enjoy panoramic views from the top of the Eiffel Tower.");
        al_price_tour.add(30);
        al_location.add("Paris, France");

        al_img_tour.add("https://example.com/france_attraction2.jpg");
        al_name_tour.add("Louvre Museum");
        al_desc_tour.add("Explore world-famous art at the Louvre Museum.");
        al_price_tour.add(20);
        al_location.add("Paris, France");

        al_img_tour.add("https://example.com/france_attraction3.jpg");
        al_name_tour.add("Versailles Palace");
        al_desc_tour.add("Discover the opulence of Versailles Palace.");
        al_price_tour.add(25);
        al_location.add("Versailles, France");

        al_img_tour.add("https://example.com/france_attraction4.jpg");
        al_name_tour.add("Mont Saint-Michel");
        al_desc_tour.add("Explore the historic abbey of Mont Saint-Michel.");
        al_price_tour.add(15);
        al_location.add("Normandy, France");

        // Attractions for Italy
        al_img_tour.add("https://example.com/italy_attraction1.jpg");
        al_name_tour.add("Colosseum");
        al_desc_tour.add("Step back in time at the ancient Colosseum.");
        al_price_tour.add(25);
        al_location.add("Rome, Italy");

        al_img_tour.add("https://example.com/italy_attraction2.jpg");
        al_name_tour.add("Venice Canals");
        al_desc_tour.add("Take a gondola ride through the picturesque Venice Canals.");
        al_price_tour.add(30);
        al_location.add("Venice, Italy");

        al_img_tour.add("https://example.com/italy_attraction3.jpg");
        al_name_tour.add("Florence Cathedral");
        al_desc_tour.add("Admire the stunning architecture of Florence Cathedral.");
        al_price_tour.add(20);
        al_location.add("Florence, Italy");

        al_img_tour.add("https://example.com/italy_attraction4.jpg");
        al_name_tour.add("Leaning Tower of Pisa");
        al_desc_tour.add("See the iconic Leaning Tower of Pisa.");
        al_price_tour.add(15);
        al_location.add("Pisa, Italy");

        // Attractions for Greece
        al_img_tour.add("https://example.com/greece_attraction1.jpg");
        al_name_tour.add("Acropolis of Athens");
        al_desc_tour.add("Explore the ancient ruins of the Acropolis of Athens.");
        al_price_tour.add(25);
        al_location.add("Athens, Greece");

        al_img_tour.add("https://example.com/greece_attraction2.jpg");
        al_name_tour.add("Santorini");
        al_desc_tour.add("Experience the stunning beauty of Santorini.");
        al_price_tour.add(35);
        al_location.add("Santorini, Greece");

        al_img_tour.add("https://example.com/greece_attraction3.jpg");
        al_name_tour.add("Mykonos");
        al_desc_tour.add("Enjoy the vibrant nightlife of Mykonos.");
        al_price_tour.add(30);
        al_location.add("Mykonos, Greece");

        al_img_tour.add("https://example.com/greece_attraction4.jpg");
        al_name_tour.add("Meteora");
        al_desc_tour.add("Marvel at the monasteries of Meteora.");
        al_price_tour.add(20);
        al_location.add("Meteora, Greece");

        // Attractions for Japan
        al_img_tour.add("https://example.com/japan_attraction1.jpg");
        al_name_tour.add("Mount Fuji");
        al_desc_tour.add("Hike to the summit of iconic Mount Fuji.");
        al_price_tour.add(30);
        al_location.add("Yamanashi, Japan");

        al_img_tour.add("https://example.com/japan_attraction2.jpg");
        al_name_tour.add("Tokyo Disneyland");
        al_desc_tour.add("Experience the magic of Tokyo Disneyland.");
        al_price_tour.add(40);
        al_location.add("Tokyo, Japan");

        al_img_tour.add("https://example.com/japan_attraction3.jpg");
        al_name_tour.add("Kyoto Temples");
        al_desc_tour.add("Explore the serene beauty of Kyoto's temples.");
        al_price_tour.add(25);
        al_location.add("Kyoto, Japan");

        al_img_tour.add("https://example.com/japan_attraction4.jpg");
        al_name_tour.add("Hiroshima Peace Memorial Park");
        al_desc_tour.add("Reflect on the tragic history of Hiroshima.");
        al_price_tour.add(20);
        al_location.add("Hiroshima, Japan");

        RecycleViewAdapterProcess();

    }

    private void RecycleViewAdapterProcess() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecycleViewAdapter adapter = new RecycleViewAdapter(al_img_tour, al_name_tour, al_desc_tour, al_price_tour, al_location, this);

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
                .setMessage("\n082139860827")
                .setNeutralButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("082139860827");
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
                .setMessage("\nfihdanps@gmail.com")
                .setNeutralButton("Go to Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_SEND );
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fihdanps@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT , "TES DULS YE BANG");
                        intent.putExtra(Intent.EXTRA_TEXT , "Travel App");
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
                .setMessage("\nKota Madiun, Jawa Timur")
                .setNeutralButton("Go to Location", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri2 = Uri.parse("geo:0,0?q="+"Kota Madiun, Jawa Timur");
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