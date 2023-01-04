package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Expression.Exp;
import Model.ADTs.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt {
    String key;
    Exp exp;

    public AssignStmt(String key, Exp exp) {
        this.key = key;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementExecutionException, ADTException, ExpressionEvaluationException {
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        if (symTbl.isDefined(key)){
            Value val = exp.eval(symTbl, state.getHeap());
            Type typId = (symTbl.lookup(key)).getType();
            if ((val.getType()).equals(typId)) {
                symTbl.update(key, val);
            }
            else{
                throw new StatementExecutionException("declared type of variable " + key + " and type of the assigned expression do not match");
            }
        }
        else throw new StatementExecutionException("the used variable" + key + " was not declared before");
        state.setSymTable(symTbl);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        Type typeVar = typeEnv.lookup(key);
        Type typeExp = exp.typecheck(typeEnv);
        if(typeVar.equals(typeExp)){
            return typeEnv;
        }
        else{
            throw new StatementExecutionException("assignstmt - types of the left hand side and right hand side operands differ");
        }
    }

    public IStmt deepCopy(){
        return new AssignStmt(key, exp.deepCopy());
    }

    public String toString() {
        return String.format("%s = %s", key, exp.toString());
    }
}
