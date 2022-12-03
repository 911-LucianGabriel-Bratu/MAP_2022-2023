package Model.Expression;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    char op;

    public ArithExp(Exp e1, Exp e2, char op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws ExpressionEvaluationException, ADTException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == '+') return new IntValue(n1 + n2);
                if (op == '-') return new IntValue(n1 - n2);
                if (op == '*') return new IntValue(n1 * n2);
                if (op == '/') {
                    if (n2 == 0) throw new ExpressionEvaluationException("division by zero");
                } else return new IntValue(n1 / n2);
            } else
                throw new ExpressionEvaluationException("second operand is not an integer");
        } else
            throw new ExpressionEvaluationException("first operand is not an integer");
        return null;
    }

    public Exp deepCopy(){
        return new ArithExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
