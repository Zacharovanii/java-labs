import controller.GameController;
import model.MapLoader;
import utils.ImageLoader;
import view.GameView;
import view.HUDView;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ImageLoader images = new ImageLoader();
                MapLoader mapLoader = new MapLoader(images, 32);

                // Создание UI
                GameView gameView = new GameView();
                HUDView hudView = new HUDView();

                // Создание контроллера
                GameController controller = new GameController(gameView, hudView, mapLoader);

                // Создание основного окна
                JFrame frame = new JFrame("Pacman");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

                frame.add(hudView);
                frame.add(gameView);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                // Добавление слушателей клавиш
                frame.addKeyListener(controller);
                gameView.addKeyListener(controller);
                hudView.addKeyListener(controller);

                frame.requestFocusInWindow();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error initializing game: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}