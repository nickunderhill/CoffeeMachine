package nick.underhill;

import java.util.Scanner;

public class CoffeeMachine {

    private final Scanner sc = new Scanner(System.in);
    private int waterAvailable;
    private int milkAvailable;
    private int coffeeAvailable;
    private int cupsAvailable;
    private int cashBox;

    public CoffeeMachine(int waterAvailable, int milkAvailable, int coffeeAvailable, int cupsAvailable, int cashBox) {
        this.waterAvailable = waterAvailable;
        this.milkAvailable = milkAvailable;
        this.coffeeAvailable = coffeeAvailable;
        this.cupsAvailable = cupsAvailable;
        this.cashBox = cashBox;
    }

    void takeMoney() {
        System.out.println("I gave you $" + cashBox);
        cashBox = 0;
    }

    void startCoffeeMachine() {
        System.out.println("Write action (1- buy, 2 - fill, 3 - take, 4 - remaining, 5 - exit): ");
        switch (sc.nextInt()) {
            case 1:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, " +
                        "3 - cappuccino, back - to main menu:");
                sellCoffee(sc.next());
                startCoffeeMachine();
                break;
            case 2:
                refillCoffeeMachine();
                startCoffeeMachine();
                break;
            case 3:
                takeMoney();
                startCoffeeMachine();
                break;
            case 4:
                System.exit(0);
                break;
            case 5:
                printStatus();
                startCoffeeMachine();
                break;
            default:
                System.out.println("Unknown command");
                startCoffeeMachine();
        }
    }

    void printStatus() {
        String message = "The coffee machine has:\n" +
                "%s of water\n" +
                "%s of milk\n" +
                "%s of coffee beans\n" +
                "%s of disposable cups\n" +
                "$%s of money";
        System.out.println(String.format(message,
                waterAvailable, milkAvailable, coffeeAvailable, cupsAvailable, cashBox));
    }

    void refillCoffeeMachine() {
        System.out.println("Write how many ml of water do you want to add:");
        waterAvailable += sc.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milkAvailable += sc.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeAvailable += sc.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cupsAvailable += sc.nextInt();
    }

    boolean makeCoffee(int water, int coffeeBeans, int price) {
        if (waterAvailable - water < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (coffeeAvailable - coffeeBeans < 0) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        } else if (cupsAvailable - 1 < 0) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        System.out.println("I have enough resources, making you a coffee!");
        waterAvailable -= water;
        coffeeAvailable -= coffeeBeans;
        cupsAvailable -= 1;
        cashBox += price;
        return true;
    }

    boolean makeCoffee(int water, int coffeeBeans, int price, int milk) {
        if(makeCoffee(water,coffeeBeans,price)) {
            if (milkAvailable - milk < 0) {
                System.out.println("Sorry, not enough milk!");
                return false;
            }
            milkAvailable -= milk;
            return true;
        }
        return false;
    }

    void sellCoffee(String drinkId) {
        switch (drinkId) {
            case "1":
                makeCoffee(250, 16, 4);
                break;
            case "2":
                makeCoffee(350, 20, 7, 75);
                break;
            case "3":
                makeCoffee(200, 12, 6, 100);
                break;
            case "back":
                startCoffeeMachine();
                break;
            default:
                System.out.println("No such coffee drink!");
                break;
        }
    }
}
