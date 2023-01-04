package Model.ADTs;

import Exceptions.ADTException;

import java.util.List;

public interface MyIList<T> {
    void add(T v);
    void remove(int pos) throws ADTException;
    void remove(T v) throws ADTException;
    List<T> getList();
    int size();
}
