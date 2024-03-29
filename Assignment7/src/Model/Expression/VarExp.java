package Model.Expression;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public class VarExp implements Exp {
    String key;

    public VarExp(String key) {
        this.key = key;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap heap)  throws ADTException {
        return tbl.lookup(key);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        return typeEnv.lookup(key);
    }

    public Exp deepCopy(){
        return new VarExp(key);
    }

    public String toString() {
        return key;
    }
}
