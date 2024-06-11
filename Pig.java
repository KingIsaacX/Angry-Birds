import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * The Pig class represents a pig in the Angry Birds game, handling its physical
 * properties,
 * image representation, and health status. It uses the JBox2D library for
 * physics simulation.
 * 
 * @author Arin Wani
 * @author William Dong
 * @author Isaac Xiao
 * @version 6/9/2024
 */

public class Pig {
    /** The FixtureDef that defines the physical properties of the Pig. */
    private FixtureDef fixtureDef;
    /** The CircleShape that represents the shape of the Pig. */
    private CircleShape circleShape;
    /** The BodyDef that defines the body of the Pig. */
    private BodyDef bodyDef;
    /** The position of the Pig in the physics world. */
    private Vec2 position;
    /** The physical body of the Pig in the physics world. */
    private Body body;
    /** The BufferedImage that holds the current image of the Pig. */
    private BufferedImage pigImage;
    /** The health of the Pig. */
    private int health;
    /** The conversion factor from pixels to meters. */
    public static final float PIXEL_TO_METER = 40f;

    /**
     * Constructs a new Pig object with the specified position and initializes its
     * physical properties and image.
     *
     * @param x the x-coordinate of the Pig
     * @param y the y-coordinate of the Pig
     */
    public Pig(int x, int y) {

        health = 2;
        URL pigURL = getClass().getResource("./images/pigs/pig" + health + ".png");
        try {
            pigImage = ImageIO.read(pigURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fixtureDef = new FixtureDef();
        circleShape = new CircleShape();
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(x / PIXEL_TO_METER, y / PIXEL_TO_METER);
        body = GamePanel.world.createBody(bodyDef);

        circleShape.setRadius(pigImage.getWidth() / 2 / Bird.PIXEL_TO_METER);
        fixtureDef.shape = circleShape;
        fixtureDef.density = .95f;
        fixtureDef.friction = .8f;
        fixtureDef.restitution = 0.4f;
        body.createFixture(fixtureDef);
        position = body.getPosition();

    }

    /**
     * Returns the FixtureDef of the Pig.
     *
     * @return the FixtureDef object
     */
    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    /**
     * Returns the CircleShape of the Pig.
     *
     * @return the CircleShape object
     */
    public CircleShape getCircleShape() {
        return circleShape;
    }

    /**
     * Returns the BodyDef of the Pig.
     *
     * @return the BodyDef object
     */
    public BodyDef getBodyDef() {
        return bodyDef;
    }

    /**
     * Returns the position of the Pig in the physics world.
     *
     * @return the Vec2 object representing the position
     */

    public Vec2 getPos() {
        return position;
    }

    /**
     * Returns the physical body of the Pig in the physics world.
     *
     * @return the Body object
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets the position of the Pig in the physics world.
     *
     * @param x the new x-coordinate of the Pig
     * @param y the new y-coordinate of the Pig
     */
    public void setPos(int x, int y) {
        body.setTransform(new Vec2(x / PIXEL_TO_METER, y / PIXEL_TO_METER), 0);
    }

    /**
     * Returns the current image of the Pig.
     *
     * @return the BufferedImage object representing the Pig
     */
    public BufferedImage getBufferedImage() {
        return pigImage;
    }

    /**
     * Removes the physical body of the Pig from the physics world.
     *
     * @param body the Body object to be removed
     */
    public void removeBody(Body body) {
        if (body != null)
            GamePanel.world.destroyBody(body);
    }

    /**
     * Returns the current health of the Pig.
     *
     * @return the health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the health of the Pig by 1 and updates its image if it is still
     * alive.
     */
    public void takeDamage() {
        health--;
        if (health > 0) {
            URL pigURL = getClass().getResource("./images/pigs/pig" + health + ".png");
            try {
                pigImage = ImageIO.read(pigURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
