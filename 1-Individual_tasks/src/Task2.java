import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter integer: ");

        int number = sc.nextInt();

        if (number % 2 == 0) {
            System.out.print(number / 4);
        } else {
            System.out.print(number * 5);
        }
    }
}
