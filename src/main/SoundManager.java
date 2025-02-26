package main;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundManager {

    // Sound Effects
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


    // Background music
    final private String RELAXING_LOOP_PATH = "sounds/relaxingLoop.wav";
    final private String HAPPY_LOOP_PATH = "sounds/happyLoop.wav";
    private Clip clip;
    private int backgroundVolume = -10;
    private final static SoundManager SOUND_MANAGER_INSTANCE = new SoundManager();
    boolean canPlaySounds = true;

    public static SoundManager getSoundManagerInstance() {
        return SOUND_MANAGER_INSTANCE;
    }

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

    public void playButtonSound() {
        playSound(BUTTON_SOUND_PATH);
    }

    public void playNotificationSound()
    {
        playSound(NOTIFICATION_SOUND_PATH);
    }

    public void playThankYouSound()
    {
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

    public void playBackgroundMusic() {
        playLoopingSound(RELAXING_LOOP_PATH);
    }

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

    public boolean canItPlaySounds() {
        return canPlaySounds;
    }

    public void toggleCanPlaySounds() {
        canPlaySounds = !canPlaySounds;
        System.out.println(canPlaySounds ? "Sounds On" : "Sounds Off");
    }

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