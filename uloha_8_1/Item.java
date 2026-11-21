package com.example.uloha_8_1;

import java.math.BigDecimal;

public class Item {

    private String name;
    private int amount;
    private BigDecimal price;

    public Item(String name, int amount,
                       BigDecimal price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotal() {
        return price.multiply(
                BigDecimal.valueOf(amount)
        );
    }
}

