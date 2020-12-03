package ua.edu.ucu.immutable;

public interface ImmutableList {

    ImmutableList add(Object e);
    //додає елемент у кінець колекції

    ImmutableList add(int index, Object e);
    //додає елемент до колекції за індексом,
    // та кидає виключну ситуацію, якщо індекс
    // виходить за межі колекції

    ImmutableList addAll(Object[] c);
    //додає масив елементів у кінець колекції

    ImmutableList addAll(int index, Object[] c);
    // додає масив елементів починаючи з зазначеного
    // індекса, та кидає виключну ситуацію, якщо
    // індекс виходить за межі колекції

    Object get(int index) throws IllegalAccessException;
    //повертає елемент за індексом, та кидає виключну
    // ситуацію, якщо індекс виходить за межі колекції

    ImmutableList remove(int index) throws IllegalAccessException;
    //видаляє елемент за індексом, та кидає виключну
    // ситуацію, якщо індекс виходить за межі колекції

    ImmutableList set(int index, Object e);
    //змінює значення елементу за індексом, та кидає
    // виключну ситуацію, якщо індекс виходить за межі колекції

    int indexOf(Object e);
    //шукає індекс елемента (повертає індекс першого
    // який знайшов, або -1 у випадку відсутності)

    int size(); //розмір колекції

    ImmutableList clear(); //очищує вміст колекції

    boolean isEmpty(); //якщо у колеції нема
    // елементів то повертає true

    Object[] toArray(); //перетворює колекцію до
    // масиву обєктів

    @Override
    String toString(); //повертає рядок, де через
    // кому відображаютсься елементи колекції
}
