package ua.edu.ucu.immutable;

public class Node {
    private Object value;
    private Node next;
    private Node prev;

    public Node(Object e) {
        this.value = e;
    }

    public void setNext(Node e) {
        this.next = e;
    }

    public void setPrev(Node e) {
        this.prev = e;
    }

    public void setValue(Object e) {
        this.value = e;
    }

    public Object getValue() {
        return this.value;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getPrev() {
        return this.prev;
    }
}