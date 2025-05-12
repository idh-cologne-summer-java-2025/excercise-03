package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable<String>{
	String documentText;
	 ArrayList<String> al;
	 Iterator<String> it;

	public Document readFromFile(File f) throws IOException {
		FileReader fileReader = new FileReader(f);
		int ch;
		StringBuilder b = new StringBuilder();
		while( (ch = fileReader.read()) != -1 ) {
			b.append((char) ch);
		}
		fileReader.close();
		this.documentText = b.toString();
		 al = new ArrayList<>(Arrays.asList(documentText.split("[,\\.\\s+]")));
		 it = al.iterator();
		 
		return this;
	}
	
	public String getDocumentText() {
		return documentText;
	}

	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
	
	

	@Override
	public Iterator<String> iterator() {
		
		return it;
		
		
	    


	
	    
	    		
	    	}
	
	}

