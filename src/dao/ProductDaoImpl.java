package dao;

import model.Category;
import model.Product;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    DBConnection dbConnection = DBConnection.getInstance();
    @Override
    public void insertProduct(Product product, int categoryId){
        try{
            Connection con = dbConnection.getConnection();
            String sql = "INSERT INTO product (title, price, description, category_id) VALUES (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getTitle());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, categoryId);
            int rowsAdded = stmt.executeUpdate();
            if(rowsAdded > 0){
                System.out.println("Product Added Successfully");
            }
            else{
                System.out.println("Product Not Added");
            }
            stmt.close();
            con.close();


        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId){
        boolean flag = false;
        List<Product> products = new ArrayList<>();;

        try{
            Connection con = dbConnection.getConnection();
            String sql = "Select * from product where category_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                flag = true;
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("category_id"));
                product.setCategory(category);
                products.add(product);
            }

            if(flag){
                return products;
            }



        }
        catch (SQLException e){
            System.out.println(e.getMessage());

        }

        return products;
    }

    @Override
    public Product getProductById(int productId){
        Product product = null;

        try{
            Connection con = dbConnection.getConnection();
            String sql = "Select * from Product where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("category_id"));
                product.setCategory(category);
                con.close();
                return product;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return product;

    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        try{
            Connection con = dbConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from product");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setTitle(rs.getString("title"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("category_id"));
                product.setCategory(category);
                products.add(product);
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return products;
    }


}

