package nick.underhill;

public class App {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine =
                new CoffeeMachine(500,300,100,30, 50);
        coffeeMachine.startCoffeeMachine();
    }
}
