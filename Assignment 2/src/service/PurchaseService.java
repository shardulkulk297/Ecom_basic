package service;

import dao.*;
import enums.Coupon;
import exception.InvalidIdException;
import model.Customer;
import model.Product;
import model.Purchase;

import java.time.LocalDate;
import java.util.Scanner;

public class PurchaseService {

    public void purchaseProduct(int customerId, int productId, Scanner sc) throws InvalidIdException {

        if(customerId == 0 || customerId < 0){
            throw new InvalidIdException("Invalid Customer Id");
        }
        else if(productId == 0 || productId < 0){
            throw new InvalidIdException("Invalid Product Id");
        }
        else{
            System.out.println("Enter Quantity");
            int quantity = sc.nextInt();
            System.out.println("Do you have coupon code? (y/n)");
            String ans = sc.next();
            double discount = 0;
            String userCoupon = "";
            if(ans.equalsIgnoreCase("y"))
            {
                System.out.println("Enter Coupon Code: ");
                userCoupon = sc.next();

                Coupon coupon = Coupon.valueOf(userCoupon); //throws illegalArgumentException if coupon is invalid
                discount = coupon.getDiscount();
            }
            else{
                userCoupon = "No Coupon";
            }

            CustomerDao customerDao = new CustomerDaoImpl();
            ProductDao productDao = new ProductDaoImpl();

            Customer customer = customerDao.getCustomerById(customerId);
            Product product = productDao.getProductById(productId);

            double discountedPrice = product.getPrice() - (product.getPrice() * discount/100);
            double price_paid = discountedPrice * quantity;

            Purchase purchase = new Purchase();
            purchase.setCustomer(customer);
            purchase.setProduct(product);
            purchase.setDateOfPurchase(LocalDate.now());
            purchase.setQuantity(quantity);
            purchase.setCouponUsed(userCoupon);
            purchase.setPrice_paid(price_paid);

            PurchaseDao purchaseDao = new PurchaseDaoImpl();
            purchaseDao.buyProduct(purchase);
        }

    }

}
