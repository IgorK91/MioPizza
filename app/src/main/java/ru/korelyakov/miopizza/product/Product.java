package ru.korelyakov.miopizza.product;

public class Product {

    private String name;

    private ProductType productType;

    private String content;

    private int picture;

    private int normalCoast;

    private int bigCoast;

    // количество
    private int count;

    // для понимания большой товар или маленький
    private boolean big = false;

    public Product(String name, ProductType productType, String content, int picture, int normalCoast, int bigCoast) {
        this.name = name;
        this.productType = productType;
        this.content = content;
        this.picture = picture;
        this.normalCoast = normalCoast;
        this.bigCoast = bigCoast;
        this.count = 0;
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

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getNormalCoast() {
        return normalCoast;
    }

    public void setNormalCoast(int normalCoast) {
        this.normalCoast = normalCoast;
    }

    public int getBigCoast() {
        return bigCoast;
    }

    public void setBigCoast(int bigCoast) {
        this.bigCoast = bigCoast;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isBig() {
        return big;
    }

    public void setBig(boolean big) {
        this.big = big;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * Получить полную цену товара
     * @return
     */
    public int getFullPrice() {
        int cost = big ? bigCoast : normalCoast;
        return count * cost;
    }
}
