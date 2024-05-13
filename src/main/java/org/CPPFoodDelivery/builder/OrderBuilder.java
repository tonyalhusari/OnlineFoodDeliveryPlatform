package org.CPPFoodDelivery.builder;

import org.CPPFoodDelivery.Mediator.Customer;
import org.CPPFoodDelivery.Mediator.Driver;
import org.CPPFoodDelivery.Mediator.Restaurant;
import org.CPPFoodDelivery.decoratorFactory.menu.MenuItem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder implements Builder {
    private Customer customer;
    private Restaurant restaurant;

    private List<MenuItem> selectedMeals;

    private Timestamp orderTime;
    private Timestamp OderPickupTime;

    private Timestamp orderDeliveryTime;
    private Driver driver;

    @Override
    public Builder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public Builder setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public Builder setSelectedMeals(List<MenuItem> selectedMeal) {
        this.selectedMeals = new ArrayList<>();
       for (MenuItem meal : selectedMeal) {
           this.selectedMeals.add(meal);
       }
        return this;
    }

    public Builder setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public Builder setOrderPickupTime(Timestamp pickupTime) {
        this.OderPickupTime = pickupTime;
        return this;
    }

    public Builder setOrderDeliveryTime(Timestamp orderDeliveryTime) {
        this.orderDeliveryTime = orderDeliveryTime;
        return this;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Timestamp getOrderPickupTime() {
        return OderPickupTime;
    }

    public Builder setDriver(Driver driver) {
        this.driver = driver;
        return this;
    }
    @Override
    public Order build() {
        return new Order(customer, restaurant, selectedMeals, orderTime, OderPickupTime, orderDeliveryTime, driver);
    }



}
