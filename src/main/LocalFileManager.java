package main;

import javax.swing.*;
import java.io.*;

import static main.JarFileOrganizer.getJarDirectory;

public class LocalFileManager {

    private final String BACKUP_ONE = "slotOne.ser";
    private final String BACKUP_TWO = "slotTwo.ser";
    private final String SAVED_FOLDER = "saved";

    // Helper method to check if a file exists
    private boolean fileExistsCheck(String fileName) {
        String filePath = getFilePath(fileName);
        File file = new File(filePath);
        return file.exists();
    }

    // Helper method to get the full path for a file, ensuring the "saved" folder exists
    private String getFilePath(String fileName) {
        String jarDirectory = getJarDirectory();
        File savedFolder = new File(jarDirectory, SAVED_FOLDER);

        // Create the "saved" folder if it doesn't exist
        if (!savedFolder.exists()) {
            boolean created = savedFolder.mkdir();
            if (!created) {
                System.err.println("Failed to create 'saved' directory.");
            }
        }

        return savedFolder.getAbsolutePath() + File.separator + fileName;
    }

    public void backUpSlotOne(Tamagotchi pet) throws IOException {
        if (fileExistsCheck(BACKUP_ONE)) {
            int selection = JOptionPane.showConfirmDialog(
                    null,
                    "Saved game already exists. Overwrite the file?",
                    "Confirm Save",
                    JOptionPane.YES_NO_OPTION
            );

            if (selection != 0) {
                throw new IOException("Slot one backup file already exists. Overwrite not allowed.");
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(getFilePath(BACKUP_ONE));
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(pet);
            System.out.println("Pet " + pet.getName() + " saved to: " + getFilePath(BACKUP_ONE));
            JOptionPane.showMessageDialog(null,"Game Saved","Confirmation",JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void backUpSlotTwo(Tamagotchi pet) throws IOException {
        if (fileExistsCheck(BACKUP_TWO)) {
            int selection = JOptionPane.showConfirmDialog(
                    null,
                    "Saved game already exists. Overwrite the file?",
                    "Confirm Save",
                    JOptionPane.YES_NO_OPTION
            );

            if (selection != 0) {
                throw new IOException("Slot one backup file already exists. Overwrite not allowed.");
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(getFilePath(BACKUP_TWO));
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(pet);
            System.out.println("Pet " + pet.getName() + " saved to: " + getFilePath(BACKUP_TWO));
            JOptionPane.showMessageDialog(null,"Game Saved","Confirmation",JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public Tamagotchi loadSlotOne() throws IOException, ClassNotFoundException {
        if (!fileExistsCheck(BACKUP_ONE)) {
            throw new FileNotFoundException("Slot one backup file does not exist.");
        }

        try (FileInputStream fileIn = new FileInputStream(getFilePath(BACKUP_ONE));
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Tamagotchi deserializedPet = (Tamagotchi) in.readObject();
            System.out.println("Deserialized Pet Loaded from: " + getFilePath(BACKUP_ONE));
            return deserializedPet;
        }
    }

    public Tamagotchi loadSlotTwo() throws IOException, ClassNotFoundException {
        if (!fileExistsCheck(BACKUP_TWO)) {
            throw new FileNotFoundException("Slot two backup file does not exist.");
        }

        try (FileInputStream fileIn = new FileInputStream(getFilePath(BACKUP_TWO));
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Tamagotchi deserializedPet = (Tamagotchi) in.readObject();
            System.out.println("Deserialized Pet Loaded from: " + getFilePath(BACKUP_TWO));
            return deserializedPet;
        }
    }
}