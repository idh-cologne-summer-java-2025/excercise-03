package idh.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Document implements Iterable<String> {
	String documentText;

	public static Document readFromFile(File f) throws IOException 
	{
		FileReader fileReader = new FileReader(f); // Opens the file for reading. Other than the InputStreamReader, FileReader is specified for text files, which use UTF-8. ISR is more flexible than that.
		int ch;
		StringBuilder b = new StringBuilder();
		while( (ch = fileReader.read()) != -1 ) // Reads each character until the end of the file (no char has the value Unicode value -1)
		{ 
			b.append((char) ch); 
			/* "read()" returns an int representing the Unicode value of the character read.
			* We cast it to "char" because StringBuilder.append(char) expects a char,
			* and we're only appending valid characters.*/
		}
		fileReader.close(); // Closes the file after reading

		// Creates a new Document object and sets its text to the read content
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
		for(String token : d) {
			System.out.println(token);
		}
		/*Iterates over the Document using its custom iterator,
		* which splits the text of d into words (tokens) using whitespace as delimiter
		* and prints each word to the console, using the temporary variable "token" in which the current String object (word) is stored, per loop run.*/
	}

	@Override
	public Iterator<String> iterator() {
		StringTokenizer st = new StringTokenizer(documentText);// Uses StringTokenizer to split the document text into tokens (words)
		List<String> list = new ArrayList<String>(); // Stores the tokens in a list
		while(st.hasMoreTokens()) {
			list.add(st.nextToken()); // Adds each token to the list
		}
		return list.iterator(); // Returns an iterator over the list of tokens
	}
	
	/* Inner class providing an alternative way to iterate over the document's tokens.
	* Although the Document class already implements Iterable by returning a List iterator,
	* this class offers a more memory-efficient, on-demand token iteration.
	* It avoids storing all tokens in a list and instead uses a StringTokenizer directly during iteration, as it is build like a List.
	* This could be useful for very large documents, where creating a full list of tokens would consume too much saving space.
	* The List solution is definently more easy though, for now. It is alle we need.*/
	
	class DocumentIterator implements Iterator<String> {

		StringTokenizer st;
		public DocumentIterator(String s) {
			 st = new StringTokenizer(s);

		}
		
		@Override
		public boolean hasNext() {
			return st.hasMoreTokens();
		}

		@Override
		public String next() {
			return st.nextToken();
		}
		
	}
	
}
