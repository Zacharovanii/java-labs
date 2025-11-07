interface MathUtils {
    double PI = 3.14159;

    static double circleArea(double r) {
        return PI * r * r;
    }
}

class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void printArea() {
        System.out.println("Площадь круга: " + MathUtils.circleArea(radius));
    }
}

public class T5 {
    public static void main(String[] args) {
        Circle c1 = new Circle(3);
        Circle c2 = new Circle(5);

        c1.printArea();
        c2.printArea();
    }
}
