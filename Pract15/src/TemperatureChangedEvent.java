import java.util.EventObject;

class TemperatureChangedEvent extends EventObject {
    private final int newTemperature;

    public TemperatureChangedEvent(Object source, int newTemperature) {
        super(source);
        this.newTemperature = newTemperature;
    }

    public int getNewTemperature() {
        return newTemperature;
    }
}
