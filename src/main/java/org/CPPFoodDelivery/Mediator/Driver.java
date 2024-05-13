package org.CPPFoodDelivery.Mediator;

import org.CPPFoodDelivery.County;
import org.CPPFoodDelivery.builder.Order;
import org.CPPFoodDelivery.builder.OrderBuilder;

import java.sql.Timestamp;
import java.util.Random;

public class Driver extends Person {
    private final County operatingCounty;
    //driver working shift there is 3 shifts
    private final int workingShift;

    private final CppFoodDelivery cppFoodDelivery;

    public Driver(String name, String address, String county, int workingShift, CppFoodDelivery cppFoodDelivery) {
        super(name, address);
        this.workingShift = workingShift;
        this.operatingCounty = County.valueOf(county);
        this.cppFoodDelivery = cppFoodDelivery;
    }

    public boolean isAvailable(Timestamp timestamp) {
        if (timestamp.getHours() >= 8 &&timestamp.getHours() < 16 && workingShift == 1) {
            return true;
        }else if (timestamp.getHours() >= 16 && timestamp.getHours() < 25 && workingShift == 2) {
            return true;
        }else return timestamp.getHours() >= 0 && timestamp.getHours() < 8 && workingShift == 3;
    }
    @Override
    public void requestOrder( Restaurant restaurant, int hour, int minute) {

    }

    public County getOperatingCounty() {
        return operatingCounty;
    }

    public void deliverToCustomer(OrderBuilder orderBuilder) {
        Timestamp orderPickupTime = orderBuilder.getOrderPickupTime();
        Timestamp orderDeliveryTime = generateOrderDeliveryTime(orderPickupTime);
        orderBuilder.setOrderDeliveryTime(orderDeliveryTime);
        Order order = orderBuilder.build();
        order.printOrderDetails();
        cppFoodDelivery.orderDelivered(order);
    }
    private Timestamp generateOrderDeliveryTime(Timestamp orderPickupTime) {
        // generate order pickup time based on order creation time add between 10 and 30 minutes
        Random rand = new Random();
        // Generate random number between 10 and 30
        int min = 10;
        int max = 30;
        int randomNum = rand.nextInt(max - min + 1) + min;

        // add random number of minutes to order creation time within 10 to 30
        Timestamp orderDeliveryTime = new Timestamp(orderPickupTime.getTime() + (randomNum * 60 * 1000));
        return orderDeliveryTime;
    }
}
