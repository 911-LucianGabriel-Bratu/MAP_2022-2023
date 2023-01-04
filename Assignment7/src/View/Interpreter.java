package View;

import Controller.Controller;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyHeap;
import Model.ADTs.MyList;
import Model.ADTs.MyStack;
import Model.Expression.*;
import Model.PrgState.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepository;
import Repository.Repository;

import java.io.IOException;

public class Interpreter {
    public static void main(String[] args){
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        try {
            ex1.typecheck(new MyDictionary<>());
            PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex1);
            IRepository repo1 = new Repository(prg1, "log1.txt");
            Controller controller1 = new Controller(repo1);
            menu.addCommand(new RunExampleCommand("1", ex1.toString(), controller1));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e){
            System.out.println(e.getMessage());
        }

        IStmt ex2 = new CompStmt(new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new
                                ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), '*'), '+')),
                                new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValueExp(new
                                        IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));
        try {
            ex2.typecheck(new MyDictionary<>());
            PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex2);
            IRepository repo2 = new Repository(prg2, "log2.txt");
            Controller controller2 = new Controller(repo2);
            menu.addCommand(new RunExampleCommand("2", ex2.toString(), controller2));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
            System.out.println(e.getMessage());
        }

        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        try {
            ex3.typecheck(new MyDictionary<>());
            PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex3);
            IRepository repo3 = new Repository(prg3, "log3.txt");
            Controller controller3 = new Controller(repo3);
            menu.addCommand(new RunExampleCommand("3", ex3.toString(), controller3));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
            System.out.println(e.getMessage());
        }

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFile(new VarExp("varf"))))))))));
        try {
            ex4.typecheck(new MyDictionary<>());
            PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex4);
            IRepository repo4 = new Repository(prg4, "log4.txt");
            Controller controller4 = new Controller(repo4);
            menu.addCommand(new RunExampleCommand("4", ex4.toString(), controller4));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
            System.out.println(e.getMessage());
        }

        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                new CompStmt(new WhileStmt(new RelExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                        new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"),
                        new ValueExp(new IntValue(1)), '-')))),
                        new PrintStmt(new VarExp("v")))));
        try {
            ex5.typecheck(new MyDictionary<>());
            PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex5);
            IRepository repo5 = new Repository(prg5, "log5.txt");
            Controller controller5 = new Controller(repo5);
            menu.addCommand(new RunExampleCommand("5", ex5.toString(), controller5));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
            System.out.println(e.getMessage());
        }

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new NewStmt("v",
                new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new NewStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")),
                        new PrintStmt(new VarExp("a")))))));
        try {
            ex6.typecheck(new MyDictionary<>());
            PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex6);
            IRepository repo6 = new Repository(prg6, "log6.txt");
            Controller controller6 = new Controller(repo6);
            menu.addCommand(new RunExampleCommand("6", ex6.toString(), controller6));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
            System.out.println(e.getMessage());
        }


        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                new CompStmt(new WriteHeapStatement("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5)), '+'))))));
        try {
            ex7.typecheck(new MyDictionary<>());
            PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex7);
            IRepository repo7 = new Repository(prg7, "log7.txt");
            Controller controller7 = new Controller(repo7);
            menu.addCommand(new RunExampleCommand("7", ex7.toString(), controller7));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
            System.out.println(e.getMessage());
        }

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
        try {
            ex8.typecheck(new MyDictionary<>());
            PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex8);
            IRepository repo8 = new Repository(prg8, "log8.txt");
            Controller controller8 = new Controller(repo8);
            menu.addCommand(new RunExampleCommand("8", ex8.toString(), controller8));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
            System.out.println(e.getMessage());
        }

        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStatement("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
        try {
            ex9.typecheck(new MyDictionary<>());
            PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap(), ex9);
            IRepository repo9 = new Repository(prg9, "log9.txt");
            Controller controller9 = new Controller(repo9);
            menu.addCommand(new RunExampleCommand("9", ex9.toString(), controller9));
        }
        catch (ExpressionEvaluationException | ADTException | StatementExecutionException e) {
            System.out.println(e.getMessage());
        }

        menu.show();
    }
}
