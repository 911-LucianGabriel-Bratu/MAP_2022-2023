package Model.ADTs;

import Exceptions.ADTException;

import java.util.*;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;

    public MyStack(){
        this.stack = new Stack<>();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public T pop() throws ADTException {
        if(stack.isEmpty()){
            throw new ADTException("Stack is empty");
        }
        return this.stack.pop();
    }
    public void push(T v){
        this.stack.push(v);
    }

    public int size(){
        return this.stack.size();
    }
    @Override
    public List<T> getReversed(){
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }

    public String toString(){
        return this.stack.toString();
    }
}
