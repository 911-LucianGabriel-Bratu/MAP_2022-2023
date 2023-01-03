package Model.Expression;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap heap) throws ExpressionEvaluationException, ADTException;
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException, ADTException;
    Exp deepCopy();
}
