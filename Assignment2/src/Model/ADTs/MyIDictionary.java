package Model.ADTs;

import Exceptions.ADTException;

public interface MyIDictionary<K, E> {
    void clear();
    void insert(K key, E elem);
    E remove(K key) throws ADTException;
    E lookup(K key) throws ADTException;
    boolean isDefined(K key);
    void update(K key, E elem) throws ADTException;
    int size();
}
