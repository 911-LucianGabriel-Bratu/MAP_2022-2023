package Model;

public class BoolType implements Type{
    public boolean equals(Type anotherType){
        return anotherType instanceof IntType;
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
