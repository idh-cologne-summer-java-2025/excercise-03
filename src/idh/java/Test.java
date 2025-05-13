package idh.java;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) throws IOException {
        Document doc = Document.readFromFile(new File("data/dracula.txt"));
        String documentText = doc.getDocumentText();

        StringTokenizer st = new StringTokenizer(documentText);
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
        System.out.println();
        System.out.println("String Tokenizer FTW!!!11!");
    }
}
