package Model;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    Stack<T> stack;

    public MyStack(){
        this.stack = new Stack<>();
    }

    public T pop() throws MyException{
        if(stack.isEmpty()){
            throw new MyException("Stack is empty");
        }
        return this.stack.pop();
    }
    public void push(T v){
        this.stack.push(v);
    }

    public int size(){
        return this.stack.size();
    }

    public String toString(){
        return this.stack.toString();
    }
}
