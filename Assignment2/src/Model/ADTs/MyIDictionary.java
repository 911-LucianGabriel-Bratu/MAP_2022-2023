package Model.ADTs;

import Exceptions.ADTException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public interface MyIDictionary<K, E> {
    void clear();
    void insert(K key, E elem);
    E remove(K key) throws ADTException;
    HashMap<K,E> getDictionary();
    E lookup(K key) throws ADTException;
    boolean isDefined(K key);
    void update(K key, E elem) throws ADTException;
    Set<K> keySet();
    Collection<E> values();
    int size();
}
