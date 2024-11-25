package usedcardealership.business.coupons;

public abstract class Coupons {
    private String type;
    private String code;

    /**
     * Parameterized constructor for Coupons.
     * 
     * @param type the type of coupon (e.g., discount, cashback)
     * @param code the unique code for the coupon
    */
    public Coupons(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public String getType(){
        return this.type;
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
        return "Coupon [Type=" + type + ", Code=" + code + "]";
    }
}
