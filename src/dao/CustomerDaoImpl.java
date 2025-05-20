package dao;

import exception.InvalidIdException;
import model.Customer;
import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    DBConnection dbConnection = DBConnection.getInstance();

    @Override
    public Customer getCustomerById(int customerId){
        List<Customer> customers = null;

        try{
            Connection con = dbConnection.getConnection();
            String sql = "Select * from customer where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, customerId);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setCity(rs.getString("city"));
                con.close();
                return customer;

            }
            else{
                throw new InvalidIdException("Invalid Customer Id");
            }

        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
    @Override
    public boolean insertCustomer(Customer customer){

        try{
            Connection con = dbConnection.getConnection();
            String sql = "INSERT INTO customer (name, city) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCity());

            int rowsAdded = stmt.executeUpdate();
            if(rowsAdded > 0){
                System.out.println("Customer Added Successfully");
                return true;
            }
            else{
                System.out.println("Customer Not Added");
                return false;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;


    }

}
