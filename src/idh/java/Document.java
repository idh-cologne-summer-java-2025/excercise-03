package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
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

	public Iterator<String> iterator() {
		StringTokenizer tokenizer = new StringTokenizer(documentText);

		return new Iterator<String>() {
			public boolean hasNext() {
				return tokenizer.hasMoreTokens(); // Gibt true zurück, wenn noch Tokens vorhanden sind
			}

			@Override
			public String next() {
				return tokenizer.nextToken(); // Gibt das nächste Token zurück
			}
		};
	}

	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}

	

	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));

		String text = d.getDocumentText();
		StringTokenizer tokenizer = new StringTokenizer(text);

		while (tokenizer.hasMoreTokens()) {   //Tokens des Textes ausgeben
			System.out.println(tokenizer.nextToken());
		}

	}

}
