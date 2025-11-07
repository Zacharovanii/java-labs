public class Client {
    private String name;
    private String accountNumber;
    private double balance;
    private String city;

    public Client(String name, String accountNumber, double balance, String city) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.city = city;
    }

    public String getName() { return name; }
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getCity() { return city; }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %10.2f | %-10s", name, accountNumber, balance, city);
    }
}
