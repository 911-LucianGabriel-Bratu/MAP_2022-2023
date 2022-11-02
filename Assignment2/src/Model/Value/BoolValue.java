package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value {
    boolean val;

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
