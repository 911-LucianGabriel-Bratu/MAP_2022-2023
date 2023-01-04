package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.Expression.Exp;
import Model.ADTs.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "(IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + "))";
    }

    public PrgState execute(PrgState state) throws ExpressionEvaluationException, ADTException, StatementExecutionException {
        Value result = this.exp.eval(state.getSymTable(), state.getHeap());
        if(result instanceof BoolValue boolResult) {
            IStmt statement;
            if (boolResult.getVal()) {
                statement = thenS;
            } else {
                statement = elseS;
            }
            MyIStack<IStmt> stack = state.getStk();
            stack.push(statement);
            state.setStk(stack);
            return null;
        }
        else {
            throw new StatementExecutionException("there is no boolean expression in the if statement");
        }
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        Type typExp = exp.typecheck(typeEnv);
        if(typExp.equals(new BoolType())){
            thenS.typecheck(typeEnv.deepcopy());
            elseS.typecheck(typeEnv.deepcopy());
            return typeEnv;
        }
        else {
            throw new StatementExecutionException("if condition not of BoolType");
        }
    }

    public IStmt deepCopy(){
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

}
