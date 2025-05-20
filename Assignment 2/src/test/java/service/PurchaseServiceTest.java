package service;

import exception.InvalidIdException;
import model.Category;
import model.Customer;
import model.Product;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseServiceTest {

    PurchaseService purchaseService;
    @BeforeEach
    public void init(){
        purchaseService = new PurchaseService();
    }


    @Test
    public void buyProductTest(){

        InvalidIdException e = assertThrows(InvalidIdException.class, () -> {
            Scanner sc = new Scanner(System.in);
            purchaseService.purchaseProduct(0, 0, sc);
            sc.close();
        });

        e = assertThrows(InvalidIdException.class, ()->{
            Scanner sc = new Scanner(System.in);
            purchaseService.purchaseProduct(-1, -1, sc);
            sc.close();
        });



    }

}