package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
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
		
		Iterator<String> i = d.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}

	@Override
	public Iterator<String> iterator() {
		StringTokenizer sT = new StringTokenizer(documentText);
		List<String> al = new ArrayList<String>();
		while(sT.hasMoreTokens()) {
			al.add(sT.nextToken());
		}
		return al.iterator();
	}

	
	
}
