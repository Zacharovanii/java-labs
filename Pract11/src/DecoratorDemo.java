public class DecoratorDemo {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        System.out.println(coffee.getDescription() + " — " + coffee.getCost() + " руб.");

        Beverage milkCoffee = new MilkDecorator(coffee);
        System.out.println(milkCoffee.getDescription() + " — " + milkCoffee.getCost() + " руб.");

        Beverage deluxeCoffee = new CreamDecorator(new SyrupDecorator(new MilkDecorator(coffee)));
        System.out.println(deluxeCoffee.getDescription() + " — " + deluxeCoffee.getCost() + " руб.");
    }
}
