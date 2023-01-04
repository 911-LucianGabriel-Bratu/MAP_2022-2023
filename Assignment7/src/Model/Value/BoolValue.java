package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value {
    boolean val;
    @Override
    public boolean equals(Object anotherValue){
        if(!(anotherValue instanceof BoolValue)){
            return false;
        }
        BoolValue castedBool = (BoolValue) anotherValue;
        return this.val == castedBool.val;
    }

    public BoolValue(boolean v){
        this.val = v;
    }

    public boolean getVal() {
        return val;
    }

    public Value deepCopy(){
        return new BoolValue(val);
    }
    public Type getType(){
        return new BoolType();
    }

    public String toString(){
        return Boolean.toString(this.val);
    }
}
