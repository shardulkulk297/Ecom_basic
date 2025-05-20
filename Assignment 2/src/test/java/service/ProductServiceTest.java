package service;

import exception.InvalidIdException;
import exception.NullDataException;
import model.Category;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    ProductService productService;

    @BeforeEach
    public void init(){
        productService = new ProductService();
    }

    @Test
    public void getProductsByCategoryTest(){

        InvalidIdException e = assertThrows(InvalidIdException.class, () -> productService.getProductsByCategory(0));
        assertEquals("Invalid Category Id", e.getMessage());

        e = assertThrows(InvalidIdException.class, () -> productService.getProductsByCategory(-1));
        assertEquals("Invalid Category Id", e.getMessage());

        assertDoesNotThrow(() -> productService.getProductsByCategory(1));
    }
    @Test
    public void addProductTest(){
        NullDataException e = assertThrows(NullDataException.class, () -> productService.addProduct(null, 1));
        assertEquals("Null Data Entered", e.getMessage());

        Category c = new Category(1, "Category");

        Product p = new Product(
                "Product", 500.00, "product xyz", c
        );


        InvalidIdException ee = assertThrows(InvalidIdException.class, ()-> productService.addProduct(p, 0));
        assertEquals("Invalid Category Id", ee.getMessage());

    }


}