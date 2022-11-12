package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value{
    String val;

    public StringValue(String str){
        this.val = str;
    }

    @Override
    public boolean equals(Object anotherValue){
        if(!(anotherValue instanceof StringValue)){
            return false;
        }
        StringValue castedStr = (StringValue) anotherValue;
        return this.val.equals(castedStr.val);
    }

    public String getVal(){
        return this.val;
    }

    public Type getType(){
        return new StringType();
    }

    public Value deepCopy(){
        return new StringValue(this.val);
    }

    @Override
    public String toString(){
        return this.val;
    }
}
