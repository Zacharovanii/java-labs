import javax.swing.*;
import java.awt.*;

public class SeaWaveApp extends JFrame {
    public SeaWaveApp() {
        setTitle("Морской прибой");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SeaModel model = new SeaModel();
        SeaPanel view = new SeaPanel(model);
        new SeaController(model, view);

        add(view, BorderLayout.CENTER);

        JLabel label = new JLabel("Нажимайте клавиши для смены цвета неба", SwingConstants.CENTER);
        add(label, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SeaWaveApp::new);
    }
}
