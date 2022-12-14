package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ADTs.MyStack;
import Model.PrgState.PrgState;
import Model.Value.Value;

import java.util.Map;

public class ForkStmt implements IStmt{
    private final IStmt statement;

    public ForkStmt(IStmt stmt){
        this.statement = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIStack<IStmt> newExeStk = new MyStack<>();
        newExeStk.push(statement);
        MyIDictionary<String, Value> newSymTbl = new MyDictionary<>();
        for(Map.Entry<String, Value> entry: state.getSymTable().getDictionary().entrySet()){
            newSymTbl.insert(entry.getKey(), entry.getValue().deepCopy());
        }
        return new PrgState(newExeStk, newSymTbl, state.getOut(), state.getFileTable(), state.getHeap());
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(statement.deepCopy());
    }

    @Override
    public String toString(){
        return "Fork(" + statement.toString() + ")";
    }
}
