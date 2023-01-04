package Model.ADTs;

import Exceptions.ADTException;
import Model.Value.Value;

import java.util.HashMap;
import java.util.Set;

public class MyHeap implements MyIHeap{
    HashMap<Integer, Value> heap;
    Integer freeLocationValue;

    public int newValue() {
        freeLocationValue += 1;
        while(freeLocationValue == 0 || heap.containsKey(freeLocationValue)){
            freeLocationValue += 1;
        }
        return freeLocationValue;
    }

    public MyHeap(){
        this.heap = new HashMap<>();
        freeLocationValue = 1;
    }

    @Override
    public int getFreeValue() {
        return freeLocationValue;
    }

    @Override
    public HashMap<Integer, Value> getContent() {
        return heap;
    }

    @Override
    public void setContent(HashMap<Integer, Value> newHashMap) {
        this.heap = newHashMap;
    }

    @Override
    public int add(Value value) {
        heap.put(freeLocationValue, value);
        Integer returnValue = freeLocationValue;
        freeLocationValue = newValue();
        return returnValue;
    }

    @Override
    public void update(Integer position, Value value) throws ADTException {
        if(!heap.containsKey(position)){
            throw new ADTException(Integer.toString(position) + " is not a location in the heap");
        }
        heap.put(position, value);
    }

    @Override
    public Value get(Integer position) throws ADTException {
        if(!heap.containsKey(position)){
            throw new ADTException(Integer.toString(position) + " is not a location in the heap");
        }
        return this.heap.get(position);
    }

    @Override
    public boolean containsKey(Integer position) {
        return this.heap.containsKey(position);
    }

    @Override
    public void remove(Integer key) throws ADTException {
        if(!containsKey(key)){
            throw new ADTException(Integer.toString(key) + " is not defined in the heap");
        }
        freeLocationValue = key;
        this.heap.remove(key);
    }

    @Override
    public Set<Integer> keySet() {
        return heap.keySet();
    }

    public String toString(){
        return this.heap.toString();
    }
}
