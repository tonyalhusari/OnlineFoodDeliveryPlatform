package org.CPPFoodDelivery.decoratorFactory;

import org.CPPFoodDelivery.decoratorFactory.menu.meals.carbs.*;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.fats.Fats;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.proteins.Protein;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CarbsFactory extends MacronutrientsFactory {
    private static CarbsFactory instance = null;

    private CarbsFactory() {
    }

    public static CarbsFactory getInstance() {
        if (instance == null) {
            instance = new CarbsFactory();
        }
        return instance;
    }

    @Override
    public Carbs getCarbs(String dietPlan) {
        //generate random carbs
        Random rand = new Random();
        if (dietPlan.equalsIgnoreCase("Paleo")) {
            List<Carbs> carbsOptions = List.of(new Pistachio());
            return carbsOptions.get(rand.nextInt(carbsOptions.size()));
        } else if (dietPlan.equalsIgnoreCase("Vegan")) {
            List<Carbs> carbsOptions = Arrays.asList(new Bread(), new Lentils(), new Pistachio());
            return carbsOptions.get(rand.nextInt(carbsOptions.size()));
        } else if (dietPlan.equalsIgnoreCase("NutAllergy")) {
            List<Carbs> carbsOptions = Arrays.asList(new Cheese(), new Bread(), new Lentils());
            System.out.println(carbsOptions.size());
            return carbsOptions.get(rand.nextInt(carbsOptions.size()));
        } else if (dietPlan.equalsIgnoreCase("NoRestrictions")) {
            List<Carbs> carbsOptions = Arrays.asList(new Cheese(), new Bread(), new Lentils(), new Pistachio());
            return carbsOptions.get(rand.nextInt(carbsOptions.size()));

        }
        return null;

    }

    @Override
    public Protein getProtein(String dietPlan) {
        return null;
    }

    @Override
    public Fats getFats(String dietPlan) {
        return null;
    }
}
