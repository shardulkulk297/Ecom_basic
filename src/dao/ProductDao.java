package dao;

import model.Product;

import java.util.List;

public interface ProductDao {

    public void insertProduct(Product product, int categoryId);
    public List<Product> getProductsByCategory(int categoryId);
    public Product getProductById(int productId);

}
