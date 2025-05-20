package controller;

import dao.*;
import enums.Role;
import exception.InvalidIdException;
import model.*;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        User user = new User();
        while(true){
            System.out.println("WELCOME TO E-COMMERCE");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.println("Enter Choice:");
            int input = sc.nextInt();

            switch(input){
                case 1->{
                    UserService userService = new UserService();
                    sc.nextLine();
                    System.out.println("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.println("Enter Password: ");
                    String password = sc.nextLine();
                    user = userService.loginUser(username, password);
                    if(user != null){
                        System.out.println("Login Successful");
                    }
                    else{
                        System.out.println("Invalid Username or Password");
                    }
                }
                case 2->{
                    UserService userService = new UserService();
                    sc.nextLine();
                    System.out.println("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.println("Enter Password: ");
                    String password = sc.nextLine();
                    System.out.println("Enter Role: (Customer/Vendor)");
                    String role = sc.nextLine();
                    user.setUsername(username);
                    user.setPassword(password);

                    try{
                        Role roleEnum = Role.valueOf(role.toUpperCase());
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }

                    user.setRole(role);
                    userService.registerUser(user);

                    if(user.getRole().toString().equalsIgnoreCase("CUSTOMER")){
                        System.out.println("Registering Customer...");
                        System.out.println("Enter Customer Name: ");
                        String name = sc.nextLine();
                        System.out.println("Enter city: ");
                        String city = sc.nextLine();
                        CustomerService customerService = new CustomerService();
                        Customer customer = new Customer(name, city, user);
                        customerService.addCustomer(customer);
                    }
                    else if(user.getRole().toString().equalsIgnoreCase("VENDOR")){
                        System.out.println("Registering Vendor...");
                        System.out.println("Enter Vendor Name: ");
                        String name = sc.nextLine();
                        System.out.println("Enter city: ");
                        String city = sc.nextLine();
                        VendorService vendorService = new VendorService();
                        Vendor vendor = new Vendor(name, city, user);
                        vendorService.addVendor(vendor);
                    }
                    else{
                        System.out.println("Invalid Role");
                    }

                }
                case 0->{
                    System.out.println("Exiting...");
                    return;
                }
                default->{
                    System.out.println("Invalid Choice");
                }
            }

            if(user.getRole().equalsIgnoreCase("CUSTOMER")){

                while(true){

                    System.out.println("Welcome " + user.getUsername() + " as Customer");
                    System.out.println("1. FETCH ALL PRODUCTS BY CATEGORY");
                    System.out.println("2. PURCHASE PRODUCT");
                    System.out.println("0. EXIT");
                    System.out.println("Enter Choice:");
                    int choice = sc.nextInt();

                    switch(choice){
                        case 1->{
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

                        case 2->{
                            ProductDao productDao = new ProductDaoImpl();
                            List<Product> products = productDao.getAllProducts();
                            for(Product product : products){
                                System.out.println(product);
                            }
                            System.out.println("Enter Product Id: ");
                            int productId = sc.nextInt();
                            System.out.println("Enter Customer Id: ");
                            int customerId = sc.nextInt();
                            PurchaseService purchaseService = new PurchaseService();
                            purchaseService.purchaseProduct(customerId, productId, sc);
                        }

                        case 0->{
                            System.out.println("Exiting...");
                            return;
                        }
                        default->{
                            System.out.println("Invalid Choice");
                        }

                    }


                }


            }

            else if(user.getRole().toString().equalsIgnoreCase("VENDOR")){

                while(true) {

                    System.out.println("Welcome " + user.getUsername() + " as Vendor");
                    System.out.println("1. INSERT PRODUCT");
                    System.out.println("2. FETCH ALL PRODUCTS BY CATEGORY");
                    System.out.println("0. EXIT");
                    System.out.println("Enter Choice:");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
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

                        case 2 -> {
                            ProductDao productDao = new ProductDaoImpl();
                            List<Product> products = productDao.getAllProducts();
                            for (Product product : products) {
                                System.out.println(product);
                            }
                            System.out.println("Enter Product Id: ");
                            int productId = sc.nextInt();
                            System.out.println("Enter Customer Id: ");
                            int customerId = sc.nextInt();
                            PurchaseService purchaseService = new PurchaseService();
                            purchaseService.purchaseProduct(customerId, productId, sc);
                        }

                        case 0 -> {
                            System.out.println("Exiting...");
                            return;
                        }
                        default -> {
                            System.out.println("Invalid Choice");
                        }
                    }
                }

            }










        }
    }

}
