import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello  World");

        Board board;

        try {
            board = new Board();
            System.out.println("Finished constructing Board");
            board.print();
        }
        catch (IOException e) {
            System.out.println("IOException in constructing Board.");
            return;
        }

        Panel panel = new Panel(board);
        panel.setFocusable(true);
        panel.addKeyListener(panel);

        JFrame frame = new JFrame("HiVolts");
        frame.setSize(720, 720);
        frame.setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
}


