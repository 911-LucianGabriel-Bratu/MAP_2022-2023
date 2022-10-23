package Model;

public class IntValue implements Value{
    int val;

    public IntValue(int v){
        this.val = v;
    }

    public int getVal() {
        return val;
    }

    public String toString(){
        return Integer.toString(this.val);
    }

    public Type getType(){
        return new IntType();
    }
}
