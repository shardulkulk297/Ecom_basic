package dao;

import model.Customer;

public interface CustomerDao {

    public Customer getCustomerById(int customerId);
    public boolean insertCustomer(Customer customer);


}
