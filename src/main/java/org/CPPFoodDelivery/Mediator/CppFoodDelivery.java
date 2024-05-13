package org.CPPFoodDelivery.Mediator;

import org.CPPFoodDelivery.County;
import org.CPPFoodDelivery.builder.Order;
import org.CPPFoodDelivery.builder.OrderBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class CppFoodDelivery implements ICppFoodDelivery {

    private final List<Restaurant> restaurants;
    private final List<Driver> drivers;
    private final List<Customer> customers;

    private final List<Order> orders;
    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }



    public CppFoodDelivery() {
        customers = new ArrayList<>();
        restaurants = new ArrayList<>();
        drivers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    @Override
    public void registerDriver(Driver driver) {
        drivers.add(driver);
    }

    @Override
    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void registerRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    @Override
    public void sendOrderToRestaurant(Customer customer, Restaurant restaurant, int hour, int minute) {
        Timestamp orderCreationTime = generateOrderCreationTime(hour, minute);
        //check if restaurant is open to accept order
        if (restaurant.isRestaurantOpen(orderCreationTime)) {
            System.out.println(customer.getName() + " please select your meals");
            restaurant.handleOrder(customer, orderCreationTime);
        }else {
            rejectOrder(customer);
        }
    }

    private void rejectOrder(Customer customer) {
        System.out.println("sorry! " + customer.getName() + " Order rejected, restaurant is closed");
        System.out.println("----------------------------------");
    }


    public void assignDriver(OrderBuilder orderBuilder) {
        Timestamp orderPickupTime = orderBuilder.getOrderPickupTime();
        County county = orderBuilder.getCustomer().getCounty();
        boolean driverAvailable = false;
        for (Driver driver : drivers) {
            if (driver.isAvailable(orderPickupTime) && driver.getOperatingCounty() == county) {
                orderBuilder.setDriver(driver);
                driverAvailable = true;
                driver.deliverToCustomer(orderBuilder);
            }
        }
        if (!driverAvailable) {
            System.out.println("sorry! no driver available");
        }
    }

    public void orderDelivered(Order order) {
        orders.add(order);
        Restaurant restaurant = order.getRestaurant();
        restaurant.addToOrderList(order);
    }

    private Timestamp generateOrderCreationTime(int hour, int minute) {
        Random random = new Random();

        // Create a calendar instance
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);  // Set a random hour between 0 and 23
        calendar.set(Calendar.MINUTE, minute);       // Set a random minute between 0 and 59
        calendar.set(Calendar.SECOND, random.nextInt(60));       // Set a random second between 0 and 59
        // Create a timestamp from the calendar instance
        Timestamp randomTimestamp = new Timestamp(calendar.getTimeInMillis());
        return randomTimestamp;
    }


    public void printDeliveredOrders() {
        System.out.println("All delivered orders in CppFoodDelivery: ");
        System.out.println("----------------------------------");
        for (Order order : orders) {
            order.printOrderDetails();
        }
    }
}
