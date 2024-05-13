package org.CPPFoodDelivery.decoratorFactory;

import org.CPPFoodDelivery.decoratorFactory.menu.meals.carbs.Carbs;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.fats.Fats;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.proteins.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProteinFactory extends MacronutrientsFactory {
    private static ProteinFactory instance = null;

    private ProteinFactory() {
    }

    public static ProteinFactory getInstance() {
        if (instance == null) {
            instance = new ProteinFactory();
        }
        return instance;
    }

    @Override
    public Carbs getCarbs(String dietPlan) {
        return null;
    }

    @Override
    public Protein getProtein(String dietPlan) {
        //generate random protein
        Random rand = new Random();
        if (dietPlan.equalsIgnoreCase("Paleo")) {
            List<Protein> proteinOptions = Arrays.asList(new Beef(), new Chicken(), new Fish());
            return proteinOptions.get(rand.nextInt(proteinOptions.size()));
        } else if (dietPlan.equalsIgnoreCase("Vegan")) {
            List<Protein> proteinOptions = Arrays.asList(new Beef(), new Tofu());
            return proteinOptions.get(rand.nextInt(proteinOptions.size()));
        } else if (dietPlan.equalsIgnoreCase("NutAllergy")) {
            List<Protein> proteinOptions = Arrays.asList(new Beef(), new Chicken(), new Fish(), new Tofu());
            return proteinOptions.get(rand.nextInt(proteinOptions.size()));

        } else if (dietPlan.equalsIgnoreCase("NoRestrictions")) {
            List<Protein> proteinOptions = Arrays.asList(new Beef(), new Chicken(), new Fish(), new Tofu());
            return proteinOptions.get(rand.nextInt(proteinOptions.size()));

        }
        return null;
    }

    @Override
    public Fats getFats(String dietPlan) {
        return null;
    }
}
