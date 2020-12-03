package ua.edu.ucu.tries;

public interface Trie {

    public void add(Tuple word);

    public boolean contains(String word);

    public boolean delete(String word);

    public Iterable<String> words() throws IllegalAccessException;

    public Iterable<String> wordsWithPrefix(String pref) throws IllegalAccessException;
    
    public int size();
}
