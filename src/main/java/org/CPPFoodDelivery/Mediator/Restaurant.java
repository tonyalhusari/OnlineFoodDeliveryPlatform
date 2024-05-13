package org.CPPFoodDelivery.Mediator;

import org.CPPFoodDelivery.County;
import org.CPPFoodDelivery.Cuisine;
import org.CPPFoodDelivery.Diet;
import org.CPPFoodDelivery.builder.Order;
import org.CPPFoodDelivery.builder.OrderBuilder;
import org.CPPFoodDelivery.decoratorFactory.FactoryCreator;
import org.CPPFoodDelivery.decoratorFactory.MacronutrientsFactory;
import org.CPPFoodDelivery.decoratorFactory.menu.MenuItem;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.Meal;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.carbs.Carbs;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.fats.Fats;
import org.CPPFoodDelivery.decoratorFactory.menu.meals.proteins.Protein;
import org.CPPFoodDelivery.decoratorFactory.menu.toppings.Fries;
import org.CPPFoodDelivery.decoratorFactory.menu.toppings.Jalapeno;
import org.CPPFoodDelivery.decoratorFactory.menu.toppings.Ketchup;

import java.sql.Timestamp;
import java.util.*;

public class Restaurant implements User {
    private final String name;
    private final String address;
    private final County county;
    private final Cuisine cuisine;

    private Timestamp openTime;
    private Timestamp closeTime;
    private final List<Meal> menu;

    private final CppFoodDelivery cppFoodDelivery;
    private static final Scanner scanner = new Scanner(System.in);
    private final List<Order> orderList;

    public Restaurant(String name, String address, String county, String cuisine, int openTime, int closeTime, CppFoodDelivery cppFoodDelivery) {
        this.name = name;
        this.address = address;
        this.county = County.valueOf(county);
        this.cuisine = Cuisine.valueOf(cuisine);
        setOpenTime(openTime);
        setCloseTime(closeTime);
        this.menu = new ArrayList<>();
        this.cppFoodDelivery = cppFoodDelivery;
        this.orderList = new ArrayList<>();
    }

    public List<Meal> getMenu() {
        return menu;
    }



    public void setOpenTime(int openTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, openTime);  // Set open hour
        calendar.set(Calendar.MINUTE, 0);       // Set a random minute always 0
        calendar.set(Calendar.SECOND, 0);       // Set a random second always 0
        calendar.set(Calendar.MILLISECOND, 0);  // Set a random millisecond always 0

        // Create a timestamp from the calendar instance
        Timestamp restaurantOpenTime = new Timestamp(calendar.getTimeInMillis());
        this.openTime = restaurantOpenTime;
    }

    public void setCloseTime(int closeTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, closeTime);  // Set close hour
        calendar.set(Calendar.MINUTE, 0);       // Set a random minute always 0
        calendar.set(Calendar.SECOND, 0);       // Set a random second always 0
        calendar.set(Calendar.MILLISECOND, 0);  // Set a random millisecond always 0

        // Create a timestamp from the calendar instance
        Timestamp restaurantCloseTime = new Timestamp(calendar.getTimeInMillis());
        this.closeTime = restaurantCloseTime;
    }

    public boolean isRestaurantOpen(Timestamp timestamp) {
        return timestamp.after(openTime) && timestamp.before(closeTime);
    }

    public void printMenu() {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println(menu.get(i).display());
        }
    }

    @Override
    public void requestOrder(Restaurant restaurant, int hour, int minute) {

    }

    @Override
    public String getName() {
        return name;
    }

    public void handleOrder(Customer customer, Timestamp orderCreationTime) {
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.setCustomer(customer);
        // add order creation time
        orderBuilder.setOrderTime(orderCreationTime);

        orderBuilder.setRestaurant(this);
        Diet dietPlan = customer.getDietPlan();

        // generate menu based on customer's diet plan
        setMenu(dietPlan);

        // add meal to order
        orderBuilder.setSelectedMeals(selectMeals(orderBuilder));

        // generate order pickup time
        orderBuilder.setOrderPickupTime(generateOrderPickupTime(orderCreationTime));

        // assign driver
        cppFoodDelivery.assignDriver(orderBuilder);

    }

    private Meal generateMeal(Diet dietPlan) {
        MacronutrientsFactory factoryCreator = FactoryCreator.getMacronutrientsFactory("Carbs");
        Carbs carbs = factoryCreator.getCarbs(dietPlan.name());

        factoryCreator = FactoryCreator.getMacronutrientsFactory("Protein");
        Protein protein = factoryCreator.getProtein(dietPlan.name());

        factoryCreator = FactoryCreator.getMacronutrientsFactory("Fats");
        Fats fats = factoryCreator.getFats(dietPlan.name());

        return new Meal(carbs, protein, fats);
    }

    public List<Meal> setMenu(Diet dietPlan) {
        // clear menu
        menu.clear();
        // generate menu based on customer's diet plan menu consist of 4 meals
        for (int i = 0; i < 4; i++) {
            menu.add(generateMeal(dietPlan));
        }
        return menu;
    }

    private Timestamp generateOrderPickupTime(Timestamp orderCreationTime) {
        // generate order pickup time based on order creation time add between 10 and 30 minutes
        Random rand = new Random();
        // Generate random number between 10 and 30
        int min = 10;
        int max = 30;
        int randomNum = rand.nextInt(max - min + 1) + min;

        // add random number of minutes to order creation time within 10 to 30
        Timestamp orderPickupTime = new Timestamp(orderCreationTime.getTime() + (randomNum * 60 * 1000));
        return orderPickupTime;
    }


    private List<MenuItem> selectMeals(OrderBuilder orderBuilder) {
        Scanner scanner = new Scanner(System.in);
        List<MenuItem> selectedMeals = new ArrayList<>();
        int choice;

        do {
            System.out.println("Available Meals According to Your Diet Plan "+ orderBuilder.getCustomer().getDietPlan() + " (select 0 to finish):");

            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ": " + menu.get(i).display());
            }
            System.out.println("0: Finish Selection");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            if (choice > 0 && choice <= menu.size()) {
                // add toppings
                MenuItem mealSelected = addToppings(menu.get(choice - 1));
                // add meal to order
                selectedMeals.add(mealSelected);
                System.out.println(mealSelected.display() + " ---> added to your selection.");

            } else if (choice != 0) {
                System.out.println("Invalid selection. Please try again.");
            }
        } while (choice != 0);
        System.out.println("-----------------------------\n");
        return selectedMeals;
    }

    private MenuItem addToppings(MenuItem meal) {
        System.out.println("would you like to add toppings to your meal?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Available Toppings:");
            System.out.println("1. Fries");
            System.out.println("2. Jalapeno");
            System.out.println("3. Ketchup");
            System.out.print("Enter your choice: ");
            int toppingChoice = scanner.nextInt();

            if (toppingChoice == 1) {
                meal = new Fries((Meal) meal);
            } else if (toppingChoice == 2) {
                meal = new Jalapeno((Meal) meal);
            } else if (toppingChoice == 3) {
                meal = new Ketchup((Meal) meal);
            }

            return meal;
        } else {
            return meal;
        }
    }

    public void addToOrderList(Order order) {
        orderList.add(order);
    }
}
