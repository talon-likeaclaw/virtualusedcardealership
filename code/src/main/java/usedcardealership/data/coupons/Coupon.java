package usedcardealership.data.coupons;

public abstract class Coupon {
    private String code;
    private double discount;

    /**
     * Parameterized constructor for Coupons.
     * 
     * @param type the type of coupon (e.g., discount, cashback)
     * @param code the unique code for the coupon
    */
    public Coupon(String code, double discount) {
        this.code = code;
        this.discount = discount;
    }

    public double getDiscount(){
        return this.discount;
    }

    public String getCode(){
        return this.code;
    }

    public abstract double applyCoupon(double originalPrice);

    /**
     * Provides a string representation of the coupon.
     * 
     * @return a string describing the coupon
     */
    @Override
    public String toString() {
        return "Coupon [Discount=" + discount + ", Code=" + code + "]";
    }
}
