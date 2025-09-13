public class Main {
    // ---------- КЛАСС КНИГА ----------
    static class Book {
        private String title;
        private String authorLastName;
        private String authorFirstName;
        private int year;
        private String publisher;
        private double costPrice;
        private double price;
        private double profit;

        // Конструктор
        public Book(String title, String authorLastName, String authorFirstName,
                    int year, String publisher,
                    double costPrice, double price) {
            this.title = title;
            this.authorLastName = authorLastName;
            this.authorFirstName = authorFirstName;
            this.year = year;
            this.publisher = publisher;
            this.costPrice = costPrice;
            this.price = price;
            this.profit = price - costPrice;
        }

        // Геттеры
        public String getTitle() { return title; }
        public String getAuthorLastName() { return authorLastName; }
        public String getAuthorFirstName() { return authorFirstName; }
        public int getYear() { return year; }
        public String getPublisher() { return publisher; }
        public double getCostPrice() { return costPrice; }
        public double getPrice() { return price; }
        public double getProfit() { return profit; }

        // Метод для печати информации
        public void printInfo() {
            System.out.println("Название: " + title +
                    ", Автор: " + authorLastName + " " + authorFirstName +
                    ", Год: " + year +
                    ", Издательство: " + publisher +
                    ", Себестоимость: " + costPrice +
                    ", Цена: " + price +
                    ", Прибыль: " + profit);
        }
    }

    // ---------- ОСНОВНОЙ МЕТОД ----------
    public static void main(String[] args) {
        // Создаем массив книг
        Book[] books = {
                new Book("Преступление и наказание", "Достоевский", "Фёдор", 2015, "Эксмо", 200, 550),
                new Book("Война и мир", "Толстой", "Лев", 2012, "АСТ", 300, 700),
                new Book("Мастер и Маргарита", "Булгаков", "Михаил", 2005, "Азбука", 150, 400),
                new Book("Метро 2033", "Глуховский", "Дмитрий", 2020, "Popcorn Books", 250, 600)
        };

        System.out.println("=== ВСЕ КНИГИ ===");
        for (Book b : books) {
            b.printInfo();
        }

        System.out.println("\n=== ВЫБОРКА: книги после 2010 года с прибылью > 300 ===");
        for (Book b : books) {
            if (b.getYear() > 2010 && b.getProfit() > 300) {
                b.printInfo();
            }
        }
    }
}
