package Model.Expression;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;

import java.util.Objects;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    String op;

    public LogicExp(Exp e1, Exp e2, String op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap heap) throws ExpressionEvaluationException, ADTException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = b1.getVal();
                n2 = b2.getVal();
                if (Objects.equals(this.op, "and")) {
                    return new BoolValue(n1 && n2);
                }
                if (Objects.equals(this.op, "or")) {
                    return new BoolValue(n1 || n2);
                }
            }
            else
                throw new ExpressionEvaluationException("second operand is not a boolean");
        }
        else
            throw new ExpressionEvaluationException("first operand is not a boolean");
        return null;
    }

    public Exp deepCopy(){
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
