package com.android.travelapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Tour implements Parcelable{
    private String imageUrl;
    //private String country;
    private String name;
    private double price;
    private int totalItems;
    private double totalPrice;

    // Constructor
    public Tour(String imageUrl, String name, double price, int totalItems, double totalPrice) {
        this.imageUrl = imageUrl;
        //this.country = country;
        this.name = name;
        this.price = price;
        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
    }

    protected Tour(Parcel in) {
        imageUrl = in.readString();
        //country = in.readString();
        name = in.readString();
        price = in.readDouble();
        totalItems = in.readInt();
        totalPrice = in.readDouble();
    }

    public static final Creator<Tour> CREATOR = new Creator<Tour>() {
        @Override
        public Tour createFromParcel(Parcel in) {
            return new Tour(in);
        }

        @Override
        public Tour[] newArray(int size) {
            return new Tour[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        //dest.writeString(country);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(totalItems);
        dest.writeDouble(totalPrice);
    }

    // Getters and Setters
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /*public String getCountry() {return country;}

    public void setCountry(String country){this.country = country;}*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

