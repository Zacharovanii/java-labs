import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Farm {
    private String name;
    private List<Animal> animals = new ArrayList<>();
    private double profit = 0;

    public Farm(String name) {
        this.name = name;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(String name) {
        animals.removeIf(a -> a.name.equalsIgnoreCase(name));
    }

    public void showAnimals() {
        System.out.println("\nЖивотные на ферме " + name + ":");
        animals.forEach(System.out::println);
    }

    public void sortByAge() {
        animals.sort(Comparator.comparingInt(a -> a.age));
        System.out.println("\nСортировка животных по возрасту:");
        showAnimals();
    }

    public void filterByType(Class<?> type) {
        System.out.println("\nФильтр: " + type.getSimpleName());
        animals.stream()
                .filter(type::isInstance)
                .forEach(System.out::println);
    }

    public void addProfit(double amount) {
        profit += amount;
    }

    public void showProfit() {
        System.out.printf("\nОбщая прибыль фермы: %.2f руб.\n", profit);
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
