package Model;

public class NopStmt implements IStmt{

    public PrgState execute(PrgState state) throws MyException{
        return state;
    }
}
