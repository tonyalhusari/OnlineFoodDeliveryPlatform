package org.CPPFoodDelivery.decoratorFactory.menu.toppings;

import org.CPPFoodDelivery.decoratorFactory.menu.MenuItem;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.Meal;

public abstract class Topping implements MenuItem {
    protected Meal meal;

    public Topping(Meal meal) {
        this.meal = meal;
    }

    @Override
    public String display() {
        return meal.display() + " with Topping: ";
    }
}
