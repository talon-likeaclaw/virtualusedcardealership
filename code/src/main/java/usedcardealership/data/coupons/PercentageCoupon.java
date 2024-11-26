package usedcardealership.data.coupons;

public class PercentageCoupon extends Coupon {
    private double discountPercentage;
    /**
     * Constructor for PercentageCoupon.
     * 
     * @param type the type of coupon (e.g., "Percentage")
     * @param code the unique code for the coupon
     * @param discountPercentage the percentage discount to be applied
     */
    public PercentageCoupon(String code, double discountPercentage) {
        super(code, discountPercentage);
    }

    /**
     * Gets the discount percentage for the coupon.
     * 
     * @return the discount percentage
     */
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Applies the percentage coupon to the original price.
     * 
     * @param originalPrice the original price before applying the coupon
     * @return the price after the percentage discount is applied
     */
    @Override
    public double applyCoupon(double originalPrice) {
        if (discountPercentage <= 0) {
            return originalPrice; // No discount if the percentage is zero or negative
        }

        double discountAmount = (discountPercentage / 100) * originalPrice;
        return originalPrice - discountAmount;
    }
}
