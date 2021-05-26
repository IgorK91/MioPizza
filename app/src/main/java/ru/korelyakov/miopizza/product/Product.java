package ru.korelyakov.miopizza.product;

import android.graphics.drawable.Drawable;

public class Product {

    public String name;

    public ProductType productType;

    public String content;

    public Integer picture;

    public Integer normalCoast;

    public Integer bigCoast;

    public Product(String name, ProductType productType, String content, Integer picture, Integer normalCoast, Integer bigCoast) {
        this.name = name;
        this.productType = productType;
        this.content = content;
        this.picture = picture;
        this.normalCoast = normalCoast;
        this.bigCoast = bigCoast;
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

    public Integer getNormalCoast() {
        return normalCoast;
    }

    public void setNormalCoast(Integer normalCoast) {
        this.normalCoast = normalCoast;
    }

    public Integer getBigCoast() {
        return bigCoast;
    }

    public void setBigCoast(Integer bigCoast) {
        this.bigCoast = bigCoast;
    }


    @Override
    public String toString() {
        return getName();
    }
}
