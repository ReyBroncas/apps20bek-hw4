package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;

import java.util.ArrayList;
import java.util.List;

public class PrefixMatches {

    private final RWayTrie trie;

    public PrefixMatches(Trie trie) {
        this.trie = (RWayTrie) trie;
    }

    public void load(String... strings) throws IllegalAccessException {
        trie.addDictionary(strings);
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref){
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k){
        List<String> list = (List<String>) trie.wordsWithPrefix(pref);
        List<String> output = new ArrayList<>();
        for(int i = 0; i<k+1; ++i){
            output.add(list.get(i));
        }
        return output;
    }

    public int size() {
        return trie.size();
    }
}
