package idh.java;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Lade das Dokument aus der Datei
        Document document = DocumentReader.readFromFile(new File("data/dracula.txt"));

        // Iteriere über die Tokens des Dokuments und gib sie aus
        for (String token : document) {
            System.out.println(token);
        }
    }
}
