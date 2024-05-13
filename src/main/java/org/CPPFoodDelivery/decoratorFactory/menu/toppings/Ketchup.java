package org.CPPFoodDelivery.decoratorFactory.menu.toppings;

import org.CPPFoodDelivery.decoratorFactory.menu.meals.Meal;

public class Ketchup extends Topping {
    public Ketchup(Meal meal) {
        super(meal);
    }

    public String display() {
        return super.display() + "Ketchup";
    }
}
