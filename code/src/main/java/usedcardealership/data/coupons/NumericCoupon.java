package usedcardealership.data.coupons;

public class NumericCoupon extends Coupon {
    private double discountAmount;

    /**
     * Constructor for NumericCoupon.
     * 
     * @param type the type of coupon (e.g., "Discount")
     * @param code the unique code for the coupon
     * @param discountAmount the amount to be discounted
     */
    public NumericCoupon(String code, double discountAmount) {
        super(code, discountAmount);
    }

    /**
     * Gets the discount amount for the coupon.
     * 
     * @return the discount amount
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the discount amount for the coupon.
     * 
     * @param discountAmount the amount to be discounted
     */
    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * Applies the discount coupon to the original price.
     * 
     * @param originalPrice the original price before applying the coupon
     * @return the price after the discount is applied
     */
    @Override
    public double applyCoupon(double originalPrice) {
        if (originalPrice < discountAmount) {
            return 0; // If the discount is larger than the original price, return 0
        }
        return originalPrice - discountAmount;
    }
}
