package Model;

public class NopStmt implements IStmt{

    public PrgState execute(PrgState state) throws MyException{
        return null;
    }

    public IStmt deepCopy(){
        return new NopStmt();
    }

    public String toString() {
        return "NopStatement";
    }
}
