package dao;

import enums.Role;
import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    DBConnection db = DBConnection.getInstance();
    @Override
    public void registerUser(User user) {

        try{

            Connection con = db.getConnection();
            String sql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());

            int rowsAdded = stmt.executeUpdate();
            if(rowsAdded > 0){
                System.out.println("User Registered Successfully");
            }
            else{
                System.out.println("Something went wrong");
            }
            stmt.close();
            con.close();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public boolean loginUser(String username, String password) {

        try{
            Connection con = db.getConnection();
            String sql = "Select * from user where username = ? and password = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return true;
            }
            else{
                return false;
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;

        try{
            Connection con = db.getConnection();
            String sql = "Select * from user where username = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                con.close();
                return user;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;

    }

    @Override
    public User getUserById(int userId){

        User user = null;

        try{
            Connection con = db.getConnection();
            String sql = "Select * from user where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                con.close();
                return user;
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }



}
