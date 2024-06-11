import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The Effects class represents visual effects in the game, such as smoke or
 * explosions.
 * It extends JLabel to display images and uses a Timer to manage the animation
 * of the effects.
 * 
 * @author Isaac Xiao
 * @author William Dong
 * @version 6/9/2024
 *          There are two types of effects: explosion and score effects. The
 *          constructor initializes
 *          the effect based on the provided parameters.
 */
public class Effects extends JLabel {
    /** The current phase of the effect animation. */
    private int phase;
    /** A reference to the current effect instance. */
    private Effects effect;
    /** The icon used to display the score effect. */
    private ImageIcon scoreIcon;
    /** The timer used to control the animation of the effect. */
    private Timer timer;

    /**
     * Constructs an explosion or smoke effect at the specified coordinates.
     * 
     * @param x           the x-coordinate of the effect
     * @param y           the y-coordinate of the effect
     * @param gp          the game panel where the effect is displayed
     * @param isExplosion true if the effect is an explosion, false if it is just
     *                    smoke
     */
    public Effects(int x, int y, GamePanel gp, boolean isExplosion) {
        this.setLocation(x, y);
        if (!isExplosion) {
            this.setSize(50, 50);
            phase = 1;
            effect = this;
            timer = new Timer(50, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (phase < 5)
                        effect.setIcon(new ImageIcon("./images/smoke/smoke" + (phase++) + ".png"));
                    else {
                        gp.remove(effect);
                        timer.stop();
                        timer = null;
                    }

                }
            });
            timer.start();
        } else {
            this.setSize(75, 75);
            phase = 1;
            effect = this;
            timer = new Timer(75, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (phase < 9) {
                        effect.setIcon(new ImageIcon("./images/smoke/explosion" + (phase++) + ".png"));
                        effect.setLocation((int) (effect.getX()), (int) (effect.getY() - effect.getY() * .08));
                    }

                    else {
                        gp.remove(effect);
                        timer.stop();
                        timer = null;
                    }

                }
            });
            timer.start();
        }

    }

    /**
     * Constructs a score effect at the specified coordinates.
     * 
     * @param x     the x-coordinate of the effect
     * @param y     the y-coordinate of the effect
     * @param gp    the game panel where the effect is displayed
     * @param score the score to be displayed
     */
    public Effects(int x, int y, GamePanel gp, int score) {
        this.setLocation(x, y);
        this.setSize(70, 70);
        effect = this;
        scoreIcon = new ImageIcon("./images/miscellaneous/score" + score + ".png");

        timer = new Timer(30, new ActionListener() {
            float counter = 0;

            public void actionPerformed(ActionEvent e) {

                if (effect.getWidth() < 80) {
                    effect.setIcon(scoreIcon);
                    Image image = scoreIcon.getImage();
                    effect.setLocation((int) (effect.getX() - ((int) (effect.getWidth() * .05))),
                            (int) (effect.getY() - ((int) (effect.getHeight() * .05))));
                    effect.setSize((int) (effect.getWidth() * 1.07), (int) (effect.getHeight() * 1.07));
                    Image newImage = image.getScaledInstance((int) (effect.getWidth() * 1.07),
                            (int) (effect.getWidth() * 1.07), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    scoreIcon = new ImageIcon(newImage);
                } else if (counter > .3) {
                    gp.remove(effect);
                    timer.stop();
                    timer = null;
                }

                counter += 1 * .03;
            }
        });
        timer.start();
    }
}
