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

            al_img_tour.add("https://dreamkapture.com/dreamkapturehostel/wp-content/uploads/2022/12/Puerto-Santa-Ana-WEB.jpg");
            al_name_tour.add("Puerto Santa Ana");
            al_desc_tour.add("Discover the incredible biodiversity of the Amazon Rainforest.");
            al_price_tour.add(40);
            al_location.add("Guayaquil, Ecuador");
            //al_country.add("Ecuador");

            al_img_tour.add("https://media.tacdn.com/media/attractions-splice-spp-674x446/06/7b/28/57.jpg");
            al_name_tour.add("Quilotoa Crater Lake");
            al_desc_tour.add("Marvel at the stunning beauty of Quilotoa Crater Lake.");
            al_price_tour.add(30);
            al_location.add("Quilotoa, Ecuador");
            //al_country.add("Ecuador");

            al_img_tour.add("https://upload.wikimedia.org/wikipedia/commons/5/5e/Vista_del_Volc%C3%A1n_Chimborazo_desde_Riobamba.jpg");
            al_name_tour.add("Chimborazo Volcano");
            al_desc_tour.add("Experience the vibrant culture of Otavalo Market.");
            al_price_tour.add(10);
            al_location.add("Chimborazo, Ecuador");
            //al_country.add("Ecuador");
        }
        else if (selectedCountry.equals("USA")) {
            al_img_tour.add("https://npf-prod.imgix.net/uploads/shutterstock_97706066_1.jpg?auto=compress%2Cformat&crop=focalpoint&fit=crop&fp-x=0.5&fp-y=0.5&h=900&q=80&w=1600");
            al_name_tour.add("Grand Canyon");
            al_desc_tour.add("Marvel at the awe-inspiring beauty of the Grand Canyon.");
            al_price_tour.add(35);
            al_location.add("Arizona, USA");

            al_img_tour.add("https://www.worldatlas.com/upload/f4/d8/7b/shutterstock-1397031029.jpg");
            al_name_tour.add("Statue of Liberty");
            al_desc_tour.add("Visit the iconic Statue of Liberty in New York Harbor.");
            al_price_tour.add(25);
            al_location.add("New York City, USA");

            al_img_tour.add("https://jacksonholewildlifesafaris.com/wp-content/uploads/2019/09/yellowstone-waterfall-hero-1440x810.jpg");
            al_name_tour.add("Yellowstone National Park");
            al_desc_tour.add("Explore the natural wonders of Yellowstone National Park.");
            al_price_tour.add(40);
            al_location.add("Wyoming, USA");

            al_img_tour.add("https://insidethemagic.net/wp-content/uploads/2023/08/disneyland-resort-california-Credit-Disney.png");
            al_name_tour.add("Disneyland");
            al_desc_tour.add("Experience the magic of Disneyland.");
            al_price_tour.add(50);
            al_location.add("California, USA");

        }
        else if (selectedCountry.equals("France")) {
            al_img_tour.add("https://www.travelandleisure.com/thmb/SPUPzO88ZXq6P4Sm4mC5Xuinoik=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/eiffel-tower-paris-france-EIFFEL0217-6ccc3553e98946f18c893018d5b42bde.jpg");
            al_name_tour.add("Eiffel Tower");
            al_desc_tour.add("Enjoy panoramic views from the top of the Eiffel Tower.");
            al_price_tour.add(30);
            al_location.add("Eiffel Tower, France");

            al_img_tour.add("https://media.architecturaldigest.com/photos/5900cc370638dd3b70018b33/16:9/w_2560%2Cc_limit/Secrets%2520of%2520Louvre%25201.jpg");
            al_name_tour.add("Louvre Museum");
            al_desc_tour.add("Explore world-famous art at the Louvre Museum.");
            al_price_tour.add(20);
            al_location.add("Louvre Museum, France");

            al_img_tour.add("https://media.cntraveler.com/photos/5a91a36760543c4ae96c2ec7/master/pass/Versailles_Getty_2018_GettyImages-154772942.jpg");
            al_name_tour.add("Versailles Palace");
            al_desc_tour.add("Discover the opulence of Versailles Palace.");
            al_price_tour.add(25);
            al_location.add("Versailles Palace, France");

            al_img_tour.add("https://i0.wp.com/www.afrenchcollection.com/wp-content/uploads/2021/08/Mont-Saint-Michel-sunset-1024-x-695-1.jpg");
            al_name_tour.add("Mont Saint-Michel");
            al_desc_tour.add("Explore the historic abbey of Mont Saint-Michel.");
            al_price_tour.add(15);
            al_location.add("Mont Saint-Michel, France");
        }
        else if (selectedCountry.equals("Italy")) {
            al_img_tour.add("https://cdn.britannica.com/36/162636-050-932C5D49/Colosseum-Rome-Italy.jpg");
            al_name_tour.add("Colosseum");
            al_desc_tour.add("Step back in time at the ancient Colosseum.");
            al_price_tour.add(25);
            al_location.add("Colosseum, Italy");

            al_img_tour.add("https://media.tacdn.com/media/attractions-splice-spp-674x446/0b/27/5b/0f.jpg");
            al_name_tour.add("Venice Canals");
            al_desc_tour.add("Take a gondola ride through the picturesque Venice Canals.");
            al_price_tour.add(30);
            al_location.add("Venice Canals , Italy");

            al_img_tour.add("https://www.italiandualcitizenship.net/wp-content/uploads/2019/03/Cathedral-of-Santa-Maria-del-Fiore-Duomo-Florence-Italy.jpg.webp");
            al_name_tour.add("Cathedral of Santa Maria del Fiore");
            al_desc_tour.add("Admire the stunning architecture of Florence Cathedral.");
            al_price_tour.add(20);
            al_location.add("Cathedral of Santa Maria del Fiore , Italy");

            al_img_tour.add("https://www.grunge.com/img/gallery/heres-whats-really-inside-the-leaning-tower-of-pisa/l-intro-1632251432.jpg");
            al_name_tour.add("Leaning Tower of Pisa");
            al_desc_tour.add("See the iconic Leaning Tower of Pisa.");
            al_price_tour.add(15);
            al_location.add("Leaning Tower of Pisa, Italy");
        }
        else if (selectedCountry.equals("Greece")) {
            al_img_tour.add("https://cdn.audleytravel.com/1050/749/79/7994359-acropolis-athens.webp");
            al_name_tour.add("Acropolis of Athens");
            al_desc_tour.add("Explore the ancient ruins of the Acropolis of Athens.");
            al_price_tour.add(25);
            al_location.add("Acropolis of Athens, Greece");

            al_img_tour.add("https://a.cdn-hotels.com/gdcs/production18/d1838/041ae6b1-0a88-4c22-a648-53a22dd4a006.jpg");
            al_name_tour.add("Santorini");
            al_desc_tour.add("Experience the stunning beauty of Santorini.");
            al_price_tour.add(35);
            al_location.add("Santorini, Greece");

            al_img_tour.add("https://a.cdn-hotels.com/gdcs/production44/d14/75a0e859-0146-4d78-8097-211d5ce89278.jpg?impolicy=fcrop&w=800&h=533&q=medium");
            al_name_tour.add("Mykonos");
            al_desc_tour.add("Enjoy the vibrant nightlife of Mykonos.");
            al_price_tour.add(30);
            al_location.add("Mykonos, Greece");

            al_img_tour.add("https://idsb.tmgrup.com.tr/ly/uploads/images/2024/01/16/310379.jpg");
            al_name_tour.add("Meteora");
            al_desc_tour.add("Marvel at the monasteries of Meteora.");
            al_price_tour.add(20);
            al_location.add("Meteora, Greece");
        }
        else if (selectedCountry.equals("Japan")) {
            al_img_tour.add("https://gaijinpot.scdn3.secure.raxcdn.com/app/uploads/sites/6/2016/02/Mount-Fuji-New.jpg");
            al_name_tour.add("Mount Fuji");
            al_desc_tour.add("Hike to the summit of iconic Mount Fuji.");
            al_price_tour.add(30);
            al_location.add("Mount Fuji, Japan");

            al_img_tour.add("https://www.japanrailpassnow.com/wp-content/uploads/2016/12/1000x667xHimeji-Castle1.jpg.pagespeed.ic.8QMnSyijG3.jpg");
            al_name_tour.add("Himeji Castle");
            al_desc_tour.add("One of Japan's most beautiful and well-preserved castles.");
            al_price_tour.add(40);
            al_location.add("Himeji Castle, Japan");

            al_img_tour.add("https://boutiquejapan.com/wp-content/uploads/2019/07/yasaka-pagoda-higashiyama-kyoto-japan.jpg");
            al_name_tour.add("Kyoto Temples");
            al_desc_tour.add("Explore the serene beauty of Kyoto's temples.");
            al_price_tour.add(25);
            al_location.add("Kyoto, Japan");

            al_img_tour.add("https://i.ytimg.com/vi/QXnDG6K4m40/maxresdefault.jpg");
            al_name_tour.add("Nara Park");
            al_desc_tour.add("Home to friendly deer and several important temples and shrines.");
            al_price_tour.add(20);
            al_location.add("Nara Park, Japan");
        }
        else if (selectedCountry.equals("Australia")) {

            al_img_tour.add("https://cdn.britannica.com/64/155864-050-34FBD7A2/view-Great-Barrier-Reef-Australia-coast.jpg");
            al_name_tour.add("Great Barrier Reef");
            al_desc_tour.add("Explore the world's largest coral reef system.");
            al_price_tour.add(45);
            al_location.add("Great Barrier Reef, Australia");

            al_img_tour.add("https://upload.wikimedia.org/wikipedia/commons/thumb/c/cd/Sydneyoperahouse_at_night.jpg/2560px-Sydneyoperahouse_at_night.jpg");
            al_name_tour.add("Sydney Opera House");
            al_desc_tour.add("Visit the iconic performing arts center in Sydney.");
            al_price_tour.add(35);
            al_location.add("Sydney Opera House, Australia");

            al_img_tour.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSAdhv-iRE1jHSKNT210-UENwJn3_DS2z8SnQZ9Zuv3A&s");
            al_name_tour.add("Uluru");
            al_desc_tour.add("Witness the stunning natural beauty of Uluru.");
            al_price_tour.add(30);
            al_location.add("Uluru, Australia");

            al_img_tour.add("https://content.api.news/v3/images/bin/ebe47f1a8d82caebd7845a099f62361e");
            al_name_tour.add("Great Ocean Road");
            al_desc_tour.add("Drive along one of the world's most scenic coastal routes.");
            al_price_tour.add(40);
            al_location.add("Great Ocean Road, Australia");

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