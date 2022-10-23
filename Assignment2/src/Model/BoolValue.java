package Model;

public class BoolValue implements Value{
    boolean val;

    public BoolValue(boolean v){
        this.val = v;
    }

    public boolean getVal() {
        return val;
    }

    public String toString(){
        return Boolean.toString(this.val);
    }

    public Type getType(){
        return new BoolType();
    }
}
