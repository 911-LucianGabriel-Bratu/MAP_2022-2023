package Model;

public class BoolType implements Type{
    public boolean equals(Object another){
        return another instanceof IntType;
    }
    public String toString() {
        return "boolean";
    }
}
