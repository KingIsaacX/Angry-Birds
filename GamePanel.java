import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import java.awt.FontFormatException;

/**
 * The GamePanel class is responsible for creating the game environment and
 * handling all the game logic.
 * It extends JPanel and implements ActionListener to manage the game loop.
 * This class also handles user interactions such as mouse events and contact
 * listener.
 * 
 * @author Isaac Xiao
 * @author Arin Wani
 * @author William Dong
 * @version 6/9/2024
 */

public class GamePanel extends JPanel implements ActionListener {
    /** The background image of the game. */
    private BufferedImage background;
    /** The image of the slingshot. */
    private BufferedImage sling;
    /** List of bird types available in the current level. */
    private ArrayList<Integer> birdTypes;
    /** Coordinates for mouse dragging to position the bird. */
    private int x2, y2;
    /** Coordinates for where to draw the current bird. */
    private int circleX, circleY;
    /** Hotbar object displaying available birds. */
    private Hotbar hotbar;
    /** The current bird to be launched. */
    private Bird curBird;
    /** List of blocks in the level. */
    private ArrayList<Block> blocks;
    /** Ground object representing the ground in the game. */
    private Ground ground;
    /** List of bodies scheduled for destruction. */
    private ArrayList<Body> destBodies;
    /** List of pigs in the level. */
    private ArrayList<Pig> pigs;
    /** JLabel displaying the current score. */
    private JLabel score;
    /** Current score of the player. */
    private int curScore;
    /** Static reference to the current GamePanel instance. */
    public static GamePanel gp;
    /** PostGamePanel object for displaying end-of-game results. */
    private PostGamePanel pgp;
    /** Current level number. */
    private int level;
    /**
     * BuildMap object representing the current level's map. Its basically a Timer
     * for the JBox2D library.
     */
    private BuildMap map;
    /** Timer object for the game loop. */
    private Timer gameLoop;
    /** The Box2D world object managing physics simulation. */
    public static World world = new World(new Vec2(0, 9f));
    /** Scheduler for timed tasks. */
    private ScheduledExecutorService scheduler;
    /** ContactListener for handling collisions. */
    private ContactListener contactListener;
    /** Egg object for Matilda's abilitie. */
    private Egg egg;

    /**
     * Constructs a new GamePanel.
     */
    public GamePanel() {
        URL backroundURL = getClass().getResource("./images/background/background1.jpg");
        URL slingURL = getClass().getResource("./images/miscellaneous/sling.png");
        gp = this;

        this.setLayout(null);
        try {
            background = ImageIO.read(backroundURL);
            sling = ImageIO.read(slingURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        curScore = 0;
        score = new JLabel("Score: " + curScore);
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("images/font/FEASFBRG.TTF"))
                    .deriveFont(Font.PLAIN, 50);
            score.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        score.setBounds(1000, 30, 400, 50);
        score.setForeground(new Color(0, 0, 128));
        this.add(score);

        destBodies = new ArrayList<Body>();

        x2 = 230;
        y2 = 401;

        hotbar = new Hotbar(this);
        ground = new Ground();

        this.addMouseListener(new MouseAdapter() {
            private boolean shouldRelease = true;

            @Override

            /**
             * Handles mouse release events to launch the bird from the slingshot.
             * 
             * @param e the MouseEvent triggered by releasing the mouse button
             */
            public void mouseReleased(MouseEvent e) {
                if (map != null && gp.isEnabled()) {
                    x2 = 230;
                    y2 = 401;

                    if (!curBird.getInAir() && shouldRelease) {
                        curBird.setPos(201, 401);
                        curBird.getBody().applyLinearImpulse((new Vec2(230, 401).sub(new Vec2(circleX, circleY + 10)))
                                .mul(Bird.getSpeed(birdTypes.get(0))), curBird.getBody().getWorldCenter());// there is a impulse applied to the bird when mouse is released to create "launching" effect

                        curBird.getBody().setAwake(true);
                        SoundClips.birdRelease();
                        SoundClips.release();
                        scheduler = Executors.newScheduledThreadPool(1);

                        scheduler.schedule(new Runnable() {
                            @Override
                            public void run() {
                                destBodies.add(curBird.getBody());
                                gp.add(new Effects(
                                        (int) ((curBird.getBody().getPosition().x - (curBird.getCircleShape().m_radius))
                                                * Bird.PIXEL_TO_METER),
                                        (int) ((curBird.getBody().getPosition().y - (curBird.getCircleShape().m_radius))
                                                * Bird.PIXEL_TO_METER),
                                        gp, false));
                                birdTypes.remove(0);
                                curBird = new Bird(birdTypes.get(0));
                                SoundClips.poof();
                            }
                        }, 4, TimeUnit.SECONDS);
                        curBird.setInAir(true);
                    }
                }
            }

            /**
             * Handles mouse press events to trigger bird abilities.
             * 
             * @param e the MouseEvent triggered by pressing the mouse button
             */
            @Override
            public void mousePressed(MouseEvent e) {
                if (birdTypes.size() != 0 && map != null && gp.isEnabled() && curBird.getInAir()) {
                    if (birdTypes.get(0) == 5 && curBird.isAbilityReady()) {
                        egg = new Egg(curBird);
                        curBird.getBody().applyLinearImpulse(new Vec2(0, -10), curBird.getBody().getWorldCenter());
                    }
                    curBird.ability(birdTypes.get(0));
                    if (birdTypes.get(0) == 4) {
                        gp.add(new Effects(
                                (int) ((curBird.getBody().getPosition().x
                                        - (curBird.getCircleShape().m_radius)) * Bird.PIXEL_TO_METER),
                                (int) ((curBird.getBody().getPosition().y
                                        - (curBird.getCircleShape().m_radius)) * Bird.PIXEL_TO_METER),
                                gp, true));
                        destBodies.add(curBird.getBody());
                        birdTypes.remove(0);
                        if (birdTypes.size() != 0)
                            curBird = new Bird(birdTypes.get(0));
                        SoundClips.poof();
                        shouldRelease = false;
                        repaint();
                    }
                } else
                    shouldRelease = true;
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            /**
             * Sets variables x2 and y2 to where to mouse is being dragged to
             * 
             * @param e the MouseEvent triggered by releasing the mouse button
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
            }
        });

        contactListener = new ContactListener() {
            @Override
            /**
             * Handles the start of a contact event between two bodies in the game.
             * 
             * @param contact the Contact object representing the contact event
             */
            public void beginContact(Contact contact) {
                if (map != null) {
                    if (contact.getFixtureA().getBody() == curBird.getBody()
                            || contact.getFixtureB().getBody() == curBird.getBody())
                        SoundClips.birdCollision();
                    if (egg != null) {
                        if ((contact.getFixtureA().getBody() == egg.getBody()
                                || contact.getFixtureB().getBody() == egg.getBody()) &&
                                (contact.getFixtureA().getBody() != curBird.getBody()
                                        && contact.getFixtureB().getBody() != curBird.getBody())) {
                            for (Body body = GamePanel.world.getBodyList(); body != null; body = body.getNext())
                                if (body != egg.getBody() && body.getType() == BodyType.DYNAMIC) {
                                    Vec2 blastDir = body.getPosition().sub(egg.getBody().getPosition());
                                    float distance = blastDir.normalize();
                                    if (distance < 2f) {
                                        float impulseMag = (2f - distance) * 100f;
                                        Vec2 impulse = blastDir.mul(impulseMag);
                                        body.applyLinearImpulse(impulse, body.getWorldCenter());
                                    }
                                }
                            destBodies.add(egg.getBody());
                            SoundClips.boom();
                        }
                    }
                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            /**
             * Handles post-solve events for contacts, allowing for reactions after the
             * physics solver.
             * 
             * @param contact the Contact object representing the contact event
             * @param impulse the ContactImpulse object representing the impulses applied
             *                during the collision
             */
            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                float totalImpulse = 0;
                for (int i = 0; i < impulse.count; i++)
                    totalImpulse += impulse.normalImpulses[i];

                for (int i = blocks.size() - 1; i >= 0; i--) {
                    if ((contact.getFixtureA().getBody() == curBird.getBody()
                            || contact.getFixtureB().getBody() == curBird.getBody())
                            && contact.getFixtureA().getBody() == blocks.get(i).getBody()
                            || contact.getFixtureB().getBody() == blocks.get(i).getBody()) {
                        if (blocks.get(i) instanceof WoodBlock && totalImpulse > 5) {
                            blocks.get(i).decrementHealth();
                            blocks.get(i).takeDamage();
                            SoundClips.woodColliding();
                        } else if (blocks.get(i) instanceof StoneBlock && totalImpulse > 20) {
                            blocks.get(i).decrementHealth();
                            blocks.get(i).takeDamage();
                            SoundClips.stoneCollision();
                        } else if (blocks.get(i) instanceof IceBlock && totalImpulse > 3.5) {
                            blocks.get(i).decrementHealth();
                            blocks.get(i).takeDamage();
                            SoundClips.iceBreaking();
                        }
                        if (blocks.get(i).getHealth() == 0) {
                            curScore += 1000;
                            destBodies.add(blocks.get(i).getBody());
                            gp.add(new Effects((int) (blocks.get(i).getBody().getPosition().x * Bird.PIXEL_TO_METER),
                                    (int) (blocks.get(i).getBody().getPosition().y * Bird.PIXEL_TO_METER), gp, 1000));
                            blocks.remove(i);
                        }
                    }
                }

                for (int i = pigs.size() - 1; i >= 0; i--) {
                    if (contact.getFixtureA().getBody() == pigs.get(i).getBody()
                            || contact.getFixtureB().getBody() == pigs.get(i).getBody()) {
                        if (totalImpulse > 7) {
                            pigs.get(i).takeDamage();
                            SoundClips.pigDamage();
                        }
                        if (pigs.get(i).getHealth() == 0) {
                            destBodies.add(pigs.get(i).getBody());
                            SoundClips.pigDeath();
                            curScore += 5000;

                            gp.add(new Effects(
                                    (int) ((pigs.get(i).getBody().getPosition().x
                                            - (pigs.get(i).getCircleShape().m_radius)) * Bird.PIXEL_TO_METER),
                                    (int) ((pigs.get(i).getBody().getPosition().y
                                            - (pigs.get(i).getCircleShape().m_radius)) * Bird.PIXEL_TO_METER),
                                    gp, false));
                            gp.add(new Effects(
                                    (int) ((pigs.get(i).getBody().getPosition().x
                                            - (pigs.get(i).getCircleShape().m_radius)) * Bird.PIXEL_TO_METER),
                                    (int) ((pigs.get(i).getBody().getPosition().y
                                            - (pigs.get(i).getCircleShape().m_radius)) * Bird.PIXEL_TO_METER),
                                    gp, 5000));
                            pigs.remove(i);
                        }
                    }
                }
            }
        };

        GamePanel.world.setContactListener(contactListener);
        gameLoop = new Timer(17, this);
        gameLoop.stop();
    }

    /**
     * Action performed on each timer tick to update the game and check for certain
     * conditions.
     * 
     * @param e the ActionEvent triggered by the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GamePanel.world.step(1.0f / 40.0f, 20, 2);

        for (Pig pig : pigs)
            pig.getBody().setLinearDamping(.99f);
        for (int i = destBodies.size() - 1; i >= 0; i--) {
            Body removeBody = destBodies.get(i);
            if (egg != null && removeBody == egg.getBody())
                egg = null;
            GamePanel.world.destroyBody(removeBody);
        }

        if (pigs.size() == 0 || birdTypes.size() == 0) {
            if (pigs.size() != 0) {
                int numPigsDead = 0;
                for (Pig pig : pigs)
                    if (pig.getBody().getLinearVelocity().length() < 0.1f)
                        numPigsDead++;
                if (numPigsDead == pigs.size()) {
                    ((Timer) e.getSource()).stop();
                    gp.setEnabled(false);
                    pgp = new PostGamePanel(map.getMaxScore(), curScore, gp);
                    if (scheduler != null)
                        scheduler.shutdownNow();
                    gp.add(pgp);
                }
            } else if (pigs.size() == 0) {
                ((Timer) e.getSource()).stop();
                gp.setEnabled(false);
                pgp = new PostGamePanel(map.getMaxScore(), curScore, gp);
                if (scheduler != null)
                    scheduler.shutdownNow();
                gp.add(pgp);
            }
        }
        repaint();
        score.setText("Score: " + curScore);

    }

    /**
     * Paints the component, rendering the game elements on the panel.
     * 
     * @param g the Graphics object used for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        super.paintComponent(g2);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(sling, 200, 375, 60, 150, this);
        g.drawImage(hotbar.getImage(), 80, 540, 360, 50, this);
        g.drawImage(background, circleX, circleY, WIDTH, HEIGHT, hotbar);
        g.drawImage(hotbar.getImage(), 80, 540, 360, 50, this);

        /** Check if the map is not null before proceeding with drawing */
        if (map != null) {
            /** Draw the egg if it exists and calculate rotation angles */
            if (egg != null) {
                AffineTransform tx = new AffineTransform();
                tx.translate((int) (egg.getBody().getPosition().x * Bird.PIXEL_TO_METER),
                        (int) (egg.getBody().getPosition().y * Bird.PIXEL_TO_METER));
                tx.rotate(egg.getBody().getAngle(), egg.getBufferedImage().getWidth() / 2,
                        egg.getBufferedImage().getHeight() / 2);
                g2.drawImage(egg.getBufferedImage(), tx, this);
            }
            /** Draw each bird from the birdTypes list */
            for (int i = 0; i < birdTypes.size(); i++) {
                try {
                    if (birdTypes.size() != 0)
                        g.drawImage(
                                ImageIO.read(getClass().getResource("./images/birds/bird" + birdTypes.get(i) + ".png")),
                                i * 59 + 87, 543, 45, 45, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /** Draw each block from the blocks list */
            for (int i = 0; i < blocks.size(); i++) {
                Block curBlock = blocks.get(i);
                AffineTransform tx = new AffineTransform();
                tx.translate(
                        curBlock.getBody().getPosition().x * Bird.PIXEL_TO_METER
                                - (curBlock.getBufferedImage().getWidth() / 2),
                        curBlock.getBody().getPosition().y * Bird.PIXEL_TO_METER
                                - (curBlock.getBufferedImage().getHeight() / 2));
                tx.rotate(curBlock.getBody().getAngle(), curBlock.getBufferedImage().getWidth() / 2f,
                        curBlock.getBufferedImage().getHeight() / 2f);
                g2.drawImage(curBlock.getBufferedImage(), tx, this);
            }
            /** Draw each pig from the pigs list */
            for (int i = 0; i < pigs.size(); i++) {
                Pig curPig = pigs.get(i);
                AffineTransform tx = new AffineTransform();
                tx.translate(
                        curPig.getBody().getPosition().x * Bird.PIXEL_TO_METER
                                - (curPig.getBufferedImage().getWidth() / 2),
                        curPig.getBody().getPosition().y * Bird.PIXEL_TO_METER
                                - (curPig.getBufferedImage().getHeight() / 2));
                tx.rotate(curPig.getBody().getAngle(), curPig.getBufferedImage().getWidth() / 2f,
                        curPig.getBufferedImage().getHeight() / 2f);
                g2.drawImage(curPig.getBufferedImage(), tx, this);
            }
            /**
             * The if-block animates the sling shot pulling animation and the rotation 
             * of the bird graphic. We calculate the point where the slingshot can no longer pull back.
             */
            if (x2 != 0 && y2 != 0) {
                g2.setStroke(new BasicStroke(5));
                g2.setColor(new Color(67, 13, 9));
                double len = Point2D.distance(230, 401.5, x2, y2);
                double dx = (x2 - 230) / len;
                double dy = (y2 - 401.5) / len;

                circleX = (int) (curBird.getPos().x * Bird.PIXEL_TO_METER
                        - curBird.getCircleShape().m_radius * Bird.PIXEL_TO_METER);
                circleY = (int) (curBird.getPos().y * Bird.PIXEL_TO_METER
                        - curBird.getCircleShape().m_radius * Bird.PIXEL_TO_METER);

                AffineTransform tx = new AffineTransform();
                tx.translate(
                        (curBird.getBody().getPosition().x * Bird.PIXEL_TO_METER)
                                - (curBird.getBufferedImage().getWidth() / 2),
                        (curBird.getBody().getPosition().y * Bird.PIXEL_TO_METER)
                                - (curBird.getBufferedImage().getWidth() / 2));

                tx.rotate(curBird.getBody().getAngle(), (curBird.getBufferedImage().getWidth() / 2),
                        (curBird.getBufferedImage().getHeight() / 2));
                if (len > 95) {
                    g2.draw(new Line2D.Float(211, 400, (int) (230 + 95 * dx), (int) (401.5 + 95 * dy)));
                    if (curBird.getInAir() != true)
                        curBird.setPos((int) (230 + (95 - curBird.getBufferedImage().getWidth() / 2) * dx),
                                (int) (401.5 + (95 - curBird.getBufferedImage().getWidth() / 2) * dy));
                    if (birdTypes.size() != 0)
                        g2.drawImage(curBird.getBufferedImage(), tx, this);
                    else
                        g2.drawImage(new BufferedImage(400, 300, BufferedImage.TYPE_INT_ARGB), tx, this);
                    g2.draw(new Line2D.Float(249, 403, (int) (230 + 95 * dx), (int) (401.5 + 95 * dy)));
                } else {
                    g2.draw(new Line2D.Float(211, 400, x2, y2));
                    if (curBird.getInAir() != true)
                        if (x2 == 230 && y2 == 401)
                            curBird.setPos(230, (int) (401.5));
                        else
                            curBird.setPos((int) (230 + (len - curBird.getBufferedImage().getWidth() / 2) * dx),
                                    (int) (401.5 + (len - curBird.getBufferedImage().getWidth() / 2) * dy));
                    if (birdTypes.size() != 0)
                        g2.drawImage(curBird.getBufferedImage(), tx, this);
                    else
                        g2.drawImage(new BufferedImage(400, 300, BufferedImage.TYPE_INT_ARGB), tx, this);
                    g2.draw(new Line2D.Float(249, 403, x2, y2));
                }
            }
        }
    }

    /**
     * Returns the current level number.
     * 
     * @return the current level number
     */
    public int getLevel() {
        return level;
    }

    /**
     * Resets the map for the current level, resetting all the variables.
     */
    public void resetMap() {
        for (Body body = world.getBodyList(); body != null; body = body.getNext())
            world.destroyBody(body);
        while (destBodies.size() > 0)
            destBodies.remove(0);
        world.setContactListener(null); 
        world = null;
        map = null;
        birdTypes = null;
        blocks = null;
        pigs = null;
        curBird = null;
        curScore = 0;
        ground = null;
    }

    /**
     * Creates the map for the current level, initializing game elements and state.
     */
    public void makeMap(int level) {
        this.level = level;
        world = new World(new Vec2(0, 9f));
        world.setContactListener(contactListener);
        map = new BuildMap(level);
        birdTypes = map.getBirdList();
        pigs = map.getPigList();
        blocks = map.getBlockList();
        curBird = new Bird(birdTypes.get(0));
        ground = new Ground();
        gameLoop.start();
    }

}