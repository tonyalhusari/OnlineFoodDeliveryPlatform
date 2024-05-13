package org.CPPFoodDelivery.Mediator;

import org.CPPFoodDelivery.County;
import org.CPPFoodDelivery.Diet;

public class Customer extends Person {
    private String name;
    private String address;

    private final County county;
    private final Diet dietPlan;

    private final CppFoodDelivery cppFoodDelivery;
    public Customer(String name, String address, String county,String dietPlan, CppFoodDelivery cppFoodDelivery) {
        super(name, address);
        this.county = County.valueOf(county);
        this.dietPlan = Diet.valueOf(dietPlan);
        this.cppFoodDelivery = cppFoodDelivery;
    }


    public Diet getDietPlan() {
        return dietPlan;
    }

    public void requestOrder(Restaurant restaurant, int hours, int minutes) {
        this.cppFoodDelivery.sendOrderToRestaurant(this, restaurant, hours, minutes);
    }

    public County getCounty() {
        return county;
    }
}
