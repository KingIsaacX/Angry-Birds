import org.jbox2d.dynamics.FixtureDef;

/**
 * The WoodBlock class represents a wood block in the game.
 * It extends the Block class and sets specific physical properties
 * suitable for a wood block.
 * 
 * @author William Dong
 * @version 6/9/2024
 */

public class WoodBlock extends Block {

    /** The FixtureDef that defines the physical properties of the stone block. */
    private FixtureDef fixtureDef;

    /**
     * Constructs a new WoodBlock with the specified position, rotation, and image.
     * It initializes the block with medium density and friction values to simulate
     * a wood block.
     *
     * @param x   the x-coordinate of the wood block
     * @param y   the y-coordinate of the wood block
     * @param deg the rotation angle of the wood block in degrees
     * @param im  the image index of the wood block
     */
    public WoodBlock(int x, int y, int deg, int im) {
        super(x, y, deg, im, 1, 3);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = super.getRectangleShape();
        fixtureDef.density = 2f;
        fixtureDef.friction = .45f;
        fixtureDef.restitution = 0.1f;
        super.getBody().createFixture(fixtureDef);
    }
}
