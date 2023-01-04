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
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    IRepository repository;
    boolean displayFlag;

    ExecutorService executorService;

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

    public void oneStepForAllPrg(List<PrgState> prgStates) throws InterruptedException, ExpressionEvaluationException, ADTException, StatementExecutionException, IOException{
        prgStates.forEach(prgState ->{
            try{
                repository.logPrgStateExec(prgState);
                display(prgState);
            } catch(IOException | ADTException e){
                System.out.println(e.getMessage());
            }
        });
        List<Callable<PrgState>> callList = prgStates.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executorService.invokeAll(callList).stream()
                .map(future -> {
                    try
                    {
                        return future.get();
                    } catch(ExecutionException | InterruptedException e){
                        System.out.println(e.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        prgStates.addAll(newPrgList);
        prgStates.forEach(prgState ->
        {
            try{
                repository.logPrgStateExec(prgState);
            } catch(IOException | ADTException e){
                System.out.println(e.getMessage());
            }
        });
        repository.setPrgList(prgStates);
    }

    public void allStep() throws ADTException, StatementExecutionException, ExpressionEvaluationException, IOException, InterruptedException {
        executorService = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());
        while(prgList.size() > 0){
            conservativeGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repository.getPrgList());
        }
        executorService.shutdown();
        repository.setPrgList(prgList);
    }

    public void conservativeGarbageCollector(List<PrgState> prgStates){
        List<Integer> symTableAddresses = Objects.requireNonNull(prgStates.stream()
                .map(p -> getAddrFromSymTable(p.getSymTable().values()))
                .map(Collection::stream)
                .reduce(Stream::concat).orElse(null)
                .collect(Collectors.toList()));
        prgStates.forEach(p ->{
            p.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(symTableAddresses,
                    getAddrFromHeap(p.getHeap().getContent().values()), p.getHeap().getContent()));
        });
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    void display(PrgState prgState){
        if(this.displayFlag){
        System.out.println(prgState.toString());
        }
    }

}
