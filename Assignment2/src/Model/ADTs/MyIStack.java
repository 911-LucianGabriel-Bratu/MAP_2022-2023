package Model.ADTs;

import Exceptions.ADTException;

public interface MyIStack<T>{
    boolean isEmpty();
    T pop() throws ADTException;
    void push(T v);
    int size();
}
