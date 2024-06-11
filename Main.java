import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Main class initializes and sets up the main JFrame and CardLayout for the
 * Angry Birds game.
 * It sets up different panels for the game, map, and start screen, and manages
 * switching between them.
 * 
 * @author Arin Wani
 * @author Isaac Xiao
 * @version 6/9/2024
 */
public class Main {
    /** The main JFrame of the application. */
    public static JFrame frame;
    /** The GamePanel where the main gameplay occurs. */
    public static GamePanel gp;
    /** The MapPanel where the game's map selector is displayed. */
    public static MapPanel mp;
    /** The StartPanel where the start screen is displayed. */
    public static StartPanel sp;
    /** The CardLayout used to switch between different panels. */
    public static CardLayout cardLayout;
    /**
     * The JPanel that holds the different game panels and uses CardLayout to switch
     * between them.
     */
    public static JPanel cardPanel;

    /**
     * The main method that sets up the JFrame and initializes the panels.
     * It adds the panels to the CardLayout and sets the initial panel to the
     * StartScreen.
     *
     * @param args Main method header
     */
    public static void main(String[] args) {
        frame = new JFrame("Angry Birds");
        frame.setBounds(200, 200, 1300, 700);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        sp = new StartPanel();
        mp = new MapPanel();
        gp = new GamePanel();
        cardPanel.add(gp, "GameScreen");
        cardPanel.add(sp, "StartScreen");
        cardPanel.add(mp, "MapScreen");
        cardLayout.show(cardPanel, "StartScreen");
        frame.add(cardPanel);
        frame.setVisible(true);
    }

}