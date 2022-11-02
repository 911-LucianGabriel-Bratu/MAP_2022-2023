package Model.ADTs;

import Exceptions.ADTException;

import java.util.HashMap;

public class MyDictionary<K,E> implements MyIDictionary<K,E> {
    HashMap<K, E> dictionary;

    public MyDictionary(){
        this.dictionary = new HashMap<>();
    }

    public void clear() {
        this.dictionary.clear();
    }

    public void insert(K key, E elem) {
        this.dictionary.put(key, elem);
    }

    public E remove(K key) throws ADTException {
        if (!isDefined(key)) {
            throw new ADTException(key + " is not defined");
        }
        return this.dictionary.remove(key);
    }

    public E lookup(K key) throws ADTException {
        if (!isDefined(key)) {
            throw new ADTException(key + " is not defined");
        }
        return this.dictionary.get(key);
    }

    public boolean isDefined(K key) {
        return dictionary.containsKey(key);
    }

    public void update(K key, E elem) throws ADTException {
        if (!isDefined(key)) {
            throw new ADTException(key + " is not defined");
        }
        this.dictionary.replace(key, elem);
    }

    public int size() {
        return this.dictionary.size();
    }

    public String toString() {
        return this.dictionary.toString();
    }
}
