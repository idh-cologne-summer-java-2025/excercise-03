package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
    String documentText;

    // Methode zum Einlesen des Textes aus einer Datei
    public static Document readFromFile(File f) throws IOException {
        FileReader fileReader = new FileReader(f);
        int ch;
        StringBuilder b = new StringBuilder();
        while ((ch = fileReader.read()) != -1) {
            b.append((char) ch);
        }
        fileReader.close();
        Document doc = new Document();
        doc.documentText = b.toString();
        return doc;
    }

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    // Die main-Methode, die die Tokens des Textes ausgibt
    public static final void main(String[] args) throws IOException {
        Document d = Document.readFromFile(new File("data/dracula.txt"));
        Iterator<String> iterator = d.iterator();
        
        // Ausgabe der ersten 100 Tokens (optional, um die Ausgabe zu begrenzen)
        int i = 0;
        while (iterator.hasNext()) {
            System.out.println(i++ + ": " + iterator.next());
            if (i > 100) break;  // Ausgabe von maximal 100 Tokens
        }
    }

    // Iterator-Methode, um die Tokens im Text zu durchlaufen
    @Override
    public Iterator<String> iterator() {
        StringTokenizer tokenizer = new StringTokenizer(documentText);  // Text in Tokens aufteilen
        return new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return tokenizer.hasMoreTokens();
            }

            @Override
            public String next() {
                return tokenizer.nextToken();
            }
        };
    }
}
