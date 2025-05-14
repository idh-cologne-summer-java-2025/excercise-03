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
