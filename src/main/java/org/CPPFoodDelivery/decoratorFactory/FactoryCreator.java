package org.CPPFoodDelivery.decoratorFactory;

public class FactoryCreator {
    public static MacronutrientsFactory getMacronutrientsFactory(String type) {
        return switch (type) {
            case "Carbs" -> CarbsFactory.getInstance();
            case "Protein" -> ProteinFactory.getInstance();
            case "Fats" -> FatsFactory.getInstance();
            default -> null;
        };
    }
}
