package Model.Expression;

import Exceptions.ADTException;
import Model.ADTs.MyIDictionary;
import Model.Value.Value;

public class VarExp implements Exp {
    String key;

    public VarExp(String key) {
        this.key = key;
    }

    public Value eval(MyIDictionary<String,Value> tbl)  throws ADTException {
        return tbl.lookup(key);
    }

    public Exp deepCopy(){
        return new VarExp(key);
    }

    public String toString() {
        return key;
    }
}
