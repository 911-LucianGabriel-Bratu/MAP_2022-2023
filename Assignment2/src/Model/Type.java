package Model;

public interface Type {
    boolean equals(Type anotherType);
    Value defaultValue();
    Type deepCopy();
}
