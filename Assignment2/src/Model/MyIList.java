package Model;

public interface MyIList<T> {
    void add(T v);
    void remove(int pos) throws MyException;
    void remove(T v) throws MyException;
    int size();
}
