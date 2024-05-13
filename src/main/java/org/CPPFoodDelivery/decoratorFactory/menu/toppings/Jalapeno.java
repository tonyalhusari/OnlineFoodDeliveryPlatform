package org.CPPFoodDelivery.decoratorFactory.menu.toppings;

import org.CPPFoodDelivery.decoratorFactory.menu.meals.Meal;

public class Jalapeno extends Topping {
    public Jalapeno(Meal meal) {
        super(meal);
    }

    public String display() {
        return super.display() + "Jalapeno";
    }
}
