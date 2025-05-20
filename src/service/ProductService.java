package service;

import dao.ProductDao;
import dao.ProductDaoImpl;
import exception.InvalidIdException;
import exception.NullDataException;
import model.Product;

import java.util.List;

public class ProductService {

    public void addProduct(Product product, int categoryId) throws InvalidIdException {
        if (categoryId == 0 || categoryId < 0)
            throw new InvalidIdException("Invalid Category Id");
        if(product ==  null){
            throw new NullDataException("Null Data Entered");
        }

        ProductDao productDao = new ProductDaoImpl();
        productDao.insertProduct(product, categoryId);
    }

    public List<Product> getProductsByCategory(int categoryId) throws InvalidIdException {
        if(categoryId == 0 || categoryId < 0){
            throw new InvalidIdException("Invalid Category Id");
        }
        ProductDao productDao = new ProductDaoImpl();
        return productDao.getProductsByCategory(categoryId);
    }


}
