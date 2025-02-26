package main;

import javax.swing.*;

import static main.JarFileOrganizer.getJarDirectory;
import java.io.*;

public class LocalFileManager {

    private final String BACKUP_ONE = "slotOne.ser";
    private final String BACKUP_TWO = "slotTwo.ser";

    // Helper method to check if a file exists
    private boolean fileExistsCheck(String fileName) {
        String filePath = getFilePath(fileName);
        File file = new File(filePath);
        return file.exists();
    }

    // Helper method to get the full path for a file
    private String getFilePath(String fileName) {
        return getJarDirectory() + File.separator + fileName;
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
                throw new IOException("Slot two backup file already exists. Overwrite not allowed.");
            }
        }

        try (FileOutputStream fileOut = new FileOutputStream(getFilePath(BACKUP_TWO));
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(pet);
            System.out.println("Pet " + pet.getName() + " saved to: " + getFilePath(BACKUP_TWO));
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