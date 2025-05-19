package dao;

import model.Vendor;

public interface VendorDao {

    public void addVendor(Vendor vendor);
    public Vendor getVendorById(int vendorId);

}
