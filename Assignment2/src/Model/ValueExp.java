package Model;

public class ValueExp implements Exp{
    Value value;

    public ValueExp(Value value) {
        this.value = value;
    }

    public Value eval(MyIDictionary<String,Value> tbl)  throws MyException{
        return this.value;
    }

    public Exp deepCopy(){
        return new ValueExp(value);
    }

    public String toString() {
        return this.value.toString();
    }
}
