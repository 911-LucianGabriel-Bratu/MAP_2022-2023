package Model;

import java.util.Objects;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    String op;

    public LogicExp(Exp e1, Exp e2, String op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Value eval(MyIDictionary<String,Value> tbl) throws MyException{
        Value v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl);
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
                throw new MyException("second operand is not a boolean");
        }
        else
            throw new MyException("first operand is not a boolean");
        return null;
    }

    public Exp deepCopy(){
        return new LogicExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
