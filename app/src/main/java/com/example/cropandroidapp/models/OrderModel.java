package com.example.cropandroidapp.models;

public class OrderModel {

    int orderImage;
    String soldItemName, orderPrice ,orderNumber,orderQuantity;

    static String username;
    static String address;

    public OrderModel() {
    }

    public OrderModel(int orderImage, String soldItemImage, String orderPrice, String orderNumber) {
        this.orderImage = orderImage;
        this.soldItemName = soldItemImage;
        this.orderPrice = orderPrice;
        this.orderNumber = orderNumber;
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        OrderModel.username = username;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        OrderModel.address = address;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
