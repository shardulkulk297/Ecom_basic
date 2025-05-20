package enums;

public enum Coupon {

    BLACKFRIDAY(20),
    SAVE10(10),
    SALE2025(25);

    private int discount;

    Coupon(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

}
