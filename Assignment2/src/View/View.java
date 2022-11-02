package View;

import Controller.Controller;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.*;
import Model.Expression.ArithExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.PrgState.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import Repository.IRepository;
import Repository.Repository;

import java.util.Objects;
import java.util.Scanner;

public class View {
    public void start(){
        boolean done = false;
        while (!done) {
            try {
                showMenu();
                Scanner readOption = new Scanner(System.in);
                int option = readOption.nextInt();
                if (option == 0) {
                    done = true;
                } else if (option == 1) {
                    runProgram1();
                } else if (option == 2) {
                    runProgram2();
                } else if (option == 3) {
                    runProgram3();
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void showMenu(){
        System.out.println("Select one of the following operations: ");
        System.out.println("0. Exit.");
        System.out.println("1. Run the first program: \nint v;\nv=2;\nPrint(v)");
        System.out.println("2. Run the second program: \nint a;\nint b;\na=2+3*5;\nb=a+1;\nPrint(b)");
        System.out.println("3. Run the third program: \nbool a;\nint v;\na=true;\n(If a Then v=2 Else v=3);\nPrint(v)");
        System.out.println("Choose an option: ");
    }

    private void runProgram1() throws ExpressionEvaluationException, StatementExecutionException, ADTException {
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        runStatement(ex1);
    }

    private void runProgram2() throws ExpressionEvaluationException, StatementExecutionException, ADTException{
        IStmt ex2 = new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new
                                ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), '*'), '+')),
                                new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));
        runStatement(ex2);
    }

    private void runProgram3() throws ExpressionEvaluationException, StatementExecutionException, ADTException{
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        runStatement(ex3);
    }

    private void runStatement(IStmt statement) throws ExpressionEvaluationException, StatementExecutionException, ADTException{
        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        PrgState state = new PrgState(exeStack, symbolTable, output, statement);

        IRepository repository = new Repository(state);
        Controller controller = new Controller(repository);

        System.out.println("Do you want to display the steps?[y/n]");
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        controller.setDisplayFlag(Objects.equals(option, "y"));

        controller.allStep();
        System.out.println("Result is " + state.getOut().toString() + '\n');
    }
}
