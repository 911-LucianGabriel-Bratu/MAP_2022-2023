package Repository;

import Exceptions.ADTException;
import Model.PrgState.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    List<PrgState> getPrgList();
    void addPrg(PrgState prg);
    void setPrgList(List<PrgState> newPrgStates);
    void logPrgStateExec(PrgState prg) throws IOException, ADTException;
    void emptyLogFile() throws IOException;
}
