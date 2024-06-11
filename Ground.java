
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * The Ground class is responsible for creating the ground and boundaries in a
 * 2D physics world using the JBox2D library.
 * It defines three static bodies: the ground at the bottom of the screen, and
 * the left and right walls.
 * 
 * @author Isaac Xiao
 * @author Arin Wani
 * @author William Dong
 * @version 6/9/2024
 */
public class Ground {

    /**
     * Constructor for the Ground class. It initializes the ground and walls as
     * static bodies in the physics world.
     */
    public Ground() {
        /** Create the ground body and its fixture */
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape groundShape = new PolygonShape();
        BodyDef body1Def = new BodyDef();
        body1Def.type = BodyType.STATIC;
        body1Def.position.set(650 / Bird.PIXEL_TO_METER, 520 / Bird.PIXEL_TO_METER);
        Body body1 = GamePanel.world.createBody(body1Def);
        groundShape.setAsBox(Main.frame.getWidth(), 0);
        fixtureDef.shape = groundShape;
        fixtureDef.density = .8f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0f;
        body1.createFixture(fixtureDef);

        /** Create the left wall body and its fixture */
        FixtureDef fixtureDef2 = new FixtureDef();
        PolygonShape groundShape2 = new PolygonShape();
        BodyDef body2Def = new BodyDef();
        body2Def.type = BodyType.STATIC;
        body2Def.position.set(0 / Bird.PIXEL_TO_METER, (Main.frame.getHeight() / 2) / Bird.PIXEL_TO_METER);
        Body body2 = GamePanel.world.createBody(body2Def);
        groundShape2.setAsBox(0, Main.frame.getHeight());
        fixtureDef2.shape = groundShape2;
        fixtureDef2.density = .8f;
        fixtureDef2.friction = 1f;
        fixtureDef2.restitution = 0f;
        body2.createFixture(fixtureDef2);

        /** Create the right wall body and its fixture */
        FixtureDef fixtureDef3 = new FixtureDef();
        PolygonShape groundShape3 = new PolygonShape();
        BodyDef body3Def = new BodyDef();
        body3Def.type = BodyType.STATIC;
        body3Def.position.set(Main.frame.getWidth() / Bird.PIXEL_TO_METER,
                (Main.frame.getHeight() / 2) / Bird.PIXEL_TO_METER);
        Body body3 = GamePanel.world.createBody(body3Def);
        groundShape3.setAsBox(0, Main.frame.getHeight());
        fixtureDef3.shape = groundShape3;
        fixtureDef3.density = .8f;
        fixtureDef3.friction = 1f;
        fixtureDef3.restitution = 0f;
        body3.createFixture(fixtureDef3);
    }

}