package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DocumentReader {

    // Methode, um das Dokument aus einer Datei zu lesen
    public static Document readFromFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        int ch;
        StringBuilder textBuilder = new StringBuilder();
        
        while ((ch = fileReader.read()) != -1) {
            textBuilder.append((char) ch);
        }
        
        fileReader.close();
        
        return new Document(textBuilder.toString());
    }
}
