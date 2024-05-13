package org.CPPFoodDelivery.decoratorFactory.menu.toppings;

import org.CPPFoodDelivery.decoratorFactory.menu.meals.Meal;

public class Fries extends Topping {
    public Fries(Meal meal) {
        super(meal);
    }

    public String display() {
        return super.display() + "Fries";
    }
}
