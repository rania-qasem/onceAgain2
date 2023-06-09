package com.example.onceagain;

public class Car {
    private String adId;
    private String userId;
    private String model, transmission, fuel, color, condition, kilometers, paint, body_Condition, payment_Method, price, description;


    public Car() {

    }

    public Car(String adId, String userId, String model,String transmission, String fuel, String color, String condition, String kilometers, String paint, String body_Condition, String payment_Method, String price, String description) {
        this.adId = adId;
        this.userId = userId;
        this.transmission = transmission;
        this.fuel = fuel;
        this.color = color;
        this.condition = condition;
        this.kilometers = kilometers;
        this.paint = paint;
        this.body_Condition = body_Condition;
        this.payment_Method = payment_Method;
        this.price = price;
        this.description = description;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getModel() {
        return userId;
    }

    public void setModel(String userId) {
        this.userId = userId;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getPaint() {
        return paint;
    }

    public void setPaint(String paint) {
        this.paint = paint;
    }

    public String getBody_Condition() {
        return body_Condition;
    }

    public void setBody_Condition(String body_Condition) {
        this.body_Condition = body_Condition;
    }

    public String getPayment_Method() {
        return payment_Method;
    }

    public void setPayment_Method(String payment_Method) {
        this.payment_Method = payment_Method;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
