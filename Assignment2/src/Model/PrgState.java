package Model;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg.deepCopy();
        stk.push(prg);
    }

    public MyIStack<IStmt> getStk(){
        return this.exeStack;
    }

    public MyIList<Value> getOut(){
        return this.out;
    }

    public MyIDictionary<String, Value> getSymTable(){
        return this.symTable;
    }

    public void setStk(MyIStack<IStmt> newStk){
        this.exeStack = newStk;
    }

    public void setOut(MyIList<Value> lst){
        this.out = lst;
    }

    public void setSymTable(MyIDictionary<String, Value> new_table){
        this.symTable = new_table;
    }

    public String toString(){
        String print_value = "";
        print_value = print_value + this.getStk().toString() + "\n" + this.getOut() + "\n" +
                this.getSymTable().toString() + "\n";
        return print_value;
    }
}