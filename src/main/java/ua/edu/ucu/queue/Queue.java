package ua.edu.ucu.queue;

import ua.edu.ucu.immutable.Node;

public class Queue {
    private Node head;
    private Node tail;
    private int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Object peek() {
        return head.getValue();
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public Object dequeue() {
        Node tmp = head;
        head = head.getNext();
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return tmp.getValue();
    }

    public void enqueue(Object e) {
        Node newNode = new Node(e);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }
}
