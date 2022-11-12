package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value {
    int val;

    @Override
    public boolean equals(Object anotherValue){
        if(!(anotherValue instanceof IntValue)){
            return false;
        }
        IntValue castedInt = (IntValue) anotherValue;
        return this.val == castedInt.val;
    }

    public IntValue(int v){
        this.val = v;
    }

    public int getVal() {
        return val;
    }

    public Type getType(){
        return new IntType();
    }

    public Value deepCopy(){
        return new IntValue(val);
    }

    public String toString(){
        return Integer.toString(this.val);
    }
}
