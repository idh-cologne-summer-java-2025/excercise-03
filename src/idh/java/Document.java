package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
    private String documentText;
    
    private String readFromFile(File f) throws IOException {
        FileReader fileReader = new FileReader(f);
        int ch;
        StringBuilder b = new StringBuilder();
        while ((ch = fileReader.read()) != -1) {
            b.append((char) ch);
        }
        fileReader.close();
        return b.toString();
    }
   
    public Document(File file) throws IOException {
        this.documentText = readFromFile(file);
    }

    public String getDocumentText() {
        return documentText;
    }

    @Override
    public Iterator<String> iterator() {
        StringTokenizer tokenizer = new StringTokenizer(documentText);
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

    public static void main(String[] args) throws IOException {
        Document doc = new Document(new File("data/dracula.txt"));
        for (String token : doc) {
            System.out.print(token + " ");
        }
    }
}
