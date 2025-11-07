interface Payable {
    void processPayment(double amount);
}

interface Refundable {
    void processPayment(double amount);
}

class Order implements Payable, Refundable {
    private boolean isRefund;

    public Order(boolean isRefund) {
        this.isRefund = isRefund;
    }

    @Override
    public void processPayment(double amount) {
        if (isRefund) {
            System.out.println("Возврат суммы: " + amount + " руб.");
        } else {
            System.out.println("Оплата прошла: " + amount + " руб.");
        }
    }
}

public class T1 {
    public static void main(String[] args) {
        Order order1 = new Order(false);
        order1.processPayment(1000);

        Order order2 = new Order(true);
        order2.processPayment(500);
    }
}
