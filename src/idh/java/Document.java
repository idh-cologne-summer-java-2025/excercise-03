package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {

	@Override
	public Iterator<String> iterator() {
	    return new Iterator<String>() {
	        StringTokenizer tokenizer = new StringTokenizer(documentText);

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

	public static final void main(String[] args) throws IOException {
	    Document d = Document.readFromFile(new File("data/dracula.txt"));
	    
	    
	    for (String token : d) {
	        System.out.println(token);
	    }
	}

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
}
