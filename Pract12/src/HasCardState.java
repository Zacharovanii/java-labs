public class HasCardState implements ATMState {
    private final ATMMachine atm;

    public HasCardState(ATMMachine atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Карта уже вставлена.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Карта извлечена.");
        atm.setState(atm.getNoCardState());
    }

    @Override
    public void enterPin(int pin) {
        if (pin == 1234) {
            System.out.println("PIN верный.");
            atm.setState(atm.getPinEnteredState());
        } else {
            System.out.println("Неверный PIN.");
            ejectCard();
        }
    }

    @Override
    public void requestCash(int amount) {
        System.out.println("Сначала введите PIN.");
    }
}
