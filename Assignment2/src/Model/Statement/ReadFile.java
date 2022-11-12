package Model.Statement;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.Expression.Exp;
import Model.PrgState.PrgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt{
    Exp e;
    String varName;

    public ReadFile(Exp e, String varName){
        this.e = e;
        this.varName = varName;
    }

    public PrgState execute(PrgState state) throws ADTException, StatementExecutionException, ExpressionEvaluationException{
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if(symTable.isDefined(this.varName)){
            Value val = symTable.lookup(varName);
            if(val.getType().equals(new IntType())){
                val = e.eval(symTable);
                if(val.getType().equals(new StringType())){
                    StringValue castedString = (StringValue) val;
                    if(fileTable.isDefined(castedString.getVal())){
                        BufferedReader br = fileTable.lookup(castedString.getVal());
                        try{
                            String line = br.readLine();
                            if(line == null){
                                line = "0";
                            }
                            symTable.insert(varName, new IntValue(Integer.parseInt(line)));
                        }
                        catch(IOException e){
                            throw new StatementExecutionException("could not read from file with name: " + castedString.getVal());
                        }
                    } else{
                        throw new StatementExecutionException("file with name: " + castedString.getVal() + " is not defined in the file table");
                    }
                } else{
                    throw new StatementExecutionException("expression: " + e.toString() +"does not evaluate to string type");
                }
            } else{
                throw new StatementExecutionException("variable: " + val + " is not of IntType");
            }
        } else{
            throw new StatementExecutionException("variable: " + varName + " is not declared");
        }
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new ReadFile(this.e, this.varName);
    }

    @Override
    public String toString(){
        return "ReadFile(" + e.toString() + ", " + varName + ")";
    }
}
