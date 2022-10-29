package Model;

public class IntValue implements Value{
    int val;

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
