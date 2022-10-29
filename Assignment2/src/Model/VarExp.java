package Model;

public class VarExp implements Exp{
    String key;

    public VarExp(String key) {
        this.key = key;
    }

    public Value eval(MyIDictionary<String,Value> tbl)  throws MyException {
        return tbl.lookup(key);
    }

    public Exp deepCopy(){
        return new VarExp(key);
    }

    public String toString() {
        return key;
    }
}
