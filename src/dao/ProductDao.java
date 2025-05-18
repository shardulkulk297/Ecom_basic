package dao;

import model.Product;

public interface ProductDao {

    public void insertProduct(Product product, int categoryId);
    public void getProductsByCategory(int categoryId);

}
