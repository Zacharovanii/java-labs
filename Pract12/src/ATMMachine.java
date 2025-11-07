public class ATMMachine {
    private final ATMState noCardState;
    private final ATMState hasCardState;
    private final ATMState pinEnteredState;
    private final ATMState noCashState;

    private ATMState currentState;
    private int cash = 10000;

    public ATMMachine() {
        noCardState = new NoCardState(this);
        hasCardState = new HasCardState(this);
        pinEnteredState = new PinEnteredState(this);
        noCashState = new NoCashState(this);

        currentState = noCardState;
    }

    public void setState(ATMState state) {
        this.currentState = state;
    }

    public void insertCard() {
        currentState.insertCard();
    }

    public void ejectCard() {
        currentState.ejectCard();
    }

    public void enterPin(int pin) {
        currentState.enterPin(pin);
    }

    public void requestCash(int amount) {
        currentState.requestCash(amount);
    }

    public ATMState getNoCardState() {
        return noCardState;
    }

    public ATMState getHasCardState() {
        return hasCardState;
    }

    public ATMState getPinEnteredState() {
        return pinEnteredState;
    }

    public ATMState getNoCashState() {
        return noCashState;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
