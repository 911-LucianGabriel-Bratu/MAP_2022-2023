package Model.ADTs;

import Exceptions.ADTException;

import java.util.List;

public interface MyIStack<T>{
    boolean isEmpty();
    T pop() throws ADTException;
    void push(T v);
    int size();
    List<T> getReversed();
}
