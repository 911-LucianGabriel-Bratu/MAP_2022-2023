package Model.Statement;

import Model.PrgState.PrgState;

public class NopStmt implements IStmt {

    public PrgState execute(PrgState state) {
        return null;
    }

    public IStmt deepCopy(){
        return new NopStmt();
    }

    public String toString() {
        return "NopStatement";
    }
}
