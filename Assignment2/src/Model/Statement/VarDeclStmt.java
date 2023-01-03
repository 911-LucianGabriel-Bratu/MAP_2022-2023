package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class VarDeclStmt implements IStmt {
    String name;
    Type type;

    public VarDeclStmt(String name, Type type){
        this.name = name;
        this.type = type;
    }

    public PrgState execute(PrgState state) throws StatementExecutionException {
        MyIDictionary<String, Value> symTbl= state.getSymTable();
        if(symTbl.isDefined(name)){
            throw new StatementExecutionException("variable with name " + name + " has already been declared");
        }
        else{
            symTbl.insert(name, type.defaultValue());
            state.setSymTable(symTbl);
            return null;
        }
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        typeEnv.insert(name, type);
        return typeEnv;
    }

    public IStmt deepCopy(){
        return new VarDeclStmt(name, type);
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}
