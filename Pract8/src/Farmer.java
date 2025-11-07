import java.util.List;

class Farmer {
    private String name;

    public Farmer(String name) {
        this.name = name;
    }

    public void feedAnimals(List<Animal> animals) {
        System.out.println("\n" + name + " кормит животных...");
        for (Animal a : animals) {
            a.feed();
        }
    }

    public double collectProducts(List<Animal> animals) {
        System.out.println("\n" + name + " собирает продукцию...");
        double total = 0;
        for (Animal a : animals) {
            try {
                total += a.collectProduct();
            } catch (Exception e) {
                System.out.println("Ошибка при сборе продукции: " + e.getMessage());
            }
        }
        return total;
    }
}
