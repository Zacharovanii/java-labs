import java.util.*;

public class BankApp {
    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>(Arrays.asList(
                new Client("Иванов",    "A001", 15000,  "Москва")   ,
                new Client("Петров",    "A002", -500,   "Ростов")   ,
                new Client("Сидоров",   "A003", 22000,  "Москва")   ,
                new Client("Кузнецов",  "A004", 0,      "Сочи")     ,
                new Client("Смирнов",   "A005", 43000,  "Ростов")   ,
                new Client("Федоров",   "A006", -1000,  "Казань")   ,
                new Client("Егоров",    "A007", 18000,  "Москва")   ,
                new Client("Беляев",    "A008", 7500,   "Сочи")     ,
                new Client("Громов",    "A009", 25000,  "Ростов")
        ));

        System.out.println("=== Исходный список клиентов ===");
        clients.forEach(System.out::println);

        clients.add(new Client("Новиков", "A010", 12000, "Москва"));
        clients.removeIf(c -> c.getName().equals("Кузнецов"));
        System.out.println("\nПосле добавления и удаления:");
        clients.forEach(System.out::println);

        clients.sort(Comparator.comparingDouble(Client::getBalance));
        System.out.println("\n=== Сортировка по балансу (возрастание) ===");
        clients.forEach(System.out::println);

        String cityFilter = "Москва";
        List<Client> moscowClients = clients.stream()
                .filter(c -> c.getCity().equalsIgnoreCase(cityFilter))
                .toList();
        System.out.println("\n=== Клиенты из города " + cityFilter + " ===");
        moscowClients.forEach(System.out::println);

        List<Client> debtors = clients.stream()
                .filter(c -> c.getBalance() < 0)
                .toList();
        System.out.println("\n=== Клиенты с отрицательным балансом ===");
        debtors.forEach(System.out::println);

        double totalBalance = clients.stream()
                .mapToDouble(Client::getBalance)
                .sum();
        System.out.printf("\nОбщий баланс всех клиентов: %.2f руб.\n", totalBalance);

        double avgBalance = clients.stream()
                .mapToDouble(Client::getBalance)
                .average()
                .orElse(0);
        System.out.printf("Средний баланс: %.2f руб.\n", avgBalance);
    }
}
