import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] arg) {
        System.out.println("Hello  World");

        Board b;

        try {
            b = new Board();
            System.out.println("Finished constructing Board");
            b.print();
        }
        catch (IOException e) {
            System.out.println("IOException in constructing Board.");
            return;
        }

        JFrame frame = new JFrame("HiVolts");
        frame.setSize(720, 720);
        frame.setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Panel(b));
        frame.setVisible(true);


    }
}
