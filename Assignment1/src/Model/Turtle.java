package Model;

public class Turtle implements IAnimal{
    private String species;
    private boolean is_endangered;
    private int age;

    public Turtle(String species, boolean is_endangered, int age){
        this.species = species;
        this.is_endangered = is_endangered;
        this.age = age;
    }

    public String getSpecies(){
        return this.species;
    }

    public boolean getIsEndangered(){
        return this.is_endangered;
    }

    public int getAge(){
        return this.age;
    }

    public void setSpecies(String species){
        this.species = species;
    }

    public void setIsEndangered(boolean endangered){
        this.is_endangered = endangered;
    }

    public void setAge(int age){
        this.age = age;
    }

    public boolean isOlderThan(int compareAge){
        return this.age > compareAge;
    }

    @Override
    public String toString(){
        if(this.is_endangered){
            return "Endangered species of turtle: " +
                    this.species + " of age: " +
                    Integer.toString(this.age) + ".";
        }
        return "Not endangered species of turtle: " +
                this.species + " of age: " +
                Integer.toString(this.age) + ".";
    }
}
