package usedcardealership.business.coupons;

public abstract class Coupons {
    private String type;
    private String code;

    public String getType(){
        return this.type;
    }

    public String getCode(){
        return this.code;
    }
}
