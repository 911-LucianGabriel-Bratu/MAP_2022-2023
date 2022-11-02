package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.PrgState.PrgState;

public interface IStmt {
    public PrgState execute(PrgState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException;
    IStmt deepCopy();
}
