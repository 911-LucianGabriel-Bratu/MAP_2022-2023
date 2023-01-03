package Model.Expression;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExp implements Exp {
    Value value;

    public ValueExp(Value value) {
        this.value = value;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap heap) {
        return this.value;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        return value.getType();
    }

    public Exp deepCopy(){
        return new ValueExp(value);
    }

    public String toString() {
        return this.value.toString();
    }
}
