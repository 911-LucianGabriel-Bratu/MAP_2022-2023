package Repository;

import Exceptions.ADTException;
import Model.PrgState.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    PrgState getCrtPrg();
    void addPrg(PrgState prg);
    void setPrgStates(List<PrgState> newPrgStates);
    void logPrgStateExec() throws IOException, ADTException;
    void emptyLogFile() throws IOException;
}
