package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.Type;

public interface IStmt {
    public PrgState execute(PrgState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException;
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException;
    IStmt deepCopy();
}
