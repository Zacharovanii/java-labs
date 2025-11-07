public class NoCardState implements ATMState {
    private final ATMMachine atm;

    public NoCardState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Карта вставлена.");
        atm.setState(atm.getHasCardState());
    }

    @Override
    public void ejectCard() {
        System.out.println("Карта не вставлена.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Сначала вставьте карту.");
    }

    @Override
    public void requestCash(int amount) {
        System.out.println("Сначала вставьте карту.");
    }
}
