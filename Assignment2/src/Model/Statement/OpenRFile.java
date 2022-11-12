package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.Expression.Exp;
import Model.Expression.ValueExp;
import Model.PrgState.PrgState;
import Model.Type.StringType;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStmt{
    Exp e;

    public OpenRFile(Exp e){
        this.e = e;
    }

    public PrgState execute(PrgState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException{
        Value val = e.eval(state.getSymTable());
        if(val.getType().equals(new StringType())){
            StringValue fileName = (StringValue) val;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if(!fileTable.isDefined(fileName.getVal())){
                BufferedReader br;
                try{
                    br = new BufferedReader(new FileReader(fileName.getVal()));
                }
                catch (FileNotFoundException e){
                    throw new StatementExecutionException(fileName.getVal() + " could not be opened");
                }
                fileTable.insert(fileName.getVal(), br);
                state.setFileTable(fileTable);
            }
            else{
                throw new StatementExecutionException(fileName.getVal() + " is already opened");
            }
        }
        else{
            throw new StatementExecutionException(e.toString() + " does not evaluate to StringType");
        }
        return state;
    }

    public IStmt deepCopy(){
        return new OpenRFile(this.e);
    }

    @Override
    public String toString(){
        return "OpenRFile(" + e.toString() + ")";
    }
}



