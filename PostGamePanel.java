import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.FontFormatException;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * The PostGamePanel class represents the panel displayed after a game is
 * completed,
 * showing the player's score, star rating, and providing options to restart the
 * game or go back to the map screen.
 * 
 * @author Isaac Xiao
 * @author William Dong
 * @author Arin Wani
 * @version 6/9/2024
 */
public class PostGamePanel extends JLayeredPane {
    /** The background image for the black panel. */
    private BufferedImage blackPanel;
    /** The background image for the blue panel. */
    private BufferedImage bluePanel;
    /** The image for the first star. */
    private BufferedImage star1;
    /** The image for the second star. */
    private BufferedImage star2;
    /** The image for the third star. */
    private BufferedImage star3;
    /** The JLabel displaying the player's score. */
    private JLabel scoreLabel;
    /** The button to return to the map screen. */
    private JButton mapButton;
    /** The button to restart the current level. */
    private JButton restartButton;
    /** The image for the map button. */
    private BufferedImage mapB;
    /** The image for the restart button. */
    private BufferedImage resB;
    /** The GamePanel instance associated with this PostGamePanel. */
    private GamePanel gp;
    /** A reference to this PostGamePanel instance. */
    private PostGamePanel pgp;

    /**
     * Constructs a new PostGamePanel with the specified score and maximum score,
     * and initializes its components, such as the star scoring system.
     *
     * @param maxScore the maximum possible score for a level
     * @param score    the player's score
     * @param gp       the GamePanel instance associated with this PostGamePanel
     */
    public PostGamePanel(int maxScore, int score, GamePanel gp) {
        pgp = this;
        mapButton = new JButton();
        restartButton = new JButton();
        this.gp = gp;
        URL black = getClass().getResource("images/ui_images/black_panel.png");
        URL blue = getClass().getResource("images/ui_images/blue_panel.png");

        try {
            blackPanel = ImageIO.read(black);
            bluePanel = ImageIO.read(blue);
            if (score > 2 * maxScore / 3) {
                star1 = ImageIO.read(getClass().getResource("images/miscellaneous/star1.png"));
                star2 = ImageIO.read(getClass().getResource("images/miscellaneous/star2.png"));
                star3 = ImageIO.read(getClass().getResource("images/miscellaneous/star3.png"));
            } else if (score > maxScore / 3) {
                star1 = ImageIO.read(getClass().getResource("images/miscellaneous/star1.png"));
                star2 = ImageIO.read(getClass().getResource("images/miscellaneous/star2.png"));
                star3 = ImageIO.read(getClass().getResource("images/miscellaneous/emptystar3.png"));
            } else if (score == 0) {
                star1 = ImageIO.read(getClass().getResource("images/miscellaneous/emptystar1.png"));
                star2 = ImageIO.read(getClass().getResource("images/miscellaneous/emptystar2.png"));
                star3 = ImageIO.read(getClass().getResource("images/miscellaneous/emptystar3.png"));
            } else {
                star1 = ImageIO.read(getClass().getResource("images/miscellaneous/star1.png"));
                star2 = ImageIO.read(getClass().getResource("images/miscellaneous/emptystar2.png"));
                star3 = ImageIO.read(getClass().getResource("images/miscellaneous/emptystar3.png"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(null);
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(320, 20, 500, 40);
        scoreLabel.setForeground(new Color(79, 79, 79));
        this.add(scoreLabel, BorderLayout.SOUTH);

        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("images/font/angrybirds-regular.TTF"))
                    .deriveFont(Font.PLAIN, 50);
            scoreLabel.setFont(customFont);
            mapB = ImageIO.read(getClass().getResource("images/miscellaneous/menu_button.png"));
            resB = ImageIO.read(getClass().getResource("images/miscellaneous/restart_button.png"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        this.setVisible(true);
        this.setBounds(220, 100, 800, 400);

        mapButton.setBounds(225, 300, 90, 90); // Set x, y, width, height
        mapButton.setOpaque(false);
        mapButton.setContentAreaFilled(false);
        mapButton.setBorderPainted(false);
        mapButton.setFocusable(false);

        restartButton.setBounds(485, 300, 90, 90); // Set x, y, width, height
        restartButton.setOpaque(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setBorderPainted(false);
        restartButton.setFocusable(false);

        this.add(mapButton);
        this.add(restartButton);

        mapButton.addActionListener(new ActionListener() {
            /**
             * Handles the action event for the map button, transitioning back to the map
             * screen.
             *
             * @param e the ActionEvent triggered by clicking the map button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                gp.resetMap();
                gp.remove(pgp);
                Main.cardLayout.show(Main.cardPanel, "MapScreen");
                BackgroundMusic.PlayMusic();
            }
        });

        restartButton.addActionListener(new ActionListener() {
            /**
             * Handles the action event for the restart button, resetting and restarting the
             * current level.
             *
             * @param e the ActionEvent triggered by clicking the restart button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                gp.resetMap();
                gp.makeMap(gp.getLevel());
                gp.remove(pgp);
                gp.revalidate();
                gp.setEnabled(true);
            }
        });
    }

    /**
     * Paints the component with Buffered Images, including the background panels
     * and stars.
     *
     * @param g the Graphics object to be used for rendering
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(blackPanel, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(bluePanel, 20, 20, getWidth() - 41, getHeight() - 41, this);
        g.drawImage(star1, 255, 70, 90, 90, this);
        g.drawImage(star2, 355, 70, 90, 90, this);
        g.drawImage(star3, 455, 70, 90, 90, this);
        g.drawImage(mapB, 225, 300, 90, 90, this);
        g.drawImage(resB, 485, 300, 90, 90, this);
    }

    /**
     * Returns the map button.
     *
     * @return the JButton representing the map button
     */
    public JButton getMapButton() {
        return mapButton;
    }

    /**
     * Returns the restart button.
     *
     * @return the JButton representing the restart button
     */
    public JButton getRestartButton() {
        return restartButton;
    }
}