package behavioral;

import creational.CentralController;

/**
 * Light — наблюдатель (Observer), реагирует на события датчика.
 */
public class Light implements EventListener {
    private final String location;
    private boolean on = false;

    public Light(String location) {
        this.location = location;
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals("MOTION")) {
            turnOn();
        }
    }

    public void turnOn() {
        if (!on) {
            on = true;
            CentralController.getInstance().log("Light in " + location + " turned ON");
        }
    }
}
