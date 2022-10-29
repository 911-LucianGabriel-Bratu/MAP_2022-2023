package Model;

public class IntType implements Type{
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
