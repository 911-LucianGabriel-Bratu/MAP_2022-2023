package Model;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt snd;

    public CompStmt(IStmt first, IStmt snd){
        this.first = first;
        this.snd = snd;
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        state.setStk(stk);
        return state;
    }

    public IStmt deepCopy(){
        return new CompStmt(first.deepCopy(), snd.deepCopy());
    }

    @Override
    public String toString(){
        return "("+first.toString() + "|" + snd.toString()+")";
    }
}
