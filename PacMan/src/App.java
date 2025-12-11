import controller.GameController;
import model.GameModel;
import model.MapLoader;
import model.MapModel;
import view.GameView;
import view.HUDView;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    App() {
        super("PacMan");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        MapModel model = MapLoader.load(GameModel.tileMap);
        GameView view = new GameView(model);
        HUDView hud = new HUDView();
        GameController controller = new GameController(model, view, hud);

        add(hud, BorderLayout.NORTH);
        add(view, BorderLayout.CENTER);

        addKeyListener(controller);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
