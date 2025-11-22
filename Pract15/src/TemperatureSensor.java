import java.util.ArrayList;
import java.util.List;

class TemperatureSensor {
    private int temperature = 20;
    private final List<TemperatureChangeListener> listeners = new ArrayList<>();

    public void addTemperatureChangeListener(TemperatureChangeListener listener) {
        listeners.add(listener);
    }

    public void removeTemperatureChangeListener(TemperatureChangeListener listener) {
        listeners.remove(listener);
    }

    public void setTemperature(int newTemperature) {
        if (this.temperature != newTemperature) {
            this.temperature = newTemperature;
            fireTemperatureChangedEvent();
        }
    }

    public int getTemperature() {
        return temperature;
    }

    private void fireTemperatureChangedEvent() {
        TemperatureChangedEvent event = new TemperatureChangedEvent(this, temperature);
        for (TemperatureChangeListener listener : listeners) {
            listener.temperatureChanged(event);
        }
    }
}
