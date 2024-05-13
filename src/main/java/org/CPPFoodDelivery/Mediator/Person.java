package org.CPPFoodDelivery.Mediator;

public abstract class Person implements User {

    private final String name;
    private final String address;



    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public String getName() {
        return name;
    }

}
