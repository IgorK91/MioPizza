package ru.korelyakov.miopizza.product;

import org.jetbrains.annotations.NotNull;

public enum ProductType {

    Pizza("Пицца"),
    Soup("Супы"),
    Calzone("Кальцоне"),
    Pasta("Пасты"),
    Salad("Салаты"),
    Drink("Напитки"),
    Dessert("Десерты");


    private final String description;

    ProductType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @NotNull
    @Override
    public String toString() {
        return getDescription();
    }
}
