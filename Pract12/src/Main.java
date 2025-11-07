public class Main {
    public static void main(String[] args) {
        ATMMachine atm = new ATMMachine();

        atm.insertCard();
        atm.enterPin(1234);
        atm.requestCash(5000);
        atm.ejectCard();

        atm.requestCash(1000);
    }
}