import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Panel extends JPanel implements KeyListener {

    private Board board;

    public Panel(Board b) {
        board = b;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.paintBoard(g);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        board.updateBoard(keyEvent.getKeyChar());
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
