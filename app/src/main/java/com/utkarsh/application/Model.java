package com.utkarsh.application;

public class Model {
String date;
    String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPurchase_amt() {
        return purchase_amt;
    }

    public void setPurchase_amt(String purchase_amt) {
        this.purchase_amt = purchase_amt;
    }

    public String getSell_amt() {
        return sell_amt;
    }

    public void setSell_amt(String sell_amt) {
        this.sell_amt = sell_amt;
    }

    String product;
    String quantity;
    String purchase_amt;
    String sell_amt;
}
