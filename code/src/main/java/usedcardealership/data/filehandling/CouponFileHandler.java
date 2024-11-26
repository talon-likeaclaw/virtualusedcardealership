package usedcardealership.data.filehandling;
import java.nio.file.*;
import java.io.*;
import java.util.*;

import usedcardealership.data.coupons.Coupon;
import usedcardealership.data.coupons.NumericCoupon;
import usedcardealership.data.coupons.PercentageCoupon;

public class CouponFileHandler {
    private String couponFile;

    public CouponFileHandler(String couponFile){
        this.couponFile = couponFile;
    }
    
    public List<Coupon> load() throws IOException{
        Path path = Paths.get(couponFile);
        List<Coupon> coupons = new ArrayList<Coupon>();
        List<String> lines = Files.readAllLines(path);

        for(String line : lines){
            String[] fields = line.split(",");
            if(fields.length == 3){
                String type = fields[0];
                String name = fields[1];
                double discount = Double.parseDouble(fields[2]);

                if(type.equals("Percentage")){
                    PercentageCoupon newCoupon = new PercentageCoupon(name, discount); 
                    coupons.add(newCoupon);
                } else if (type.equals("Numeric")){
                    NumericCoupon newCoupon = new NumericCoupon(name, discount);
                    coupons.add(newCoupon);
                }
            }
        }
        return coupons;
    }
}
