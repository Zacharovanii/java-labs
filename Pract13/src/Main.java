import creational.CentralController;
import structural.SmartHomeFacade;

/**
 * Демонстрация работы трёх паттернов:
 * Singleton (CentralController),
 * Facade (SmartHomeFacade),
 * Observer (MotionSensor -> Light).
 */
public class Main {
    public static void main(String[] args) {
        CentralController controller = CentralController.getInstance();
        controller.log("Smart Home System started.");

        SmartHomeFacade home = new SmartHomeFacade();
        home.simulateMotion(); // обнаружено движение, свет включается

        controller.log("Simulation finished.");
    }
}
