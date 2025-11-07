import java.util.*;

interface IBook {
    String getFullInfo();
    double getProfit();
    boolean isProfitable(double minProfit);
}

class Book implements IBook {
    private String title;
    private String authorLast;
    private String authorFirst;
    private int year;
    private String publisher;
    private double costPrice;
    private double price;
    private double profit;

    public Book(String title, String authorLast, String authorFirst, int year,
                String publisher, double costPrice, double price, double profit) {
        this.title = title;
        this.authorLast = authorLast;
        this.authorFirst = authorFirst;
        this.year = year;
        this.publisher = publisher;
        this.costPrice = costPrice;
        this.price = price;
        this.profit = profit;
    }

    @Override
    public String getFullInfo() {
        return "Название: " + title + "\n" +
                "Автор: " + authorFirst + " " + authorLast + "\n" +
                "Год выхода: " + year + "\n" +
                "Издательство: " + publisher + "\n" +
                "Себестоимость: " + costPrice + " руб.\n" +
                "Цена: " + price + " руб.\n" +
                "Прибыль: " + profit + " руб.\n";
    }

    @Override
    public double getProfit() {
        return profit;
    }

    @Override
    public boolean isProfitable(double minProfit) {
        return profit >= minProfit;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Война и мир", "Толстой", "Лев", 1869, "Русский вестник", 150, 500, 350));
        books.add(new Book("Преступление и наказание", "Достоевский", "Фёдор", 1866, "Русский вестник", 120, 450, 330));
        books.add(new Book("Мастер и Маргарита", "Булгаков", "Михаил", 1967, "Москва", 200, 600, 400));
        books.add(new Book("Евгений Онегин", "Пушкин", "Александр", 1833, "Санкт-Петербург", 100, 300, 200));

        System.out.println("--- Все книги ---");
        for (Book book : books) {
            System.out.println(book.getFullInfo());
        }

        System.out.println("--- Книги с прибылью больше 300 руб. ---");
        for (Book book : books) {
            if (book.isProfitable(300)) {
                System.out.println(book.getFullInfo());
            }
        }
    }
}
