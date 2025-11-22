import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class SeaController implements ActionListener, KeyListener {
    private final SeaModel model;
    private final SeaPanel view;
    private final Timer timer;
    private double speed = 0.1;

    public SeaController(SeaModel model, SeaPanel view) {
        this.model = model;
        this.view = view;

        timer = new Timer(30, this);
        timer.start();

        view.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.updatePhase(speed);
        view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) speed += 0.05;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) speed -= 0.05;
        else {
            model.changeSkyColor();
            System.out.println("Нажата кнопка: " + e.getKeyChar());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
