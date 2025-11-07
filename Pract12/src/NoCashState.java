public class NoCashState implements ATMState {
    private final ATMMachine atm;

    public NoCashState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("В банкомате нет денег.");
    }

    @Override
    public void ejectCard() {
        System.out.println("В банкомате нет денег.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("В банкомате нет денег.");
    }

    @Override
    public void requestCash(int amount) {
        System.out.println("В банкомате нет денег.");
    }
}
