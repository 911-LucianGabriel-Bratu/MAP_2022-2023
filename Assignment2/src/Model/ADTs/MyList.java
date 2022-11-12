package Model.ADTs;

import Exceptions.ADTException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    List<T> list;

    public MyList(){
        this.list = new ArrayList<>();
    }

    public void add(T v){
        this.list.add(v);
    }

    public void remove(int pos) throws ADTException {
        if(pos < 0 || pos > this.list.size()){
            throw new ADTException("non-existent position");
        }
        this.list.remove(pos);
    }

    public void remove(T v) throws ADTException {
        if(!this.list.remove(v)){
            throw new ADTException("non-existent element");
        }
    }

    public int size(){
        return this.list.size();
    }

    public List<T> getList(){
        return this.list;
    }

    public String toString(){
        return list.toString();
    }
}
