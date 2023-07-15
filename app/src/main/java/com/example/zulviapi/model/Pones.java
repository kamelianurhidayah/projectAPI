package com.example.zulviapi.model;

import com.google.gson.annotations.SerializedName;

public class Pones {

    private Integer id;

    @SerializedName("phoneName")
    private String phoneName;

    @SerializedName("price")
    private Integer price;

    public Pones(Integer id, String phoneName, Integer price) {
        this.id = id;
        this.phoneName = phoneName;
        this.price = price;
    }

    public Pones() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
