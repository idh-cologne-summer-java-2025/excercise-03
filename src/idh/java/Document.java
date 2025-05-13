/*
- make Document class iterable X
- split String into tokens using StringTokenizer X
- iterate over Tokens using Iterators + put out on console X
*/
package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable<String>{
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
	
	public static final void main(String[] args) throws IOException {
		Document d = Document.readFromFile(new File("data/dracula.txt"));
		StringTokenizer tokenizer = new StringTokenizer(d.getDocumentText());
		List<String> l = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			String tokens = tokenizer.nextToken();
			l.add(tokens);
		}
		for (String string : l) {
			System.out.println(string);
		}
	}

	@Override
	public Iterator<String> iterator() {
		// the Tokenizer could/should rather be put in here
		return this.iterator();
	}
}