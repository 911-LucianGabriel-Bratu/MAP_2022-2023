package Model.ADTs;

import Exceptions.ADTException;
import java.util.Set;

public interface MyIDictionary<K, E> {
    void clear();
    void insert(K key, E elem);
    E remove(K key) throws ADTException;
    E lookup(K key) throws ADTException;
    boolean isDefined(K key);
    void update(K key, E elem) throws ADTException;
    Set<K> keySet();
    int size();
}
