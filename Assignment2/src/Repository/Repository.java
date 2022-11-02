package Repository;

import Model.PrgState.PrgState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    List<PrgState> programStates;
    int current_position;

    public Repository(PrgState prgState){
        this.programStates = new ArrayList<>();
        this.current_position = 0;
        this.programStates.add(prgState);
    }

    public int getCurrentPosition(){
        return this.current_position;
    }

    @Override
    public void addPrg(PrgState prg){
        this.programStates.add(prg);
    }

    @Override
    public void setPrgStates(List<PrgState> newPrgStates){
        this.programStates = newPrgStates;
    }

    @Override
    public PrgState getCrtPrg(){
        return this.programStates.get(this.current_position);
    }
}
