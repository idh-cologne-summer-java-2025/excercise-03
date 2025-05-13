package idh.java;

import java.util.Iterator;

public class Document implements Iterable<String> {
    private String documentText;

    // Konstruktor für das Dokument
    public Document(String text) {
        this.documentText = text;
    }

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    // Implementierung des Iterable-Interfaces
    @Override
    public Iterator<String> iterator() {
        return new TokenIterator(documentText);
    }
}
