import java.util.Locale;
import java.util.Scanner;

public class Task1 {
    public static final double PI = 3.14;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        System.out.println("Variables format: 0.0 (double)");

        System.out.print("Enter radius: ");
        double R = sc.nextDouble();
        System.out.print("Enter height: ");
        double h = sc.nextDouble();

        System.out.print("Result: S = ");
        System.out.print(2 * PI * R * h);
    }
}
