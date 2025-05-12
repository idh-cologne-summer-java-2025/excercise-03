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

	public static Document readFromFile(File f) throws IOException {
		FileReader fileReader = new FileReader(f);
		int ch;
		StringBuilder b = new StringBuilder();
		while( (ch = fileReader.read()) != -1 ) {
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
	 // Hier machen wir Document iterierbar:
    @Override
    public Iterator<String> iterator() {
        // Wir erzeugen aus dem Text eine Liste von Tokens (z. B. Wörtern)
        List<String> tokens = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(documentText);
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens.iterator(); // Gibt einen Iterator über die Liste zurück
    }
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
		// Ausgabe der Tokens in einer for-each-Schleife:
        for (String token : d) {
            System.out.println(token);
        }
	}
		
}

	
	
