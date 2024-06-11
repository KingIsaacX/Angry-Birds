import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * The Block class represents a block object in the game.
 * It includes physical properties for the block using the JBox2D physics engine,
 * manages the block's image and health, and handles the block's damage state.
 * 
 * Different block types have different properties and health values.
 * The block's image changes based on its damage state.
 * 
 * The class also manages the block's physical characteristics such as position, shape, restitution, friction, and density.
 * @author William Dong
 * @author Arin Wani
 * @author Isaac Xiao
 * @version 6/9/2024
 */

public class Block{
    /** The fixture definition for the block's physical body. */
    private FixtureDef fixtureDef;
    /** The polygon shape used for the block's physical body. */
    private PolygonShape rectangleShape;
    /** The body definition for the block's physical body. */
    private BodyDef bodyDef;
    /** The position of the block in the world. */
    private Vec2 position;
    /** The physical body of the block. */
    private Body body;
    /** The image of the block. */
    private BufferedImage blockImage;
    /** The health of the block. */
    private int health;
    /** The image index of the block. */
    private int im;
    /** The material type of the block. */
    private int blockMat;
    /** The damage state of the block. */
    private int ch;

    /**
     * Constructs a Block at the specified x and y coordinates.
     * Initializes the block's image, shape, and physical properties.
     *
     * @param x the x-coordinate of the block
     * @param y the y-coordinate of the block
     * @param deg the rotation angle of the block in degrees
     * @param im the image index of the block
     * @param blockMat the material type of the block
     * @param health the initial health of the block
     */
    public Block(int x, int y,float deg,int im,int blockMat,int health) {
        
        URL blockURL = getClass().getResource("./images/blocks/block"+im+"M"+blockMat+"D"+1+".png");
        try {
            blockImage = ImageIO.read(blockURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.health=health;
        this.im = im;
        this.blockMat = blockMat;
        this.ch = 1;
        rectangleShape = new PolygonShape();
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(x/Bird.PIXEL_TO_METER, y/Bird.PIXEL_TO_METER);
        bodyDef.angle = (float)Math.toRadians(deg);
        body = GamePanel.world.createBody(bodyDef);
        body.setAwake(true);
        rectangleShape.setAsBox((blockImage.getWidth()/2f) / Bird.PIXEL_TO_METER , (blockImage.getHeight()/2f) / Bird.PIXEL_TO_METER );

        position = body.getPosition();
 
    }

    /**
     * Decrements the health of the block by one.
     */ 
    public void decrementHealth()
    {
        health--;
    }
    /**
     * @return the health of the block
     */
    public int getHealth()
    {
        return health;
    }
    /**
     * @return the fixture definition
     */
    public FixtureDef getFixtureDef() 
    {
        return fixtureDef;
    }
    /**
     * @return the rectangle shape
     */
    public PolygonShape getRectangleShape() 
    {
        return rectangleShape;
    }

    /**
     * @return the image of the block as a BufferedImage
     */
    public BufferedImage getBufferedImage()
    {
        return blockImage;
    }

    /**
     * @return the body definition
     */
    public BodyDef getBodyDef() 
    {
        return bodyDef;
    }

    /**
     * @return the position as a Vec2 object
     */
    public Vec2 getPos() 
    {
        return position;
    }

    /**
     * @return the body
     */
    public Body getBody() 
    {
        return body;
    }

    /**
     * Sets the position of the block to the specified x and y coordinates.
     * 
     * @param x the new x-coordinate
     * @param y the new y-coordinate
     * 
     */ 
    public void setPos(int x, int y) {
        body.setTransform(new Vec2(x / Bird.PIXEL_TO_METER, y / Bird.PIXEL_TO_METER), 0);
    }
    /**
     * Updates the block's image to reflect damage.
     * If the block's damage state is within the range, the image is updated.
     */
    public void takeDamage()
    {
        ch++;
        if (ch <= 3) 
        {
            URL blockURL = getClass().getResource("./images/blocks/block"+im+"M"+blockMat+"D"+ch+".png");
            try {
                blockImage = ImageIO.read(blockURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}