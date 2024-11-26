package usedcardealership.business.manager;

import java.util.*;

import usedcardealership.data.coupons.*;
import usedcardealership.interaction.PrettyUtils;

/**
 * Manages all Coupons in the system
 * 
 * @author Juan Sebastian Badel
 * @version 11/25/2024
 */
public class CouponManager {
    private List<Coupon> couponList;

    /**
     * Constructor to initialize the coupon list.
     * 
     * @param couponList a List<Coupon> containing all the available coupons
     */
    public CouponManager(List<Coupon> couponList) {
        if (couponList == null) {
            throw new IllegalArgumentException("Coupon list cannot be null.");
        }
        this.couponList = couponList;
    }

    /**
     * Get all available coupons
     * 
     * @return List<Coupon> a list of all coupons
     */
    public List<Coupon> getCoupons() {
        return this.couponList;
    }

    /**
     * Add a new coupon to the list
     * 
     * @param coupon a Coupon object to be added
     */
    public void addCoupon(Coupon coupon) {
        validateCouponNull(coupon);
        if (!this.couponList.contains(coupon)) {
            this.couponList.add(coupon);
        }
    }

    /**
     * Remove a coupon from the list
     * 
     * @param coupon a Coupon object to be removed
     */
    public void removeCoupon(Coupon coupon) {
        validateCouponNull(coupon);
        this.couponList.remove(coupon);
    }

    /**
     * Apply a coupon to a price and get the discounted price.
     * 
     * @param couponCode the code of the coupon to be applied
     * @param originalPrice the original price before the discount
     * @return double the final price after applying the discount
     */
    public double applyCoupon(String couponCode, double originalPrice) {
        for (Coupon coupon : this.couponList) {
            if (coupon.getCode().equals(couponCode)) {
                return coupon.applyCoupon(originalPrice);
            }
        }
        throw new IllegalArgumentException("Coupon code not found.");
    }

    /**
     * Validates that the coupon parameter is not null
     * Throws exception if null
     * 
     * @param coupon the coupon to check
     */
    public void validateCouponNull(Coupon coupon) {
        if (coupon == null) {
            throw new IllegalArgumentException("Coupon cannot be null.");
        }
    }


    public Coupon possibleCoupon() {
        Random random = new Random();
        double chance = random.nextDouble();
    
        if (chance <= 1) { // 25% chance
            if (!this.couponList.isEmpty()) {
                return this.couponList.get(random.nextInt(this.couponList.size()));
            } else {
                System.out.println(PrettyUtils.returnYellow("No coupons available at the moment."));
            }
        } else {
            System.out.println(PrettyUtils.returnYellow("No coupon this time. Better luck next time!"));
        }
        return null;
    }
}

