package Model;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T>{
    List<T> list;

    public MyList(){
        this.list = new ArrayList<>();
    }

    public void add(T v){
        this.list.add(v);
    }

    public void remove(int pos) throws MyException {
        if(pos < 0 || pos > this.list.size()){
            throw new MyException("non-existent position");
        }
        this.list.remove(pos);
    }

    public void remove(T v) throws MyException {
        if(!this.list.remove(v)){
            throw new MyException("non-existent element");
        }
    }

    public int size(){
        return this.list.size();
    }

    public String toString(){
        return list.toString();
    }
}
