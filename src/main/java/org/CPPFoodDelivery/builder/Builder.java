package org.CPPFoodDelivery.builder;

import org.CPPFoodDelivery.Mediator.Customer;
import org.CPPFoodDelivery.Mediator.Restaurant;

public interface Builder {
    Builder setCustomer(Customer customer);
    Builder setRestaurant(Restaurant restaurant);

    Order build();
}
