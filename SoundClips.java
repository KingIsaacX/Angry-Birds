import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The SoundClips class provides methods to play various sound clips for a game.
 * Each method corresponds to a specific sound effect.
 * 
 * @author Isaac Xiao
 * @version 6/9/2024
 */
public class SoundClips {

    /**
     * Plays the bird collision sound effect.
     */
    public static void birdCollision() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/bird_collision.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the bird release sound effect.
     */
    public static void birdRelease() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/bird_Release.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the ice breaking sound effect.
     */
    public static void iceBreaking() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/ice_breaking.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the pig damage sound effect.
     */
    public static void pigDamage() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/pig_damage.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the release sound effect.
     */
    public static void release() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/release.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the slingshot stretch sound effect.
     */
    public static void slingshotStretch() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/slingshot_stretch.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the stone collision sound effect.
     */
    public static void stoneCollision() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/stone_collision.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the wood colliding sound effect.
     */
    public static void woodColliding() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/wood_collision.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the pig death sound effect.
     */
    public static void pigDeath() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/pig_death.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the poof sound effect.
     */
    public static void poof() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/poof.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the boom (bomb) sound effect.
     */
    public static void boom() {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("sounds/bomb.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
