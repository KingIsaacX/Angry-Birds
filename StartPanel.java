import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The StartPanel class represents the initial screen displayed when the game is
 * run.
 * It includes a background image and a start button to transition to the next
 * screen.
 * 
 * @author Arin Wani
 * @author Isaac Xiao
 * @version 6/9/2024
 */
public class StartPanel extends JPanel {

    /** The background image for the start screen. */
    private BufferedImage background;
    /** The button to start the game. */
    private JButton startButton;

    /**
     * Constructs a new StartPanel, initializes its components, and sets up the
     * start button.
     */
    public StartPanel() {
        try {
            background = ImageIO.read(getClass().getResource("/images/ui_Images/startScreen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setLayout(null);
        startButton = new JButton();
        startButton.setBounds(460, 390, 375, 160); // Set x, y, width, height
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusable(false);
        this.add(startButton);
        startButton.addActionListener(new ActionListener() {
            /**
             * Handles the action event for the start button, transitioning to the next
             * screen.
             *
             * @param e the ActionEvent triggered by clicking the start button
             */
            public void actionPerformed(ActionEvent e) {
                Main.cardLayout.next(Main.cardPanel);
            }
        });

        BackgroundMusic.PlayMusic();
    }

    /**
     * Paints the component with the background image.
     *
     * @param g the Graphics object to be used for rendering
     */
    public void paintComponent(Graphics g) {

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Returns the start button.
     *
     * @return the JButton representing the start button
     */
    public JButton getButton() {
        return startButton;
    }
}
