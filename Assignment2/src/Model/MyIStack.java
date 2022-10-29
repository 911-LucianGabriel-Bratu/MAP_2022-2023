package Model;

public interface MyIStack <T>{
    T pop() throws MyException;
    void push(T v);
    int size();
}
