package org.CPPFoodDelivery.decoratorFactory;

import org.CPPFoodDelivery.decoratorFactory.menu.meals.carbs.Carbs;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.fats.*;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.proteins.Protein;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FatsFactory extends MacronutrientsFactory {
    private static FatsFactory instance = null;

    private FatsFactory() {
    }

    public static FatsFactory getInstance() {
        if (instance == null) {
            instance = new FatsFactory();
        }
        return instance;
    }

    @Override
    public Carbs getCarbs(String dietPlan) {
        return null;
    }

    @Override
    public Protein getProtein(String dietPlan) {
        return null;
    }

    @Override
    public Fats getFats(String dietPlan) {
        //generate random fats
        Random rand = new Random();
        if (dietPlan.equalsIgnoreCase("Paleo")) {
            List<Fats> fatsOptions = Arrays.asList(new Avocado(), new Tuna(), new Peanut());
            return fatsOptions.get(rand.nextInt(fatsOptions.size()));

        } else if (dietPlan.equalsIgnoreCase("Vegan")) {
            List<Fats> fatsOptions = Arrays.asList(new Avocado(), new Peanut());
            return fatsOptions.get(rand.nextInt(fatsOptions.size()));

        } else if (dietPlan.equalsIgnoreCase("NutAllergy")) {
            List<Fats> fatsOptions = Arrays.asList(new Avocado(), new SourCream(), new Tuna(), new Peanut());
            return fatsOptions.get(rand.nextInt(fatsOptions.size()));

        } else if (dietPlan.equalsIgnoreCase("NoRestrictions")) {
            List<Fats> fatsOptions = Arrays.asList(new Avocado(), new SourCream(), new Tuna());
            return fatsOptions.get(rand.nextInt(fatsOptions.size()));
        }
        return null;

    }
}
