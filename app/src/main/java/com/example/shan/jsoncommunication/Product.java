package com.example.shan.jsoncommunication;

/**
 * Created by shan on 7/25/18.
 */

public class Product {
    public String productId;
    public String productName;
    public int quantity;

    public Product() {
        // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    }

    public Product(String productId, String productName, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

}
