package Model;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl) throws MyException;
}
