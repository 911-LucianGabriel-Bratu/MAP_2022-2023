package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt first, IStmt snd){
        this.first = first;
        this.snd = snd;
    }

    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        state.setStk(stk);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        return snd.typecheck(first.typecheck(typeEnv));
    }

    public IStmt deepCopy(){
        return new CompStmt(first.deepCopy(), snd.deepCopy());
    }

    @Override
    public String toString(){
        return "("+first.toString() + "|" + snd.toString()+")";
    }
}
