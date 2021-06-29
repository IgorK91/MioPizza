package ru.korelyakov.miopizza.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.product.ProductType;

public class Cart implements Serializable {

    private List<Product> productList = new ArrayList<>();
    private String name;
    private String number;

    public void addToCart(Product product) {
        productList.add(product);
    }

    public void removeFromCart(Product product) {
        productList.remove(product);
    }

    public void cleanCart() {
        productList.clear();
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

    public String getAllNames() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product:
                productList) {
            stringBuilder.append(product.getName());
        }
        return stringBuilder.toString();
    }

    public String getAllCount() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product:
                productList) {
            stringBuilder.append(product.getCount());
        }
        return stringBuilder.toString();
    }

    /**
     * Общая цена всех продуктов в корзине
     * @return
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product:
             productList) {
            totalPrice += product.getFullPrice();
        }
        return totalPrice;
    }

    /**
     * Общее количество всех продуктов в корзине
     * @return
     */
    public int getTotalCount() {
        int totalCount = 0;
        for (Product product:
                productList) {
            totalCount += product.getCount();
        }
        return totalCount;
    }
}
//g
