package creational;

/**
 * Singleton — центральный контроллер системы.
 */
public class CentralController {
    private static CentralController instance;

    private CentralController() {}

    public static CentralController getInstance() {
        if (instance == null) {
            instance = new CentralController();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[CentralController] " + message);
    }
}
