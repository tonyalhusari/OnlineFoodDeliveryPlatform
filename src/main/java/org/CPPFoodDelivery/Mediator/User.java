package org.CPPFoodDelivery.Mediator;

public interface User {

    void requestOrder(Restaurant restaurant, int hour, int minute);

    String getName();


}
