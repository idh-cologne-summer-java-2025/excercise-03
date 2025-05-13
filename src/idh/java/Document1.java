package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Document1 implements Iterable<String>{
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
		Iterator<String> iterator = d.iterator();
//		while(iterator.hasNext())
//		{
//			System.out.println(iterator.next());
//		}
		for(String word : d) {
			System.out.println(word);
		}
		
		
	}

	@Override
	public Iterator<String> iterator() {
		StringTokenizer str = new StringTokenizer(documentText);
		List<String> list = new ArrayList<String>();
		while (str.hasMoreTokens()) {
			list.add(str.nextToken());
		}
		return list.iterator();
	}

	
	
}