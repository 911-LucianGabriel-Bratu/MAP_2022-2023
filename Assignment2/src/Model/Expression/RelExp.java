package Model.Expression;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ADTs.MyIDictionary;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

import java.util.Objects;

public class RelExp implements Exp{
    Exp exp1;
    Exp exp2;
    String operator;

    public RelExp(Exp exp1, Exp exp2, String op){
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.operator = op;
    }

    public Value eval(MyIDictionary<String, Value> tbl) throws ExpressionEvaluationException, ADTException{
        Value v1, v2;
        v1 = this.exp1.eval(tbl);
        if(v1.getType().equals(new IntType())){
            v2 = this.exp2.eval(tbl);
            if(v2.getType().equals(new IntType())){
                IntValue value1 = (IntValue) v1;
                IntValue value2 = (IntValue) v2;
                int x1, x2;
                x1 = value1.getVal();
                x2 = value2.getVal();
                if (Objects.equals(this.operator, "<"))
                    return new BoolValue(x1 < x2);
                else if (Objects.equals(this.operator, "<="))
                    return new BoolValue(x1 <= x2);
                else if (Objects.equals(this.operator, "=="))
                    return new BoolValue(x1 == x2);
                else if (Objects.equals(this.operator, "!="))
                    return new BoolValue(x1 != x2);
                else if (Objects.equals(this.operator, ">"))
                    return new BoolValue(x1 > x2);
                else if (Objects.equals(this.operator, ">="))
                    return new BoolValue(x1 >= x2);
            }
            else throw new ExpressionEvaluationException("second parameter is not an integer");
        }
        else throw new ExpressionEvaluationException("first parameter is not an integer");
        return null;
    }

    public Exp deepCopy(){
        return new RelExp(this.exp1, this.exp2, this.operator);
    }

    @Override
    public String toString(){
        return this.exp1.toString() + " " + this.operator + this.exp2.toString();
    }
}
