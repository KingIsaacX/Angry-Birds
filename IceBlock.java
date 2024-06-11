import org.jbox2d.dynamics.FixtureDef;

/**
 * The IceBlock class represents a specific type of Block with properties
 * resembling ice.
 * It extends the Block class and initializes its physical properties to make it
 * behave like ice.
 * 
 * @author William Dong
 * @version 6/9/2024
 */
public class IceBlock extends Block {

    /** The FixtureDef that defines the physical properties of the IceBlock. */
    private FixtureDef fixtureDef;

    /**
     * Constructs a new IceBlock object with the specified position, rotation, and
     * image.
     * Initializes the physical properties to simulate an ice-like behavior.
     *
     * @param x   the x-coordinate of the IceBlock
     * @param y   the y-coordinate of the IceBlock
     * @param deg the rotation angle of the IceBlock in degrees
     * @param im  the image index for the IceBlock
     */
    public IceBlock(int x, int y, int deg, int im) {
        super(x, y, deg, im, 2, 2);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = super.getRectangleShape();
        fixtureDef.density = .9f;
        fixtureDef.friction = .2f;
        fixtureDef.restitution = 0f;
        super.getBody().createFixture(fixtureDef);
    }
}