import javax.swing.*;

public class TemperatureMonitorApp extends JFrame {
    public TemperatureMonitorApp() {
        setTitle("Мониторинг температуры");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        TemperatureSensor model = new TemperatureSensor();
        TemperatureView view = new TemperatureView();
        new TemperatureController(model, view);

        add(view);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureMonitorApp::new);
    }
}
