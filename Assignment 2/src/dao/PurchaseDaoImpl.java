package dao;

import model.Purchase;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PurchaseDaoImpl implements PurchaseDao {

    @Override
    public void buyProduct(Purchase purchase) {
        DBConnection dbConnection = DBConnection.getInstance();

        try{
            Connection con = dbConnection.getConnection();
            String sql = "INSERT INTO purchase (date_of_purchase, customer_id, product_id, quantity, coupon_used, price_paid) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, purchase.getDateOfPurchase().toString());
            stmt.setInt(2, purchase.getCustomer().getId());
            stmt.setInt(3, purchase.getProduct().getId());
            stmt.setInt(4, purchase.getQuantity());
            stmt.setString(5, purchase.getCouponUsed());
            stmt.setDouble(6, purchase.getPrice_paid());

            int rowsAdded = stmt.executeUpdate();
            if(rowsAdded > 0){
                System.out.println("Product purchased Successfully");
            }
            else{
                System.out.println("Something went wrong");
            }
            stmt.close();


        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
