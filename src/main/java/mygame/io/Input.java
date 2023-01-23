package mygame.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Input {

    public static String getJsonFromFile(String filePath) {
        String jsonString = "";
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                jsonString += scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
        return jsonString;
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

