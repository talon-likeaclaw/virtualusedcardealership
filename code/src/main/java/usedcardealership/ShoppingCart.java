/**
 * Shopping Cart, holds Vehicles customer wishes to buy;
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/6/2024
 */
package usedcardealership;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Vehicles> productsList;

    public ShoppingCart(){
        this.productsList = new ArrayList<Vehicles>();
    }
    /**
     * Adds a vehicle taken as input to productsList
     * 
     * @param vehicle
     * @return void
     */
    public void addVehicle(Vehicle vehicle){
        this.productsList.add(new Vehicle(vehicle));
    }
    /**
     * Removes vehicle from the productsList at selected index
     * 
     * @return void
     */
    public void removeVehicle(int index){
        this.productsList.remove(index);
    }
    /**
     * Removes every element from the productsList
     * 
     * @return void
     */
    public void emptyCart(){
        for(int i = 0; i < this.productsList.size(); i++){
            this.productsList.remove(i);
        }
    }
}
