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

public class NewStmt implements IStmt{
    String varName;
    Exp expression;

    public NewStmt(String varName, Exp expression){
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(symTable.isDefined(varName)){
            Value varValue = symTable.lookup(varName);
            if(varValue.getType() instanceof RefType){
                Value evaluatedValue = expression.eval(symTable, heap);
                Type locationType = ((RefValue) varValue).getLocationType();
                if(locationType.equals(evaluatedValue.getType())){
                    int newPos = heap.add(evaluatedValue);
                    symTable.insert(varName, new RefValue(newPos, locationType));
                    state.setSymTable(symTable);
                    state.setHeap(heap);
                }
                else{
                    throw new StatementExecutionException(varName + " is not of " + evaluatedValue.getType());
                }
            }
            else{
                throw new StatementExecutionException(varName + " is not of RefType");
            }
        }
        else{
            throw new StatementExecutionException(varName + " is not in the symbol table");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        Type typVar = typeEnv.lookup(varName);
        Type typExp = expression.typecheck(typeEnv);
        if(typVar.equals(new RefType(typExp))){
            return typeEnv;
        }
        else{
            throw new StatementExecutionException("NewStmt - right hand side and left hand side operands have different types");
        }
    }

    @Override
    public IStmt deepCopy() {
        return new NewStmt(varName, expression.deepCopy());
    }

    public String toString(){
        return "New(" + varName + ", " + expression.toString() + ")";
    }
}
