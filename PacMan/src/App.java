import controller.GameController;
import view.HUDView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class App extends JFrame {
    App() {
        super("PacMan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        GameController controller = new GameController();

        add(controller.getHUD(), BorderLayout.NORTH);
        add(controller.getViewPanel(), BorderLayout.CENTER);

        addKeyListener(controller);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
