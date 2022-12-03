package Model.Expression;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Value.RefValue;
import Model.Value.Value;

public class ReadHeapExp implements Exp{
    Exp expression;

    public ReadHeapExp(Exp exp){
        this.expression = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value = expression.eval(tbl, heap);
        if(value instanceof RefValue){
            RefValue refValue = (RefValue) value;
            if(heap.containsKey(refValue.getAddress())){
                return heap.get(refValue.getAddress());
            }
            else {
                throw new ExpressionEvaluationException("Address not defined on the heap");
            }
        }
        else{
            throw new ExpressionEvaluationException(value.toString() + " is not of RefType");
        }
    }

    @Override
    public Exp deepCopy() {
        return new ReadHeapExp(this.expression.deepCopy());
    }

    public String toString(){
        return "rH(" + expression.toString() + ")";
    }
}
