package Model.Expression;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Value.Value;

public class ValueExp implements Exp {
    Value value;

    public ValueExp(Value value) {
        this.value = value;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap heap) {
        return this.value;
    }

    public Exp deepCopy(){
        return new ValueExp(value);
    }

    public String toString() {
        return this.value.toString();
    }
}
