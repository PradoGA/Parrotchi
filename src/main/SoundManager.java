package main;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundManager {

    // Paths to sound effect files
    final private String BUTTON_SOUND_PATH = "sounds/buttonSound.wav";
    final private String HELLO_SOUND_PATH = "sounds/helloSound.wav";
    final private String PARROT_SOUND_PATH = "sounds/parrotSound.wav";
    final private String PLAY_SOUND_PATH = "sounds/playSound.wav";
    final private String LOVE_SOUND_PATH = "sounds/loveSound.wav";
    final private String CORRECT_SOUND_PATH = "sounds/correctSound.wav";
    final private String FAIL_SOUND_PATH = "sounds/failSound.wav";
    final private String ANGRY_SOUND_PATH = "sounds/angrySound.wav";
    final private String SLEEP_SOUND_PATH = "sounds/sleepSound.wav";
    final private String DONT_SOUND_PATH = "sounds/noSound.wav";
    final private String NOTIFICATION_SOUND_PATH = "sounds/notificationSound.wav";
    final private String THANK_SOUND_PATH = "sounds/thankyouSound.wav";

    // Paths to background music files
    final private String RELAXING_LOOP_PATH = "sounds/relaxingLoop.wav";
    final private String HAPPY_LOOP_PATH = "sounds/happyLoop.wav";
    private Clip clip;  // Clip to play sounds
    private int backgroundVolume = -5;  // Volume level for background music
    private final static SoundManager SOUND_MANAGER_INSTANCE = new SoundManager();  // Singleton instance
    boolean canPlaySounds = true;  // Flag to check if sounds can be played

    // Singleton pattern to get the single instance of SoundManager
    public static SoundManager getSoundManagerInstance() {
        return SOUND_MANAGER_INSTANCE;
    }

    // Plays a sound from the given file path
    private void playSound(String soundPath) {
        if (!canItPlaySounds()) {
            return;
        }
        try (InputStream audioSrc = getClass().getClassLoader().getResourceAsStream(soundPath);
             BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc)) {
            if (audioSrc == null) {
                throw new IOException("Sound file not found: " + soundPath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Public methods to play specific sound effects
    public void playButtonSound() {
        playSound(BUTTON_SOUND_PATH);
    }

    public void playNotificationSound() {
        playSound(NOTIFICATION_SOUND_PATH);
    }

    public void playThankYouSound() {
        playSound(THANK_SOUND_PATH);
    }

    public void playHelloSound() {
        playSound(HELLO_SOUND_PATH);
    }

    public void playDontSound() {
        playSound(DONT_SOUND_PATH);
    }

    public void playFailSound() {
        playSound(FAIL_SOUND_PATH);
    }

    public void playCorrectSound() {
        playSound(CORRECT_SOUND_PATH);
    }

    public void playAngrySound() {
        playSound(ANGRY_SOUND_PATH);
    }

    public void playSleepSound() {
        playSound(SLEEP_SOUND_PATH);
    }

    public void playPlaySound() {
        playSound(PLAY_SOUND_PATH);
    }

    public void playLoveSound() {
        playSound(LOVE_SOUND_PATH);
    }

    public void playParrotSound() {
        playSound(PARROT_SOUND_PATH);
    }

    // Plays the default relaxing background music
    public void playBackgroundMusic() {
        playLoopingSound(RELAXING_LOOP_PATH);
    }

    // Changes the background music based on the given value
    public void changeBackgroundMusic(Boolean value) {
        if (clip.isRunning()) {
            clip.stop();
            if (value) {
                playLoopingSound(HAPPY_LOOP_PATH);
            } else {
                playBackgroundMusic();
            }
        }
    }

    // Toggles the background music on and off
    public void toggleBackgroundMusic() {
        if (clip == null) {
            return;
        }
        if (clip.isRunning()) {
            clip.stop();
        } else {
            clip.start();
        }
    }

    // Checks if sounds can be played
    public boolean canItPlaySounds() {
        return canPlaySounds;
    }

    // Toggles the ability to play sounds on and off
    public void toggleCanPlaySounds() {
        canPlaySounds = !canPlaySounds;
        System.out.println(canPlaySounds ? "Sounds On" : "Sounds Off");
    }

    // Plays a sound in a loop from the given file path
    private void playLoopingSound(String soundPath) {
        try (InputStream audioSrc = getClass().getClassLoader().getResourceAsStream(soundPath);
             BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc)) {
            if (audioSrc == null) {
                throw new IOException("Sound file not found: " + soundPath);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(backgroundVolume);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}