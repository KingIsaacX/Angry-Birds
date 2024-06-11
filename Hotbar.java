import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * The Hotbar class represents a component in the game that displays a hotbar
 * image that will contain images of the birds.
 * It extends JComponent and is responsible for loading and rendering the hotbar
 * image.
 * 
 * @author Isaac Xiao
 * @version 6/9/2024
 */
public class Hotbar extends JComponent {
    /** The BufferedImage that holds the hotbar image. */
    private BufferedImage hotbar;

    /** The GamePanel associated with this hotbar. */
    private GamePanel gp;

    /**
     * Constructs a new Hotbar object and loads the hotbar image.
     * 
     * @param gp the GamePanel associated with this hotbar
     */
    public Hotbar(GamePanel gp) {
        URL hotbarURL = getClass().getResource("./images/miscellaneous/hotbar1.jpg");
        try {
            hotbar = ImageIO.read(hotbarURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the hotbar image.
     * 
     * @return the BufferedImage containing the hotbar image
     */
    public BufferedImage getImage() {
        return hotbar;
    }

    /**
     * Paints the hotbar image on the component.
     * 
     * @param g the Graphics object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(hotbar, 80, 540, 360, 50, gp);
    }

}
