import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Panel extends JPanel {

    public static BufferedImage fence;
    public static BufferedImage mho;
    public static BufferedImage player;

    public Panel() {
        super();
        try {
            fence = ImageIO.read(new File("Fence.png"));
            mho = ImageIO.read(new File("Mho.png"));
            player = ImageIO.read(new File("Player.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);



    }
}
