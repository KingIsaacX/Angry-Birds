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
 * The Bird class represents a bird object in the game.
 * It includes physical properties for the bird using the JBox2D physics
 * library.
 * Manages the bird's image and abilities, and handles the bird's movement and
 * physical state.
 * 
 * Different bird types have different properties and abilities.
 * 
 * It handles the bird's abilities based on the bird type, such as speeding up
 * or causing an explosion.
 * 
 * @author Isaac Xiao
 * @author William Dong
 * @author Arin Wani
 * @version 6/9/2024
 * 
 * 
 */
public class Bird {

    /** The fixture definition for the bird's physical body. */
    private FixtureDef fixtureDef;
    /** The circle shape used for the bird's physical body. */
    private CircleShape circleShape;
    /** The body definition for the bird's physical body. */
    private BodyDef bodyDef;
    /** The position of the bird in the world. */
    private Vec2 position;
    /** The physical body of the bird. */
    private Body body;
    /** Indicates whether the bird is currently in the air. */
    private boolean isInAir;
    /** Indicates whether the bird's ability is ready to be used. */
    private boolean abilityReady;

    /** Creates egg object when white bird is clicked in air */
    private Egg egg;
    /** The image of the bird. */
    private BufferedImage birdImage;
    /** Conversion factor from pixels to meters. */
    public static final float PIXEL_TO_METER = 40f;

    /**
     * Constructs a Bird of the specified type.
     * Initializes the bird's image, shape, and physical properties.
     *
     * @param birdType the type of the bird
     */
    public Bird(int birdType) {
        URL birdURL = getClass().getResource("./images/birds/bird" + birdType + ".png");
        try {
            birdImage = ImageIO.read(birdURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        circleShape = new CircleShape();
        if (birdType == 1)
            circleShape.setRadius(birdImage.getWidth() / 2 / PIXEL_TO_METER);
        else if (birdType == 2)
            circleShape.setRadius(birdImage.getWidth() / 2 / PIXEL_TO_METER - 9 / PIXEL_TO_METER);
        else if (birdType == 3)
            circleShape.setRadius(birdImage.getWidth() / 2 / PIXEL_TO_METER - 3 / PIXEL_TO_METER);
        else if (birdType == 4)
            circleShape.setRadius(birdImage.getWidth() / 2 / PIXEL_TO_METER - 5 / PIXEL_TO_METER);
        else if (birdType == 5)
            circleShape.setRadius(birdImage.getWidth() / 2 / PIXEL_TO_METER - 5 / PIXEL_TO_METER);

        fixtureDef = new FixtureDef();
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(230 / PIXEL_TO_METER, 401 / PIXEL_TO_METER);
        body = GamePanel.world.createBody(bodyDef);
        body.setAwake(false);

        fixtureDef.shape = circleShape;
        if (birdType == 1)
            fixtureDef.density = 2f;
        else if (birdType == 2)
            fixtureDef.density = 3f;
        else if (birdType == 3)
            fixtureDef.density = 17;
        else if (birdType == 4)
            fixtureDef.density = 2;
        else if (birdType == 5)
            fixtureDef.density = 2.2f;
        fixtureDef.friction = .56f;
        fixtureDef.restitution = 0.4f;
        body.createFixture(fixtureDef);
        position = body.getPosition();
        isInAir = false;
        abilityReady = true;
    }

    /**
     * @return the fixture definition
     */
    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    /**
     * @return the circle shape
     */
    public CircleShape getCircleShape() {
        return circleShape;
    }

    /**
     * @return the body definition
     */
    public BodyDef getBodyDef() {
        return bodyDef;
    }

    /**
     * @return the position as a Vec2 object
     */
    public Vec2 getPos() {
        return position;
    }

    /**
     * @return the body of the bird
     */
    public Body getBody() {
        return body;
    }

    /**
     * Sets the position of the bird to the specified x and y coordinates.
     * 
     * @param x the new x-coordinate
     * @param y the new y-coordinate
     */
    public void setPos(int x, int y) {
        body.setTransform(new Vec2(x / PIXEL_TO_METER, y / PIXEL_TO_METER), 0);
    }

    /**
     * @return whether the bird is in the air
     */
    public boolean getInAir() {
        return isInAir;
    }

    /**
     * Sets whether the bird is in the air.
     * 
     * @param inAir the new state indicating if the bird is in the air
     */
    public void setInAir(boolean inAir) {
        this.isInAir = inAir;
    }

    /**
     * @return the image of the bird as a BufferedImage
     */
    public BufferedImage getBufferedImage() {
        return birdImage;
    }

    /**
     * Gets the speed of the bird based on its type.
     * 
     * @param birdType the type of the bird
     * @return the speed of the given bird
     */
    public static float getSpeed(int birdType) {
        if (birdType == 1 || birdType == 2)
            return .35f;
        else if (birdType == 3)
            return 7.5f;
        else if (birdType == 4)
            return .6f;
        else if (birdType == 5)
            return .5f;
        return 0;
    }

    /**
     * Activates the bird's special ability (if it has one) based on its type.
     * 
     * @param birdType the type of the bird
     */
    public void ability(int birdType) {
        if (abilityReady && isInAir) {
            if (birdType == 2) {
                Vec2 direction = new Vec2(body.getLinearVelocity().clone().mul(1 / body.getLinearVelocity().length()));
                body.applyLinearImpulse(direction.mul(10), body.getWorldCenter());
                try {
                    birdImage = ImageIO.read(getClass().getResource("./images/birds/bird2ZOOM.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (birdType == 4) {
                Vec2 explosionCenter = body.getPosition();
                SoundClips.boom();
                for (Body body = GamePanel.world.getBodyList(); body != null; body = body.getNext()) {
                    if (body != this.body && body.getType() == BodyType.DYNAMIC) {
                        Vec2 blastDir = body.getPosition().sub(explosionCenter);
                        float distance = blastDir.normalize();
                        if (distance < 4.5f) {
                            float impulseMag = (4.5f - distance) * 25f;
                            Vec2 impulse = blastDir.mul(impulseMag);
                            body.applyLinearImpulse(impulse, body.getWorldCenter());
                        }
                    }
                }
                GamePanel.gp.add(new Effects(
                        (int) ((position.x - (circleShape.m_radius)) * Bird.PIXEL_TO_METER),
                        (int) ((position.y - (circleShape.m_radius)) * Bird.PIXEL_TO_METER), GamePanel.gp, false));
            }
            abilityReady = false;
        }
    }

    /**
     * @return the egg object associated with the bird
     */
    public Egg getEgg() {
        return egg;
    }

    /**
     * @return whether the bird's ability is ready to be used
     */
    public boolean isAbilityReady() {
        return abilityReady;
    }
}