import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Product<T> {
    private String name;
    private T price;

    public Product(String name, T price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public T getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product<?> product)) return false;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + '}';
    }
}

class ProductManager {
    private Set<Product<Double>> products = new HashSet<>();

    public void addProduct(Product<Double> product) {
        products.add(product);
    }

    public Set<Product<Double>> findByPriceRange(double minPrice, double maxPrice) {
        Set<Product<Double>> result = new HashSet<>();
        for (Product<Double> product : products) {
            double price = product.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    public void showAll() {
        for (Product<Double> product : products) {
            System.out.println(product);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        manager.addProduct(new Product<>("Телефон", 799.99));
        manager.addProduct(new Product<>("Ноутбук", 1499.50));
        manager.addProduct(new Product<>("Наушники", 199.99));
        manager.addProduct(new Product<>("Мышь", 49.99));
        manager.addProduct(new Product<>("Монитор", 249.99));

        System.out.println("Все товары:");
        manager.showAll();

        System.out.println("\nТовары с ценой от 100 до 800:");
        Set<Product<Double>> found = manager.findByPriceRange(100, 800);
        for (Product<Double> p : found) {
            System.out.println(p);
        }
    }
}
