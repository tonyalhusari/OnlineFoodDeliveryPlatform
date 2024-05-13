package org.CPPFoodDelivery;

import org.CPPFoodDelivery.Mediator.*;
import org.CPPFoodDelivery.decoratorFactory.menu.MenuItem;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.Meal;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.carbs.Bread;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.fats.Tuna;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.proteins.Beef;
import org.CPPFoodDelivery.decoratorFactory.menu.toppings.Fries;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        CppFoodDelivery cppFoodDelivery = new CppFoodDelivery();
        Driver driver1 = new Driver("driver1", "1 driver Street", "Orange", 1, cppFoodDelivery);
        Driver driver2 = new Driver("driver2", "2 driver Street", "Orange", 2, cppFoodDelivery);
        Driver driver3 = new Driver("driver3", "3 driver Street", "Orange", 3, cppFoodDelivery);

        Driver driver4 = new Driver("driver4", "4 driver Street", "LA", 1, cppFoodDelivery);
        Driver driver5 = new Driver("driver5", "5 driver Street", "LA", 2, cppFoodDelivery);
        Driver driver6 = new Driver("driver6", "6 driver Street", "LA", 3, cppFoodDelivery);

        Driver driver7 = new Driver("driver7", "7 driver Street", "SanBernardino", 1, cppFoodDelivery);
        Driver driver8 = new Driver("driver8", "8 driver Street", "SanBernardino", 2, cppFoodDelivery);

        Customer customer1 = new Customer("customer1", "1 customer Street", "Orange", "Paleo", cppFoodDelivery);
        Customer customer2 = new Customer("customer2", "2 customer Street", "Orange", "NoRestrictions", cppFoodDelivery);
        Customer customer3 = new Customer("customer3", "3 customer Street", "Orange", "Vegan", cppFoodDelivery);

        Customer customer4 = new Customer("customer4", "4 customer Street", "LA", "Vegan", cppFoodDelivery);
        Customer customer5 = new Customer("customer5", "5 customer Street", "LA", "Paleo", cppFoodDelivery);
        Customer customer6 = new Customer("customer6", "6 customer Street", "LA", "NoRestrictions", cppFoodDelivery);

        Customer customer7 = new Customer("customer7", "7 customer Street", "SanBernardino", "Vegan", cppFoodDelivery);
        Customer customer8 = new Customer("customer8", "8 customer Street", "SanBernardino", "Vegan", cppFoodDelivery);
        Customer customer9 = new Customer("customer9", "9 customer Street", "SanBernardino", "NoRestrictions", cppFoodDelivery);
        Customer customer10 = new Customer("customer10", "10 customer Street", "SanBernardino", "NoRestrictions", cppFoodDelivery);

        Restaurant restaurant1 = new Restaurant("restaurant1", "1 restaurant Street", "Orange", "ITALIAN", 8, 23, cppFoodDelivery);
        Restaurant restaurant2 = new Restaurant("restaurant2", "2 restaurant Street", "LA", "MEXICAN", 8, 21, cppFoodDelivery);
        Restaurant restaurant3 = new Restaurant("restaurant3", "3 restaurant Street", "LA", "JAPANESE", 0, 9, cppFoodDelivery);
        Restaurant restaurant4 = new Restaurant("restaurant4", "4 restaurant Street", "SanBernardino", "CHINESE", 6, 23, cppFoodDelivery);

        cppFoodDelivery.registerDriver(driver1);
        cppFoodDelivery.registerDriver(driver2);
        cppFoodDelivery.registerDriver(driver3);
        cppFoodDelivery.registerDriver(driver4);
        cppFoodDelivery.registerDriver(driver5);
        cppFoodDelivery.registerDriver(driver6);
        cppFoodDelivery.registerDriver(driver7);
        cppFoodDelivery.registerDriver(driver8);

        cppFoodDelivery.registerCustomer(customer1);
        cppFoodDelivery.registerCustomer(customer2);
        cppFoodDelivery.registerCustomer(customer3);
        cppFoodDelivery.registerCustomer(customer4);
        cppFoodDelivery.registerCustomer(customer5);
        cppFoodDelivery.registerCustomer(customer6);
        cppFoodDelivery.registerCustomer(customer7);
        cppFoodDelivery.registerCustomer(customer8);
        cppFoodDelivery.registerCustomer(customer9);
        cppFoodDelivery.registerCustomer(customer10);

        cppFoodDelivery.registerRestaurant(restaurant1);
        cppFoodDelivery.registerRestaurant(restaurant2);
        cppFoodDelivery.registerRestaurant(restaurant3);
        cppFoodDelivery.registerRestaurant(restaurant4);

        // this order should be accepted by restaurant1 and delivered by driver2
        customer1.requestOrder(restaurant1, 17, 16);
        // this order should be accepted by restaurant1 and delivered by driver1
        customer2.requestOrder(restaurant1, 9, 17);
        // this order should be rejected by restaurant2 --> restaurant is closed
        customer3.requestOrder(restaurant1, 6, 18);

        // this order should be accepted by restaurant2 and delivered by driver4
        customer4.requestOrder(restaurant2, 11, 22);
        // this order should be accepted by restaurant2 and delivered by driver5
        customer5.requestOrder(restaurant2, 20, 33);
        // this order should be rejected by restaurant2 --> restaurant is closed
        customer6.requestOrder(restaurant2, 1, 41);

        // this order should be accepted by restaurant3 but not delivered (driver is not available)

        customer7.requestOrder(restaurant3, 3, 19);

        // this order should be accepted by restaurant4 and delivered by driver7
        customer8.requestOrder(restaurant4, 10, 16);
        // this order should be accepted by restaurant4 and delivered by driver8
        customer9.requestOrder(restaurant4, 19, 30);
        // this order should be accepted by restaurant4 and delivered by driver7
        customer10.requestOrder(restaurant4, 13, 18);

        // print all orders (orders not delivered or sent when restaurant is closed will not be printed it's not satisfied by the delivery app)
        System.out.println("printing all delivered orders");
        cppFoodDelivery.printDeliveredOrders();

    }
}