package mygame.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static mygame.io.InputClasses.JsonParser.getJSONFromFile;


public class InputClasses {
    public static class JsonParser {
        public static String getJSONFromFile(String filename) {
            String jsonText = "";
            try {
                BufferedReader bufferedReader =
                        new BufferedReader(new FileReader(filename));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonText += line + "\n";
                }

                bufferedReader.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonText;
        }
    }

    public static void main(String[] args) {
        String strJson = JsonParser.getJSONFromFile("files/exemplo.json");
        System.out.println(strJson);

    }


}