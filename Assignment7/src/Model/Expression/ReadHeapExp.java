package Model.Expression;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Type.RefType;
import Model.Type.Type;
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
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        Type typ = expression.typecheck(typeEnv);
        if(typ instanceof RefType){
            RefType reft = (RefType) typ;
            return reft.getInner();
        }
        else{
            throw new ExpressionEvaluationException("the rH argument is not a RefType");
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
