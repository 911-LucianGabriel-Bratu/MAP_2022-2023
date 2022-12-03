package Controller;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Statement.IStmt;
import Model.ADTs.MyIStack;
import Model.PrgState.PrgState;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;
    boolean displayFlag;

    public Controller(IRepository repo){
        this.repository = repo;
        this.displayFlag = false;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public List<Integer> getAddrFromHeap(Collection<Value> heapValues){
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e -> (symTableAddr.contains(e.getKey())) || heapAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public PrgState oneStep(PrgState state) throws ADTException, StatementExecutionException, ExpressionEvaluationException {
        MyIStack<IStmt> stack = state.getStk();
        if(stack.isEmpty()){
            throw new StatementExecutionException("prgstate stack is empty");
        }
        IStmt crtStmt = stack.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws ADTException, StatementExecutionException, ExpressionEvaluationException, IOException {
        PrgState prg = repository.getCrtPrg();
        this.repository.logPrgStateExec();
        display();
        while(!prg.getStk().isEmpty()){
            oneStep(prg);
            this.repository.logPrgStateExec();
            prg.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(getAddrFromSymTable(prg.getSymTable().values()), getAddrFromHeap(prg.getHeap().getContent().values()), prg.getHeap().getContent()));
            display();
        }
    }

    void display(){
        if(this.displayFlag){
        System.out.println(this.repository.getCrtPrg().toString());
        }
    }

}
