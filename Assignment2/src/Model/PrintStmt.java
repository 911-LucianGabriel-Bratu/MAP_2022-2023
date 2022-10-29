package Model;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(Exp exp){
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIList<Value> out = state.getOut();
        out.add(exp.eval(state.getSymTable()));
        state.setOut(out);
        return state;
    }

    public IStmt deepCopy(){
        return new PrintStmt(exp.deepCopy());
    }

    public String toString(){
        return "print(" +exp.toString()+")";
    }
}
