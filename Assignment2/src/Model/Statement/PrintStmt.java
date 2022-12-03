package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Model.Expression.Exp;
import Model.ADTs.MyIList;
import Model.PrgState.PrgState;
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

    public IStmt deepCopy(){
        return new PrintStmt(exp.deepCopy());
    }

    public String toString(){
        return "print(" +exp.toString()+")";
    }
}
