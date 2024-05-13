package org.CPPFoodDelivery.Mediator;

import org.CPPFoodDelivery.builder.Order;
import org.CPPFoodDelivery.builder.OrderBuilder;

public interface ICppFoodDelivery {

    void registerDriver(Driver driver);

    void registerCustomer(Customer customer);

    void registerRestaurant(Restaurant restaurant);

    void sendOrderToRestaurant(Customer customer, Restaurant restaurant, int hour, int minute);
    void assignDriver(OrderBuilder orderBuilder);

    void orderDelivered(Order order);

    void printDeliveredOrders();



}
