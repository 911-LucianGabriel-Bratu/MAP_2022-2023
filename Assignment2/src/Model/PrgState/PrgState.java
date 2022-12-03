package Model.PrgState;

import Exceptions.ADTException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIList;
import Model.ADTs.MyIStack;
import Model.Statement.IStmt;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.List;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<String, BufferedReader> fileTable;
    MyIHeap heap;
    IStmt originalProgram;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value> ot, MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        this.fileTable = fileTable;
        originalProgram = prg.deepCopy();
        this.heap = heap;
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
    public MyIDictionary<String, BufferedReader> getFileTable(){
        return this.fileTable;
    }

    public MyIHeap getHeap(){
        return this.heap;
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

    public void setFileTable(MyIDictionary<String, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }

    public void setHeap(MyIHeap newHeap){
        this.heap = newHeap;
    }

    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStmt> stack = exeStack.getReversed();
        for (IStmt statement: stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws ADTException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookup(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (Value elem: out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    public String fileTableToString() {
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key: fileTable.keySet()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }

    public String heapToString() throws ADTException {
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key: heap.keySet()) {
            heapStringBuilder.append(String.format("%d -> %s\n", key, heap.get(key)));
        }
        return heapStringBuilder.toString();
    }

    public String toString(){
        String print_value = "";
        print_value = print_value + "ExeStack:\n" + this.getStk().getReversed() + "\n" +
                "Symbol Table:\n" + this.getSymTable().toString() + "\n" + "Output:\n" + this.getOut() + "\n" +
                        "FileTable: \n" + fileTable.toString() + "\n" + "Heap:\n" + heap.toString() + "\n";
        return print_value;
    }

    public String prgStateToString() throws ADTException{
        return "ExeStack: \n" + exeStackToString() + "Symbol table: \n" + symTableToString() + "Output: \n" +
                outToString() + "File table:\n" + fileTableToString() + "Heap:\n" + heapToString();
    }
}