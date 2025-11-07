import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoveImageExample extends JFrame {
    private JLabel imageLabel;
    private JButton centerButton;
    private JPanel mainPanel;
    private int startX, startY;

    public MoveImageExample() {
        super("Практическая работа №14 — Перемещение изображения");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null);

        ImageIcon icon = new ImageIcon(new ImageIcon("C:\\Users\\evilh\\OneDrive\\Pictures\\ОБОИ.jpg").getImage()
                .getScaledInstance(80, 80, Image.SCALE_SMOOTH));

        imageLabel = new JLabel(icon);
        startX = 250;
        startY = 120;
        imageLabel.setBounds(startX, startY, 80, 80);
        add(imageLabel);

        centerButton = new JButton("В центр");
        centerButton.setBounds(240, 300, 120, 30);
        add(centerButton);

        mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, 600, 400);
        add(mainPanel);

        centerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageLabel.setLocation(startX, startY);
            }
        });

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() - imageLabel.getWidth() / 2;
                int y = e.getY() - imageLabel.getHeight() / 2;
                System.out.print("x: ");
                System.out.println(x);
                System.out.print("y: ");
                System.out.println(y);
                imageLabel.setLocation(x, y);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setTitle("Курсор в окне");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setTitle("Курсор вне окна");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MoveImageExample::new);
    }
}
