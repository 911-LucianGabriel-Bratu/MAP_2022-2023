package Model;

public class VarDeclStmt implements IStmt{
    String name;
    Type type;

    public VarDeclStmt(String name, Type type){
        this.name = name;
        this.type = type;
    }

    public PrgState execute(PrgState state) throws MyException{
        MyIDictionary<String,Value> symTbl= state.getSymTable();
        if(symTbl.isDefined(name)){
            throw new MyException("variable with name " + name + " has already been declared");
        }
        else{
            symTbl.insert(name, type.defaultValue());
            state.setSymTable(symTbl);
            return state;
        }
    }

    public IStmt deepCopy(){
        return new VarDeclStmt(name, type);
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}
