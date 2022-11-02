package Controller;

import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.Statement.IStmt;
import Model.ADTs.MyIStack;
import Model.PrgState.PrgState;
import Repository.IRepository;

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

    public PrgState oneStep(PrgState state) throws ADTException, StatementExecutionException, ExpressionEvaluationException {
        MyIStack<IStmt> stack = state.getStk();
        if(stack.isEmpty()){
            throw new StatementExecutionException("prgstate stack is empty");
        }
        IStmt crtStmt = stack.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws ADTException, StatementExecutionException, ExpressionEvaluationException{
        PrgState prg = repository.getCrtPrg();
        display();
        while(!prg.getStk().isEmpty()){
            oneStep(prg);
            display();
        }
    }

    void display(){
        if(this.displayFlag){
        System.out.println(this.repository.getCrtPrg().toString());
        }
    }

}