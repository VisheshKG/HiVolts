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
        if (board.playing) {
            super.paintComponent(g);
            board.paintBoard(g);
        }
        else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 720, 720);
            g.setColor(Color.RED);
            g.drawString("You Lose", 360, 360 );
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'r') {

        }
        else {
            board.updateBoard(keyEvent.getKeyChar());
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
