package Model.Type;

import Model.Value.IntValue;
import Model.Value.Value;

public class IntType implements Type {
    public boolean equals(Type anotherType){
        return anotherType instanceof IntType;
    }
    public Value defaultValue(){
        return new IntValue(0);
    }
    public Type deepCopy(){
        return new IntType();
    }
    public String toString() {
        return "int";
    }
}
