package Model.Type;

import Model.Value.BoolValue;
import Model.Value.Value;

public class BoolType implements Type {
    public boolean equals(Type anotherType){
        return anotherType instanceof BoolType;
    }
    public Value defaultValue(){
        return new BoolValue(false);
    }
    public Type deepCopy(){
        return new BoolType();
    }
    public String toString() {
        return "boolean";
    }
}
