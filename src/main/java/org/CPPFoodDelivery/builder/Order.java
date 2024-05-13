package org.CPPFoodDelivery.builder;

import org.CPPFoodDelivery.Mediator.Customer;
import org.CPPFoodDelivery.Mediator.Driver;
import org.CPPFoodDelivery.Mediator.Restaurant;
import org.CPPFoodDelivery.decoratorFactory.menu.MenuItem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final Customer customer;
    private final Restaurant restaurant;

    private final List<MenuItem> menuItems;

    private final Timestamp orderTime;

    private final Timestamp pickupTime;
    private final Timestamp deliveryTime;
    private final Driver driver;
    public Order(Customer customer, Restaurant restaurant, List<MenuItem> menuItems, Timestamp orderTime, Timestamp pickupTime, Timestamp deliveryTime, Driver driver) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.menuItems = new ArrayList<>(menuItems);

        this.orderTime = orderTime;

        this.pickupTime = pickupTime;
        this.deliveryTime = deliveryTime;
        this.driver = driver;
    }

    public void printOrderDetails() {
        System.out.println("Order Details:");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Restaurant: " + restaurant.getName());
        System.out.println("*** Menu Items ***");
        printMenuItems();
        System.out.println("Order Time: " + orderTime);
        System.out.println("Pickup Time: " + pickupTime);
        System.out.println("Delivery Time: " + deliveryTime);
        System.out.println("Driver: " + driver.getName());
        System.out.println();
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }

    private void printMenuItems() {
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem.display());
        }
    }

}
