import java.util.*;

@FunctionalInterface
interface Processor<T> {
    void process(T obj);
}

class Laptop {
    String brand;
    int ram;
    double price;

    public Laptop(String brand, int ram, double price) {
        this.brand = brand;
        this.ram = ram;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Brand: %s | RAM: %dGB | Price: %.2f$", brand, ram, price);
    }
}

public class Main {
    public static void main(String[] args) {
        Laptop[] laptops = {
                new Laptop("Dell", 8, 700),
                new Laptop("HP", 16, 900),
                new Laptop("Lenovo", 16, 850),
                new Laptop("Apple", 32, 2500),
                new Laptop("Asus", 16, 780)
        };

        Processor<Laptop> printer = l -> System.out.println(l);

        List<Laptop> filtered = Arrays.stream(laptops)
                .filter(l -> l.ram >= 16)
                .sorted(Comparator.comparingDouble(l -> l.price))
                .limit(2)
                .toList();

        System.out.println("Ноутбуки с RAM >= 16 GB (2 самых дешёвых):");
        filtered.forEach(printer::process);
    }
}
