package ua.edu.ucu.tries;


import ua.edu.ucu.queue.Queue;

import java.util.ArrayList;
import java.util.List;

public class RWayTrie implements Trie {
    private final TrieNode root;
    private int size;

    public RWayTrie() {
        root = new TrieNode();
        size = 0;
    }


    public boolean hasNoChildren(TrieNode node) {
        boolean found = false;
        for (TrieNode child : node.children) {
            if (child != null) {
                found = true;
                break;
            }
        }
        return !found;
    }

    private int hashIndex(char c) {
        return c - 'a';
    }

    public void addDictionary(String... strings) {
        for (String word : strings) {
            this.add(new Tuple(word, word.length()));
            size++;
        }
    }

    private List<String> traverse(TrieNode node, String prefix){
        ArrPair tuple = new ArrPair(node, "" + node.value);
        List<String> result = new ArrayList<>();
        Queue queue = new Queue();
        queue.enqueue(new ArrPair(node, prefix));

        while (!queue.isEmpty()) {
            tuple = (ArrPair) queue.dequeue();

            for (TrieNode child : tuple.node.children) {
                if (child != null) {
                    queue.enqueue(new ArrPair(child, "" + tuple.value + child.value));
                }
            }
            if (tuple.node.isWordEnd) {
                result.add(tuple.value);
            }
        }
        return result;

    }

    @Override
    public void add(Tuple t) {
        TrieNode node = this.root;
        for (int i = 0; i < t.weight; ++i) {
            char ch = t.term.charAt(i);
            int index = hashIndex(ch);

            if (node.children[index] == null) {
                node.children[index] = new TrieNode(ch);
            }
            node = node.children[index];
        }
        node.isWordEnd = true;
    }

    @Override
    public boolean contains(String word) {
        TrieNode node = this.root;
        boolean found = true;

        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            int index = hashIndex(ch);

            if (node.children[index] == null) {
                found = false;
                break;
            }
            node = node.children[index];
        }
        return node.isWordEnd && found;
    }

    @Override
    public boolean delete(String word) {
        TrieNode node = this.root;
        TrieNode lastNode = node;
        char lastNodeChar = node.value;
        boolean found = true;

        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            int index = hashIndex(ch);

            if (node.children[index].isWordEnd) {
                lastNode = node;
                lastNodeChar = ch;
            }
            if (node.children[index] == null) {
                found = false;
                break;
            }
            node = node.children[index];
        }
        node.isWordEnd = false;
        size--;

        if (hasNoChildren(node)) {
            lastNode.children[hashIndex(lastNodeChar)] = null;
        }
        return found;
    }

    @Override
    public Iterable<String> words(){
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s){
        if (s.equals("")) {
            traverse(root, "");
        }
        TrieNode node = root;
        boolean found = true;
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            int index = hashIndex(ch);

            if (node.children[index] == null) {
                found = false;
                break;
            }
            tmp.append(ch);
            node = node.children[index];
        }
        if ((!found) || (node.isWordEnd && (hasNoChildren(node)))) {
            return null; // empty array
        } else {
            return traverse(node, tmp.toString());
        }
    }


    @Override
    public int size() {
        return size;
    }
}
