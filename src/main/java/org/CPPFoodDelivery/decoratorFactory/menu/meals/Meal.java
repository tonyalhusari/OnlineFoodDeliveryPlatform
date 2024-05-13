package org.CPPFoodDelivery.decoratorFactory.menu.meals;

import org.CPPFoodDelivery.decoratorFactory.menu.MenuItem;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.carbs.Carbs;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.fats.Fats;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.proteins.Protein;

public class Meal implements MenuItem {

    private final Carbs carbs;
    private final Protein protein;
    private final Fats fats;

    public Meal(Carbs carbs, Protein protein, Fats fats) {
        this.carbs = carbs;
        this.protein = protein;
        this.fats = fats;
    }

    @Override
    public String display() {
        return "[Carbs: " + carbs.getCarbs() + ", Protein: " + protein.getProtein() + ", Fats: " + fats.getFats() + "]";
    }

}
