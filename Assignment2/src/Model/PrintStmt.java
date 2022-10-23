package Model;

public class PrintStmt implements IStmt{
    Exp exp;

    public String toString(){
        return "print(" +exp.toString()+")");
    }

    @Override
    public PrgState execute(PrgState state) throws MyException{
        return state;
    }
}
