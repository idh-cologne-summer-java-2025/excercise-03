package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable <String> {
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
	
	public Iterator <String> interator() {
		return new DocumentIterator(documentText);
	}
	
	class DocumentIterator implements Iterator <String> {
		
		
	
		StringTokenizer st;
		public DocumentIterator(String s) {
			st = new StringTokenizer(s);
		}
		
		
	public boolean hasNext() {
		return st.hasMoreTokens();
	}
	
	public String next() {
		return st.nextToken();
	}
	
	}
	
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		
		for (String word : d) {
			System.out.println(word);
		
	}

	
	
}
