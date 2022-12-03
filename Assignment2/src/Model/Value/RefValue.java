package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public RefValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public Type getType() {
        return new RefType(this.locationType);
    }

    public int getAddress(){
        return this.address;
    }

    public Type getLocationType(){
        return this.locationType;
    }

    public String toString(){
        return "(" + Integer.toString(this.address) + "," + locationType.toString() + ")";
    }

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType.deepCopy());
    }
}
