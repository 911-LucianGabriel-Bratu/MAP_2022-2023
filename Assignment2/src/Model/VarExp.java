package Model;

public class VarExp implements Exp{
    String id;

    public Value eval(MyIDictionary<String,Value> tbl)  throws MyException {
        return tbl.lookup(id);
    }
}
