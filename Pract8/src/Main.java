import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Farm farm = new Farm("Весёлый хутор");
            Farmer farmer = new Farmer("Иван");

            farm.addAnimal(new Cow("Мурка", 4));
            farm.addAnimal(new Chicken("Ряба", 2));
            farm.addAnimal(new Sheep("Белка", 3));
            farm.addAnimal(new Cow("Зорька", 5));

            farm.showAnimals();
            farm.sortByAge();
            farm.filterByType(Cow.class);

            farmer.feedAnimals(farm.getAnimals());
            double income = farmer.collectProducts(farm.getAnimals());
            farm.addProfit(income);

            farm.showProfit();

        } catch (Exception e) {
            System.out.println("Ошибка выполнения программы: " + e.getMessage());
        }
    }
}
