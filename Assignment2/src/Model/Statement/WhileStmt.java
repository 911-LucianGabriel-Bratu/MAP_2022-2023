package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class WhileStmt implements IStmt{
    Exp expression;
    IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt){
        this.expression = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value val = expression.eval(state.getSymTable(), state.getHeap());
        MyIStack<IStmt> stack = state.getStk();
        if(!(val.getType().equals(new BoolType()))){
            throw new StatementExecutionException(val + " is not of BoolType");
        }
        if(!(val instanceof BoolValue)){
            throw new StatementExecutionException(val + " is not a BoolValue");
        }
        BoolValue boolValue = (BoolValue) val;
        if(boolValue.getVal()){
            stack.push(this.deepCopy());
            stack.push(stmt);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        Type typExp = expression.typecheck(typeEnv);
        if(typExp.equals(new BoolType())){
            stmt.typecheck(typeEnv.deepcopy());
            return typeEnv;
        }
        else{
            throw new StatementExecutionException("the condition of while is not of BoolType");
        }
    }

    public String toString(){
        return "While(" + expression.toString() + ") { " + stmt.toString() + " }";
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(this.expression.deepCopy(), this.stmt.deepCopy());
    }
}
