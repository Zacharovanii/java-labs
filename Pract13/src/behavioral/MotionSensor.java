package behavioral;

import creational.CentralController;
import java.util.ArrayList;
import java.util.List;

/**
 * MotionSensor — субъект (Subject), уведомляет наблюдателей о движении.
 */
public class MotionSensor {
    private final List<EventListener> listeners = new ArrayList<>();
    private final String location;

    public MotionSensor(String location) {
        this.location = location;
    }

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    public void detectMotion() {
        CentralController.getInstance().log("Motion detected in " + location);
        notifyAllListeners(new Event("MOTION", "Движение обнаружено в " + location));
    }

    private void notifyAllListeners(Event event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
