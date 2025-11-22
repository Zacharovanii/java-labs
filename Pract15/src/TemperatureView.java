import javax.swing.*;
import java.awt.*;

class TemperatureView extends JPanel {
    private final JLabel tempLabel = new JLabel("Температура: 20 °C", SwingConstants.CENTER);
    private final JLabel statusLabel = new JLabel("Температура в норме", SwingConstants.CENTER);
    private final JButton incButton = new JButton("Повысить");
    private final JButton decButton = new JButton("Понизить");
    private final JButton resetButton = new JButton("Сброс");

    public TemperatureView() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(230, 240, 255));
        tempLabel.setFont(new Font("Arial", Font.BOLD, 36));
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(incButton);
        buttonPanel.add(decButton);
        buttonPanel.add(resetButton);

        add(statusLabel, BorderLayout.NORTH);
        add(tempLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateTemperatureDisplay(int temperature, boolean overheated, boolean overcooled) {
        tempLabel.setText("Температура: " + temperature + " °C");
        if (overheated) {
            statusLabel.setText("Перегрев!");
            setBackground(new Color(255, 180, 180));
        } else if (overcooled) {
            statusLabel.setText("Недогрев :(");
            setBackground(new Color(137, 183, 250));
        } else {
            statusLabel.setText("Температура в норме");
            setBackground(new Color(230, 240, 255));
        }
    }

    // Геттеры для контроллера
    public JButton getIncButton() {
        return incButton;
    }

    public JButton getDecButton() {
        return decButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }
}
