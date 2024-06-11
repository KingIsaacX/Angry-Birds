import java.io.File;
import javax.sound.sampled.*;

/**
 * The BackgroundMusic class manages the playback of background music for the
 * game.
 * The music is played continuously in a loop.
 * 
 * @author Arin Wani
 * @version 6/9/2024
 */
public class BackgroundMusic {

    /**
     * The Clip object used to control the playback of the background music.
     */
    private static Clip clip;
    /**
     * The File object is the path to the music file.
     */
    private static File musicPath;

    /**
     * Plays background music continuously
     */
    public static void PlayMusic() {
        try {
            musicPath = new File("./sounds/BackgroundMusic.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the currently playing background music when called
     */
    public static void stopMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

}