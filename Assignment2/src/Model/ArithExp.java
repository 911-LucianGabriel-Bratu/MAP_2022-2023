package Model;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    char op;

    public ArithExp(Exp e1, Exp e2, char op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
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
                    if (n2 == 0) throw new MyException("division by zero");
                } else return new IntValue(n1 / n2);
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
        return null;
    }

    public Exp deepCopy(){
        return new ArithExp(e1.deepCopy(), e2.deepCopy(), op);
    }

    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
