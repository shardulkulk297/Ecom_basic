package service;

import dao.VendorDao;
import dao.VendorDaoImpl;
import model.Vendor;

public class VendorService {

    public void addVendor(Vendor vendor){
        VendorDao vendorDao = new VendorDaoImpl();
        vendorDao.addVendor(vendor);
    }

}
