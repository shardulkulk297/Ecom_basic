package controller;

import dao.*;
import exception.InvalidIdException;
import model.Category;
import model.Product;
import service.ProductService;
import service.PurchaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);

        while(true){
            System.out.println("WELCOME TO E-COMMERCE");
            System.out.println("1. INSERT PRODUCT");
            System.out.println("2. FETCH ALL PRODUCTS BY CATEGORY");
            System.out.println("3. PURCHASE PRODUCT");
            System.out.println("0. EXIT");
            System.out.println("Enter Choice:");
            int choice = sc.nextInt();

            switch(choice){
                case 1->{
                    System.out.println("Enter Product Title: ");
                    String title = sc.next();
                    System.out.println("Enter Product Price: ");
                    double price = sc.nextDouble();
                    System.out.println("Enter Description of Product: ");
                    String description = sc.next();
                    System.out.println("Enter Product Category Id: ");
                    int categoryId = sc.nextInt();
                    Product product = new Product();
                    product.setTitle(title);
                    product.setPrice(price);
                    product.setDescription(description);
                    ProductService productService = new ProductService();
                    productService.addProduct(product, categoryId);
                }

                case 2->{

                    try{
                        System.out.println("Enter Category Id:");
                        int categoryId = sc.nextInt();
                        ProductService productService = new ProductService();
                        List<Product> products = new ArrayList<>();
                        products = productService.getProductsByCategory(categoryId);
                        for(Product product : products){
                            System.out.println(product);
                        }

                    }
                    catch(InvalidIdException e){
                        System.out.println(e.getMessage());
                    }



                }

                case 3->{
                    System.out.println("Enter Product Id: ");
                    int productId = sc.nextInt();
                    System.out.println("Enter Customer Id: ");
                    int customerId = sc.nextInt();
                    PurchaseService purchaseService = new PurchaseService();
                    purchaseService.purchaseProduct(customerId, productId, sc);
                }
            }

        }
    }

}
