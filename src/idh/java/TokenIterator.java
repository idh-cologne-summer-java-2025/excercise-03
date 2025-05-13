package idh.java;

import java.util.Iterator;
import java.util.StringTokenizer;

public class TokenIterator implements Iterator<String> {
    private StringTokenizer tokenizer;

    // Konstruktor: StringTokenizer initialisieren
    public TokenIterator(String text) {
        this.tokenizer = new StringTokenizer(text);
    }

    // Überprüfen, ob es noch mehr Tokens gibt
    @Override
    public boolean hasNext() {
        return tokenizer.hasMoreTokens();
    }

    // Nächstes Token holen
    @Override
    public String next() {
        return tokenizer.nextToken();
    }
}
