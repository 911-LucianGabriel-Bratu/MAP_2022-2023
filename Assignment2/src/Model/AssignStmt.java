package Model;

public class AssignStmt implements IStmt{
    String key;
    Exp exp;

    public AssignStmt(String key, Exp exp) {
        this.key = key;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        if (symTbl.isDefined(key)){
            Value val = exp.eval(symTbl);
            Type typId = (symTbl.lookup(key)).getType();
            if ((val.getType()).equals(typId)) {
                symTbl.update(key, val);
            }
            else{
                throw new MyException("declared type of variable" + key + " and type of  the assigned expression do not match");
            }
        }
        else throw new MyException("the used variable" + key + " was not declared before");
        state.setSymTable(symTbl);
        return state;
    }

    public IStmt deepCopy(){
        return new AssignStmt(key, exp.deepCopy());
    }

    public String toString() {
        return String.format("%s = %s", key, exp.toString());
    }
}
