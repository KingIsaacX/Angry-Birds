import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * The Egg class represents an egg object in the game. It extends JComponent to
 * enable rendering
 * on the GUI.
 * 
 * @author Isaac Xiao
 * @version 6/9/2024
 *          An egg is associated with a parent bird from which it is laid. It
 *          has physical properties
 *          defined by the JBox2D physics engine.
 */
public class Egg extends JComponent {
    /** The image of the egg. */
    private BufferedImage eggImage;
    /** The fixture definition for the egg's physical properties. */
    private FixtureDef eggFix;
    /** The shape of the egg. */
    private CircleShape eggShape;
    /** The body definition for the egg's body. */
    private BodyDef eggBodyDef;
    /** The physical body of the egg. */
    private Body eggBody;

    /**
     * Constructs an Egg associated with the specified parent bird.
     * 
     * @param parentBird the parent bird laying the egg
     */
    public Egg(Bird parentBird) {
        try {
            eggImage = ImageIO.read(getClass().getResource("./images/birds/egg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        eggFix = new FixtureDef();
        eggShape = new CircleShape();
        eggBodyDef = new BodyDef();
        eggShape.setRadius((eggImage.getWidth() / 2 + 10) / Bird.PIXEL_TO_METER);
        eggFix.shape = eggShape;
        eggFix.density = 5;
        eggFix.friction = .24f;
        eggFix.restitution = 0.4f;
        eggBodyDef.type = BodyType.DYNAMIC;
        eggBodyDef.position.set((int) (parentBird.getBody().getPosition().x),
                (int) (parentBird.getBody().getPosition().y));
        eggBody = GamePanel.world.createBody(eggBodyDef);
        eggBody.createFixture(eggFix);
    }

    /**
     * @return the body of the egg
     */
    public Body getBody() {
        return eggBody;
    }

    /**
     * @return the image of the egg
     */
    public BufferedImage getBufferedImage() {
        return eggImage;
    }

}
