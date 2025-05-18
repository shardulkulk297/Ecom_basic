package model;

import java.time.LocalDate;

public class Purchase {
    private int id;
    private LocalDate dateOfPurchase;
    private Customer customer;
    private Product product;
    private int quantity;

    public Purchase() {

    }

    public Purchase(int id, LocalDate dateOfPurchase, Customer customer, Product product, int quantity) {
        this.id = id;
        this.dateOfPurchase = dateOfPurchase;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public Purchase(LocalDate dateOfPurchase, Customer customer, Product product, int quantity) {
        this.dateOfPurchase = dateOfPurchase;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
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
    @Override
    public String toString() {
        return product.getTitle();
    }



}
