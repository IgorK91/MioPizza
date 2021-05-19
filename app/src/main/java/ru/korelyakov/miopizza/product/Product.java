package ru.korelyakov.miopizza.product;

import android.graphics.drawable.Drawable;

public class Product {

    public String name;

    public ProductType productType;

    public String content;

    public Integer picture;

    public Product(String name, ProductType productType, String content, Integer picture) {
        this.name = name;
        this.productType = productType;
        this.content = content;
        this.picture = picture;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return getName();
    }
}
