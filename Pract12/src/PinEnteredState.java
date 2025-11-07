public class PinEnteredState implements ATMState {
    private final ATMMachine atm;

    public PinEnteredState(ATMMachine atm) {
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
        System.out.println("PIN уже введён.");
    }

    @Override
    public void requestCash(int amount) {
        if (amount > atm.getCash()) {
            System.out.println("Недостаточно средств.");
            ejectCard();
        } else {
            System.out.println("Выдано: " + amount + " руб.");
            atm.setCash(atm.getCash() - amount);
            if (atm.getCash() == 0) {
                atm.setState(atm.getNoCashState());
            }
            ejectCard();
        }
    }
}
