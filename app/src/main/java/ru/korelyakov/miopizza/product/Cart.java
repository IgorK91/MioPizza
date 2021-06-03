package ru.korelyakov.miopizza.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.product.ProductType;

public class Cart implements Serializable {

    private List<Product> productList = new ArrayList<>();
    private List<Integer> coastList = new ArrayList<>();
    private List<Integer> countList = new ArrayList<>();
    private String name;
    private String number;
    private int countPosition;
    private List<String> nameList = new ArrayList<>();

    public void addToCart(Product product) {
        productList.add(product);
    }

    public void removeFromCart(Product product) {
        productList.remove(product);
    }

    public void cleanCart() {
        productList.clear();
        nameList.clear();
        coastList.clear();
        countList.clear();
    }

    public Product getItem(int index) {
        return productList.get(index);
    }

    public int getSize() {
        return productList.size();
    }

    public List<Product> getItems() {
        return productList;
    }

    public void addToCoastList(int integer) {
        coastList.add(integer);
    }

    public List<Integer> getCoastList() {
        return coastList;
    }

    public void addToCountList(int integer) {
        countList.add(integer);
    }

    public List<Integer> getCountList() {
        return countList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void addPositions(int countPosition) {
        this.countPosition += countPosition;
    }

    public int getCountPosition() {
        return countPosition;
    }

    public void addToCartNameList(String string) {
        nameList.add(string);
    }

    public List<String> getCartNameList() {
        return nameList;
    }
}
//g
