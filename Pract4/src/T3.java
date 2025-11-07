interface Employee {
    double calculateSalary();
    String getRole();
}

abstract class BaseEmployee implements Employee {
    @Override
    public String getRole() {
        return "Employee";
    }
}

class Developer extends BaseEmployee {
    private double hourlyRate;
    private int hoursWorked;

    public Developer(double hourlyRate, int hoursWorked) {
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    @Override
    public String getRole() {
        return "Developer";
    }
}

public class T3 {
    public static void main(String[] args) {
        Developer dev = new Developer(1000, 160);
        System.out.println("Роль: " + dev.getRole());
        System.out.println("Зарплата: " + dev.calculateSalary() + " руб.");
    }
}
