package service;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import model.Customer;

public class CustomerService {

    public void addCustomer(Customer customer){
        CustomerDao customerDao = new CustomerDaoImpl();
        customerDao.insertCustomer(customer);
    }

}
