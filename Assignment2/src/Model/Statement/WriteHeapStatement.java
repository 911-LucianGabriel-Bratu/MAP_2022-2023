package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

import java.sql.Statement;

public class WriteHeapStatement implements IStmt {
    String varName;
    Exp expression;

    public WriteHeapStatement(String varName, Exp expression){
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(symTable.isDefined(varName)){
            Value val = symTable.lookup(varName);
            if(val.getType() instanceof RefType){
                RefValue refVal = (RefValue) val;
                if(heap.containsKey(refVal.getAddress())){
                    Value evaluatedVal = expression.eval(symTable, heap);
                    if(evaluatedVal.getType().equals(refVal.getLocationType())){
                        heap.update(refVal.getAddress(), evaluatedVal);
                        state.setHeap(heap);
                    }
                    else{
                        throw new StatementExecutionException(evaluatedVal + " not of " + refVal.getLocationType().toString());
                    }
                }
                else{
                    throw new StatementExecutionException("The RefValue " + val + " is not defined on the heap");
                }
            }
            else{
                throw new StatementExecutionException(val + " not of RefType");
            }
        }
        else{
            throw new StatementExecutionException(varName + " is not defined on the SymbolTable");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        if (typeEnv.lookup(varName).equals(new RefType(expression.typecheck(typeEnv)))) {
            return typeEnv;
        }
        else{
            throw new StatementExecutionException("WriteHeapStmt - right hand side and left hand side operands have different types");
        }
    }

    public String toString(){
        return "wH(" + varName + ", " + expression.toString() + ")";
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }
}
