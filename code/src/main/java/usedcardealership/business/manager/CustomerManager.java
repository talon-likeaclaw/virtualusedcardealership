/**
 * Manages all Customers, has a List<Customer>
 * 
 * @author Juan Sebastian Badel - 2338127
 * @version 11/4/2024
 * 
 * @version 11/11/2024
 */

package usedcardealership.business.manager;

import java.util.*;
import usedcardealership.data.customer.*;
import usedcardealership.data.vehicle.*;
import usedcardealership.interaction.*;
import usedcardealership.UsedCarDealership;

public class CustomerManager {

    private List<Customer> customerList;

    /**
     * Initializes the field customerList
     * 
     * @param customerList a List<Customer> with all customers
     */
    public CustomerManager(List<Customer> customerList) {
        if (customerList == null) {
            throw new IllegalArgumentException("Customer list cannot be null.");
        }
        this.customerList = customerList;
    }

    public List<Customer> getCustomers() {
        return this.customerList;
    }

    /**
     * Function updates the customerList List<Customer> to add a Customer object
     * 
     * @param customer a Customer object
     * @return void
     */
    public void addCustomer(Customer customer) {
        validateCustomerNull(customer);
        if (!this.customerList.contains(customer)) {
            this.customerList.add(customer);
        }
    }

    /**
     * Function checks if the customerList List<Customer> contains a Customer object
     * 
     * @param customer a Customer object
     * @return boolean
     */
    public boolean searchCustomer(Customer customer) {
        validateCustomerNull(customer);
        return this.customerList.contains(customer);
    }

    /**
     * Function updates a Cutomer's field
     * 
     * @param customer a Customer object
     * @return void
     */
    public void updateCustomer(Customer customer) {
        validateCustomerNull(customer);
        for (int i = 0; i < this.customerList.size(); i++) {
            if (this.customerList.get(i).equals(customer)) {
                this.customerList.set(i, new Customer(customer));
                break;
            }
        }
    }

    /**
     * Displays the account details of the current customer and allows navigation
     * back to the main menu.
     * 
     * @param dealer the dealership manager containing the current customer's
     *               information
     */
    public static void viewAccountView(DealershipManager dealer) {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            PrettyUtils.printYellow("Account Details:");
            System.out.println(dealer.getCurrentCustomer());
            List<Vehicle> vehicles = dealer.getCurrentCustomer().getVehicles();
            List<Integer> ids = new ArrayList<Integer>();
            PrettyUtils.printYellow("\nCurrent Vehicles:");
            for (int i = 0; i < vehicles.size(); i++) {
                System.out.println(vehicles.get(i));
                ids.add(vehicles.get(i).getID());
            }
            PrettyUtils.printYellow("\nWould you like to:");
            String menu = PrettyUtils.returnYellow("1:") + " Sell Vehicle\n" +
                    PrettyUtils.returnYellow("0:") + " Exit";
            int choice = Prompter.promptOption(menu, 1);
            if (choice == -1) {
                continue;
            }
            switch (choice) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    CustomerManager.sellVehicleView(dealer);
                default:
                    PrettyUtils.printRed("You may only select 0");
            }
        }
    }

    /**
     * Adds a vehicle to the shopping cart, removes it from available inventory,
     * and provides options for further actions.
     * 
     * @param dealer   the DealershipManager handling the dealership state.
     * @param vehicle  the Vehicle to be added to the shopping cart.
     * @param vehicles the list of available vehicles to update upon addition to the
     *                 cart.
     */
    public static void addVehicleToCart(DealershipManager dealer, Vehicle vehicle, List<Vehicle> vehicles) {
        PrettyUtils.wipe();
        dealer.getCurrentCart().addVehicle(vehicle);
        dealer.getVehicleManager().removeVehicle(vehicle);
        vehicles.remove(vehicle);

        PrettyUtils.printGreen("\nVehicle added to Shopping Cart");
        boolean inPage = true;
        while (inPage) {
            inPage = UsedCarDealership.checkoutPrompter(dealer);
        }
    }

    /**
     * Displays the shopping cart and allows the user to proceed to checkout or exit
     * the view.
     * 
     * @param dealer the DealershipManager handling the current dealership state.
     */
    public static void viewShoppingCart(DealershipManager dealer) {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            PrettyUtils.printYellow("Your Shopping Cart:");
            inPage = UsedCarDealership.checkoutPrompter(dealer);
        }
    }

    /**
     * Allows the current customer to view and sell their vehicles. The user can:
     * - View a list of owned vehicles.
     * - Select a vehicle by its ID to sell.
     * - Confirm whether to proceed with the sale.
     * 
     * @param dealer the dealership manager containing the current customer's
     *               vehicles
     */
    public static void sellVehicleView(DealershipManager dealer) {
        boolean inPage = true;
        while (inPage) {
            PrettyUtils.wipe();
            List<Vehicle> vehicles = dealer.getCurrentCustomer().getVehicles();
            List<Integer> ids = new ArrayList<Integer>();
            int choice;
            if (vehicles.size() > 0) {
                PrettyUtils.printYellow("Current Vehicles:");
                for (int i = 0; i < vehicles.size(); i++) {
                    System.out.println(vehicles.get(i));
                    ids.add(vehicles.get(i).getID());
                }
                PrettyUtils.printYellow("Would you like to:");
                String menu = PrettyUtils.returnYellow("1:") + " Select Vehicle to Sell\n" +
                        PrettyUtils.returnYellow("0:") + " Exit";
                choice = Prompter.promptOption(menu, 1);
            } else {
                PrettyUtils.printRed("You currently do not own any vehicles.");
                Prompter.promptEnter();
                choice = 0;
            }
            if (choice == -1) {
                // Invalid input; loop restarts automatically
                continue;
            }
            switch (choice) {
                case 0:
                    inPage = false;
                    break;
                case 1:
                    System.out.println("\nWhich vehicle will you sell to us?");
                    try {
                        int vehicleID = Prompter.promptVehicleId();
                        if (vehicleID == -1) {
                            Prompter.promptEnter();
                            break;
                        }
                        if (!(ids.contains(vehicleID))) {
                            PrettyUtils.printRed("\nInvalid Vehicle ID!");
                            Prompter.promptEnter();
                        } else {
                            PrettyUtils.wipe();
                            System.out.println("\nAre you sure you want to sell vehicle "
                                    + PrettyUtils.returnYellow("[" + vehicleID + "]") + "?");
                            boolean confirmed = Prompter.promptYesNo();
                            if (confirmed) {
                                dealer.manageVehicleSale(dealer, vehicleID);
                                Prompter.promptEnter();
                            } else {
                                PrettyUtils.printRed("\nVehicle selection cancelled.");
                                Prompter.promptEnter();
                            }
                        }
                        break;
                    } catch (NumberFormatException e) {
                        PrettyUtils.printRed("Invalid input! Please enter a numeric vehicle ID.");
                        Prompter.promptEnter();
                    } catch (IllegalArgumentException e) {
                        PrettyUtils.printRed(e.getMessage());
                        Prompter.promptEnter();
                    }
                default:
                    break;
            }
        }
    }

    /**
     * Validates that the customer parameter is not null
     * Throws exception if null
     * 
     * @param customer the customer to check
     */
    public void validateCustomerNull(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
    }
}