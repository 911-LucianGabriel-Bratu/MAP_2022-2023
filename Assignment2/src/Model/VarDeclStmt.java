package Model;

public class VarDeclStmt implements IStmt{
    String name;
    Type typ;

    public PrgState execute(PrgState state) throws MyException{
        return state;
    }
}
