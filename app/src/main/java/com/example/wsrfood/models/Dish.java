package com.example.wsrfood.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "dishes")
public class Dish implements Serializable {
    @PrimaryKey
    @NonNull
    public String id;
    public String category;
    public String name;
    public double price;
    public String icon;
    public String version;

    public Dish(@NonNull String id, String category, String name, double price, String icon, String version) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.icon = icon;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getIcon() {
        return icon;
    }

    public String getVersion() {
        return version;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
