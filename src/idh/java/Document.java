package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Document {
    String documentText;

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

    public static final void main(String[] args) throws IOException {
        Document d = Document.readFromFile(new File("data/dracula.txt"));

        StringTokenizer tokenizer = new StringTokenizer(d.getDocumentText());

        int count = tokenizer.countTokens();

        for (int i = 0; i < count; i++) {
            String token = tokenizer.nextToken();
            System.out.println(token);
        }
    }
}
