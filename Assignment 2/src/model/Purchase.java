package model;

import java.time.LocalDate;

public class Purchase {
    private int id;
    private LocalDate dateOfPurchase;
    private Customer customer;
    private Product product;
    private int quantity;
    private String couponUsed;
    private double price_paid;


    public Purchase() {

    }

    public Purchase(int id, LocalDate dateOfPurchase, Customer customer, Product product, int quantity, String couponUsed, double price_paid) {
        this.id = id;
        this.dateOfPurchase = dateOfPurchase;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.couponUsed = couponUsed;
        this.price_paid = price_paid;
    }

    public Purchase(LocalDate dateOfPurchase, Customer customer, Product product, int quantity, String couponUsed, double price_paid) {
        this.dateOfPurchase = dateOfPurchase;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.couponUsed = couponUsed;
        this.price_paid = price_paid;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }
    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getCouponUsed() {
        return couponUsed;
    }
    public void setCouponUsed(String couponUsed) {
        this.couponUsed = couponUsed;
    }
    public double getPrice_paid() {
        return price_paid;
    }
    public void setPrice_paid(double price_paid) {
        this.price_paid = price_paid;
    }
    @Override
    public String toString() {
        return product.getTitle();
    }



}
