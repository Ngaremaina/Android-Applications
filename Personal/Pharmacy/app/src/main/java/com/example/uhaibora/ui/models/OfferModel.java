package com.example.uhaibora.ui.models;

public class OfferModel {
    String imageUrl, name;
    Integer price, discount;

    public OfferModel() {
    }

    public OfferModel(String imageUrl, String name, Integer price, Integer discount) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
