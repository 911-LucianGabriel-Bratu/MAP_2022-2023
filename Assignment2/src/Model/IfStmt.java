package Model;

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

    public PrgState execute(PrgState state) throws MyException {
        Value result = this.exp.eval(state.getSymTable());
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
            return state;
        }
        else {
            throw new MyException("there is no boolean expression in the if statement");
        }
    }

    public IStmt deepCopy(){
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

}
