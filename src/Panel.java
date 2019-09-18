import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel {

    private Board board;

    public Panel(Board b) {
        board = b;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.paintBoard(g);
    }
}
