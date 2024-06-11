import org.jbox2d.dynamics.FixtureDef;

/**
 * The StoneBlock class represents a stone block in the game.
 * It extends the Block class and sets specific physical properties
 * suitable for a stone block.
 * 
 * @author William Dong
 * @version 6/9/2024
 */

public class StoneBlock extends Block {

    /** The FixtureDef that defines the physical properties of the stone block. */
    private FixtureDef fixtureDef;

    /**
     * Constructs a new StoneBlock with the specified position, rotation, and image.
     * It initializes the block with higher density and friction values to simulate
     * a stone block.
     *
     * @param x   the x-coordinate of the stone block
     * @param y   the y-coordinate of the stone block
     * @param deg the rotation angle of the stone block in degrees
     * @param im  the image index of the stone block
     */
    public StoneBlock(int x, int y, int deg, int im) {
        super(x, y, deg, im, 3, 3);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = super.getRectangleShape();
        fixtureDef.density = 6f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0f;
        super.getBody().createFixture(fixtureDef);
    }
}