package Repository;

import Exceptions.ADTException;
import Model.PrgState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    List<PrgState> programStates;
    int current_position;

    String logFilePath;

    public Repository(PrgState prgState, String logFilePath){
        this.programStates = new ArrayList<>();
        this.current_position = 0;
        this.programStates.add(prgState);
        this.logFilePath = logFilePath;
    }

    public int getCurrentPosition(){
        return this.current_position;
    }

    @Override
    public void addPrg(PrgState prg){
        this.programStates.add(prg);
    }
    public List<PrgState> getPrgList(){
        return this.programStates;
    }

    @Override
    public void setPrgList(List<PrgState> newPrgStates){
        this.programStates = newPrgStates;
    }

    public void logPrgStateExec(PrgState prg) throws IOException, ADTException{
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(prg.prgStateToString());
        logFile.close();
    }
    public void emptyLogFile() throws IOException{
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.close();
    }
}
