package Model.ADTs;

import Exceptions.ADTException;

public interface MyIList<T> {
    void add(T v);
    void remove(int pos) throws ADTException;
    void remove(T v) throws ADTException;
    int size();
}
