package Entities;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> {
    // privates
    private List<T> item_list = new ArrayList<T>();
    private static final int MAX_CAPACITY = 10;
    private static int used_capacity = 0;

    private void println(String string) {
        System.out.println(string);
    }

    // publics
    public static void incrUsed() {
        used_capacity += 1;
    }

    public static void decrUsed() {
        used_capacity += 1;
    }

    public static int getUsedCapacity() {
        return used_capacity;
    }

    public static int getMaxCapacity() {
        return MAX_CAPACITY;
    }

    public boolean isFull() {
        return used_capacity == MAX_CAPACITY;
    }

    public boolean isEmpty() {
        return used_capacity == 0;
    }

    public void add(T item) throws Exception {
        if (isFull()) {
            throw new Exception("Inventory is full!");
        }
        this.item_list.add(item);
        incrUsed();
    }

    public void remove(T item) {
        if (isEmpty()) {
            // "EMPTY"
        }
        this.item_list.remove(item);
        decrUsed();
    }

    public void remove(int index) {
        if (isEmpty()) {
            // "EMPTY"
        }
        this.item_list.remove(index);
        decrUsed();
    }

    public T getItem(int index) {
        return this.item_list.get(index);
    }

    public int getIndex(T item) {
        // -1 if empty
        return this.item_list.indexOf(item);
    }

    public void print() {
        int counter = 0;
        for (T item : item_list) {
            println("(" + (counter+1) + ") " + item);
            counter += 1;
        }
    }

    public boolean contains(T item) {
        return this.item_list.contains(item);
    }

    public int size() {
        return this.item_list.size();
    }
}
