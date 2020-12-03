package ua.edu.ucu.tries;

public class TrieNode {
    final int R = 26;
    TrieNode[] children;
    boolean isWordEnd;
    char value;

    TrieNode() {
        children = new TrieNode[R];
    }

    TrieNode(char value) {
        this.value = value;
        children = new TrieNode[26];
    }
}
