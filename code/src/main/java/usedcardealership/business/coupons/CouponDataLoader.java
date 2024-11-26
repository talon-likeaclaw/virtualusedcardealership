package usedcardealership.business.coupons;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class CouponDataLoader {
    private String couponFile;

    public CouponDataLoader(String couponFile){
        this.couponFile = couponFile;
    }
    
    public List<Coupon> loadCoupons() throws IOException{
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
