package service;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import exception.NullDataException;
import model.Customer;

public class CustomerService {

    public boolean addCustomer(Customer customer){

        if(customer == null){
            throw new NullDataException("Customer is null");
        }


        CustomerDao customerDao = new CustomerDaoImpl();
        return customerDao.insertCustomer(customer);
    }

}
