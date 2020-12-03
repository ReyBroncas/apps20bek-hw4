package ua.edu.ucu.immutable;

public class ImmutableLinkedList implements ImmutableList {
    private Node tail;
    public Node head;
    private int listSize = 0;

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public ImmutableLinkedList(Object[] values) {
        int size = values.length;
        Node currNode = new Node(values[0]);
        if (size == 1) {
            head = currNode;
            tail = head;
            head.setNext(null);
            head.setPrev(null);
        } else {
            Node newHead = currNode;

            for (int i = 1; i < size; ++i) {
                Node newNode = new Node(values[i]);
                currNode.setNext(newNode);
                newNode.setPrev(currNode);
                currNode = currNode.getNext();
            }

            this.head = newHead;
            this.tail = currNode;
        }
        this.listSize = size;

    }

    private void checkBounds(int i) {
        if (i < 0 || i >= this.listSize) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return add(-1, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(-1, c);
    }

    // Index -1 reserved for last index placement
    @Override
    public ImmutableList addAll(int index, Object[] values) {
        if (index == -1) {
            index = listSize;
        } else {
            checkBounds(index);
        }
        int size = values.length;
        int newLen = size + listSize;
        Object[] currArray = this.toArray();
        Object[] newArray = new Object[listSize + size];
        int j = 0;
        for (int i = 0; i < newLen; ++i) {
            if (i < index) {
                newArray[i] = currArray[i];
            } else if (i >= index && j < values.length) {
                newArray[i] = values[j];
                ++j;
            } else {
                newArray[i] = currArray[i - j];
            }
        }


        return new ImmutableLinkedList(newArray);
    }

    @Override
    public Object get(int index) throws IllegalAccessException {
        if (isEmpty()) {
            throw new IllegalAccessException();
        }
        checkBounds(index);
        Node newHead = this.head;
        for (int i = 0; i < index; ++i) {
            newHead = newHead.getNext();
        }
        return newHead.getValue();
    }

    @Override
    public ImmutableList remove(int index) throws IllegalAccessException {
        if (isEmpty()) {
            throw new IllegalAccessException();
        }
        checkBounds(index);
        Object[] currArray = this.toArray();
        Object[] newArray = new Object[listSize];
        if (index >= 0) {
            System.arraycopy(currArray, 0, newArray, 0, index);
        }
        if (listSize - 1 - index >= 0) {
            System.arraycopy(currArray, index + 1,
                    newArray, index, listSize - 1 - index);
        }
        return new ImmutableLinkedList(newArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (isEmpty()) {
            return new ImmutableLinkedList(new Object[]{e});
        }
        checkBounds(index);
        Object[] outputArray = this.toArray();
        outputArray[index] = e;
        return new ImmutableLinkedList(outputArray);
    }

    @Override
    public int indexOf(Object e) {
        Node currNode = this.head;
        for (int i = 0; i != listSize; ++i) {
            if (currNode.getValue().equals(e)) {
                return i;
            }
            currNode = currNode.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] outputArr = new Object[listSize];
        Node currNode = head;
        for (int i = 0; i < listSize; ++i) {
            outputArr[i] = currNode.getValue();
            currNode = currNode.getNext();
        }
        return outputArr;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("[ ");
        for (Object e : this.toArray()) {
            out.append(e).append(" ");
        }
        out.append("]");
        return out.toString();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);

    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public Object getFirst() throws IllegalAccessException {
        return this.get(0);
    }

    public Object getLast() throws IllegalAccessException {
        return this.get(listSize - 1);
    }

    public ImmutableLinkedList removeFirst() throws IllegalAccessException {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() throws IllegalAccessException {
        return (ImmutableLinkedList) remove(listSize - 1);
    }

}
