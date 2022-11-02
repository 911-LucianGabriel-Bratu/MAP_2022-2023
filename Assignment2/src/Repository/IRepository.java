package Repository;

import Model.PrgState.PrgState;

import java.util.List;

public interface IRepository {
    PrgState getCrtPrg();
    void addPrg(PrgState prg);
    void setPrgStates(List<PrgState> newPrgStates);
}
