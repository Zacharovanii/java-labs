import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter your name: ");

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        String name = capitalize(userInput.trim());

        System.out.println("Hello, " + name + "!");
    }

    public static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}