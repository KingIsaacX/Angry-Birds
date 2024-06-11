import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The MapPanel class represents a panel where users can select different maps
 * to play in the Angry Birds game.
 * It displays map selection buttons and handles user interaction with these
 * buttons.
 * 
 * @author Arin Wani
 * @author Isaac Xiao
 * @author Willaim Dong
 * @version 6/9/2024
 */

public class MapPanel extends JPanel implements ActionListener {

    /** The background image for the map selection screen. */
    private BufferedImage background;
    /** A list of buttons for selecting different maps. */
    private ArrayList<JButton> mapButtons;

    /**
     * Constructs a new MapPanel object, initializes the background image, and adds
     * map selection buttons.
     */
    public MapPanel() {
        try {
            background = ImageIO.read(getClass().getResource("/images/ui_Images/animatedLevelSelect.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapButtons = new ArrayList<JButton>();
        this.setLayout(null);
        addMapButton("/images/miscellaneous/one.png", 415, 290, 66, 66);
        addMapButton("/images/miscellaneous/two.png", 515, 290, 66, 66);
        addMapButton("/images/miscellaneous/three.png", 615, 290, 66, 66);
        addMapButton("/images/miscellaneous/four.png", 715, 290, 66, 66);
        addMapButton("/images/miscellaneous/five.png", 815, 290, 66, 66);
        addMapButton("/images/miscellaneous/six.png", 415, 400, 66, 66);
        addMapButton("/images/miscellaneous/seven.png", 515, 400, 66, 66);
        addMapButton("/images/miscellaneous/eight.png", 615, 400, 66, 66);
        addMapButton("/images/miscellaneous/nine.png", 715, 400, 66, 66);
        addMapButton("/images/miscellaneous/ten.png", 815, 400, 66, 66);
    }

    /**
     * Handles action events when a map button is pressed.
     * It switches the view to the GamePanel
     * 
     * @param e the ActionEvent triggered by a button press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < mapButtons.size(); i++)
            if (e.getSource() == mapButtons.get(i)) {
                Main.gp.makeMap(i + 1);
                Main.cardLayout.show(Main.cardPanel, "GameScreen");
                BackgroundMusic.stopMusic();
                GamePanel.gp.setEnabled(true);
            }
    }

    /**
     * Paints the background image of the map selection screen.
     *
     * @param g the Graphics object used for drawing
     */
    public void paintComponent(Graphics g) {

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Adds the map selection buttons to the panel.
     *
     * @param iconPath the file path to the icon image for the button
     * @param x        the x-coordinate of the button's position
     * @param y        the y-coordinate of the button's position
     * @param width    the width of the button
     * @param height   the height of the button
     */
    private void addMapButton(String iconPath, int x, int y, int width, int height) {
        JButton mapButton = new JButton();
        mapButton.setFont(mapButton.getFont().deriveFont(Font.BOLD));
        mapButton.setBounds(x, y, width, height);
        ImageIcon mapButtonIcon = new ImageIcon(getClass().getResource(iconPath));
        ImageIcon resizedIcon = resizeIcon(mapButtonIcon, 100, 100);
        mapButton.setIcon(resizedIcon);
        mapButton.setIconTextGap(100);
        mapButton.addActionListener(this);
        mapButtons.add(mapButton);
        this.add(mapButton);
    }

    /**
     * Resizes an icon to the specified width and height.
     *
     * @param icon          the ImageIcon to resize
     * @param resizedWidth  the width to resize the icon to
     * @param resizedHeight the height to resize the icon to
     * @return a new ImageIcon with the specified dimensions
     */
    public ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Returns the list of map selection buttons.
     *
     * @return an ArrayList of JButton objects representing the map selection
     *         buttons
     */
    public ArrayList<JButton> getBList() {
        return mapButtons;
    }
}
