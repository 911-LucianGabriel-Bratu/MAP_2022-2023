package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.Expression.Exp;
import Model.ADTs.MyIList;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws ADTException, ExpressionEvaluationException {
        MyIList<Value> out = state.getOut();
        out.add(exp.eval(state.getSymTable(), state.getHeap()));
        state.setOut(out);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    public IStmt deepCopy(){
        return new PrintStmt(exp.deepCopy());
    }

    public String toString(){
        return "print(" +exp.toString()+")";
    }
}
