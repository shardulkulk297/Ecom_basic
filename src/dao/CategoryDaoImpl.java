package dao;

import exception.InvalidIdException;
import model.Category;
import model.Customer;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDaoImpl implements CategoryDao {

    DBConnection dbConnection = DBConnection.getInstance();

    @Override
    public Category getCategoryById(int categoryId){
        Category category = null;

        try{
            Connection con = dbConnection.getConnection();
            String sql = "Select * from category where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                con.close();
                return category;
            }
            else{
                throw new InvalidIdException("Invalid Category Id");
            }

        }

        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;

    }



}
