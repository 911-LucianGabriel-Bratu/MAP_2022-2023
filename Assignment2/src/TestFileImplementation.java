import Controller.Controller;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.*;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.PrgState.PrgState;
import Model.Statement.*;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class TestFileImplementation {
    private void runStatement(IStmt statement) throws ExpressionEvaluationException, StatementExecutionException, ADTException, IOException {
        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap heap = new MyHeap();
        PrgState state = new PrgState(exeStack, symbolTable, output, fileTable, heap, statement);

        IRepository repository = new Repository(state, "log.txt");
        Controller controller = new Controller(repository);

        System.out.println("Do you want to display the steps?[y/n]");
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        controller.setDisplayFlag(Objects.equals(option, "y"));

        controller.allStep();
        System.out.println("Result is " + state.getOut().toString() + '\n');
    }
    public void start() throws ExpressionEvaluationException, StatementExecutionException, ADTException, IOException{
        IStmt stmt = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));
        this.runStatement(stmt);
    }

    public static void main(String[] args) throws ExpressionEvaluationException, ADTException, IOException, StatementExecutionException {
        TestFileImplementation t = new TestFileImplementation();
        t.start();
    }
}




