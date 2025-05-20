package service;

import exception.NullDataException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    CustomerService customerService = new CustomerService();
    @org.junit.jupiter.api.Test
    void addCustomer() {

        NullDataException e = assertThrows(NullDataException.class, () -> customerService.addCustomer(null));
        assertEquals("Customer is null", e.getMessage());
    }


}