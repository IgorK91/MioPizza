package ru.korelyakov.miopizza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.product.ProductType;

public class Corzina implements Serializable {

    private List<Product> productList = new ArrayList<>();
    private List<Integer> coastList = new ArrayList<>();
    private List<Integer> countList = new ArrayList<>();
    private String name;
    private String number;
    private Integer countPosition;
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

    public void addToCart2(Integer integer) {
        coastList.add(integer);
    }

    public List<Integer> getItems2() {
        return coastList;
    }

    public void addToCart3(Integer integer) {
        countList.add(integer);
    }

    public List<Integer> getItems3() {
        return countList;
    }

    public void addToCart4(String name) {
        this.name = name;
    }

    public String getItems4() {
        return name;
    }

    public void addToCart5(String number) {
        this.number = number;
    }

    public String getItems5() {
        return number;
    }

    public void addToCart6(Integer countPosition) {
        this.countPosition = countPosition;
    }

    public Integer getItems6() {
        return countPosition;
    }

    public void addToCart7(String string) {
        nameList.add(string);
    }

    public List<String> getItems7() {
        return nameList;
    }
}
