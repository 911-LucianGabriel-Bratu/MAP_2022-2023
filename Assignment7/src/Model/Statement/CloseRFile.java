package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
    Exp e;

    public CloseRFile(Exp e){
        this.e = e;
    }

    public PrgState execute(PrgState state) throws StatementExecutionException, ADTException, ExpressionEvaluationException{
        Value val = e.eval(state.getSymTable(), state.getHeap());
        if(!val.getType().equals(new StringType())){
            throw new StatementExecutionException("expression: " + e.toString() + " does not evaluate to StringType");
        }
        StringValue fileName = (StringValue) val;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if(!fileTable.isDefined(fileName.getVal())){
            throw new StatementExecutionException(val + " is not present in the file table");
        }
        BufferedReader br = fileTable.lookup(fileName.getVal());
        try{
            br.close();
        }catch (IOException e){
            throw new StatementExecutionException("error closing file with name: " + fileName.getVal());
        }
        fileTable.remove(fileName.getVal());
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        if(e.typecheck(typeEnv).equals(new StringType())){
            return typeEnv;
        }
        else{
            throw new StatementExecutionException("expression not of StringType");
        }
    }

    @Override
    public IStmt deepCopy(){
        return new CloseRFile(this.e);
    }
    @Override
    public String toString() {
        return "CloseRFile(" + e.toString() + ")";
    }
}
