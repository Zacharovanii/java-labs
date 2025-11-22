import java.util.EventListener;

interface TemperatureChangeListener extends EventListener {
    void temperatureChanged(TemperatureChangedEvent event);
}
