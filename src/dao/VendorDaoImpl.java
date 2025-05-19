package dao;

import model.Vendor;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorDaoImpl implements VendorDao {
    DBConnection db = DBConnection.getInstance();
    @Override
    public void addVendor(Vendor vendor) {
        try{
            Connection con = db.getConnection();
            String sql = "INSERT INTO vendor (name, city) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, vendor.getName());
            stmt.setString(2, vendor.getCity());
            int rowsAdded = stmt.executeUpdate();
            if(rowsAdded > 0){
                System.out.println("Vendor Added Successfully");
            }
            else{
                System.out.println("Vendor Not Added");
            }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Vendor getVendorById(int vendorId) {

        try{
            Connection con = db.getConnection();
            String sql = "Select * from vendor where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, vendorId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Vendor vendor = new Vendor();
                vendor.setId(rs.getInt("id"));
                vendor.setName(rs.getString("name"));
                vendor.setCity(rs.getString("city"));
                return vendor;
            }

            else{
                System.out.println("Vendor Not Found");
                return null;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}
