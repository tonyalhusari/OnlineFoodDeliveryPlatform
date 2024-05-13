package org.CPPFoodDelivery.decoratorFactory;

import org.CPPFoodDelivery.decoratorFactory.menu.meals.carbs.Carbs;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.fats.Fats;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.proteins.Protein;

public abstract class MacronutrientsFactory {

    public abstract Carbs getCarbs(String dietPlan);

    public abstract Protein getProtein(String dietPlan);

    public abstract Fats getFats(String dietPlan);
}
