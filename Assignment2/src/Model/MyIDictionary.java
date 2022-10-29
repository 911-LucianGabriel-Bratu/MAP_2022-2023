package Model;

public interface MyIDictionary<K, E> {
    void clear();
    void insert(K key, E elem);
    E remove(K key) throws MyException;
    E lookup(K key) throws MyException;
    boolean isDefined(K key);
    void update(K key, E elem) throws MyException;
    int size();
}
