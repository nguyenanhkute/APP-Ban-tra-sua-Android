package com.example.app_banhang.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("name") private String name;
    @SerializedName("price") private String price;
    @SerializedName("image") private String image;
    @SerializedName("id") private String id;
    public Product(String name, String price, String image, String id) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


