package structural;

import behavioral.Light;
import behavioral.MotionSensor;

/**
 * Facade — предоставляет упрощённый интерфейс управления умным домом.
 */
public class SmartHomeFacade {
    private final MotionSensor motionSensor;
    private final Light light;

    public SmartHomeFacade() {
        motionSensor = new MotionSensor("Living Room");
        light = new Light("Living Room");
        motionSensor.addListener(light);
    }

    public void simulateMotion() {
        motionSensor.detectMotion();
    }
}
