package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.Type;

public class NopStmt implements IStmt {

    public PrgState execute(PrgState state) {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        return typeEnv;
    }

    public IStmt deepCopy(){
        return new NopStmt();
    }

    public String toString() {
        return "NopStatement";
    }
}
