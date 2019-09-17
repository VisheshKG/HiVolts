import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] arg) {
        //System.out.println("Hello  World");
        Board b = new Board();
        b.print();

        JFrame frame = new JFrame("HiVolts");
        frame.setSize(720, 720);
        frame.setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Panel());
        frame.setVisible(true);


        while (true) {

        }
    }
}
