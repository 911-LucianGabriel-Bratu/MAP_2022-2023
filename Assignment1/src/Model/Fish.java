package Model;

public class Fish implements IAnimal{
    private String species;
    private int age;

    public Fish(String species, int age) {
        this.species = species;
        this.age = age;
    }

    public String getSpecies(){
        return this.species;
    }

    public int getAge(){
        return this.age;
    }

    public void setSpecies(String species){
        this.species = species;
    }

    public void setAge(int age){
        this.age = age;
    }

    public boolean isOlderThan(int compareAge){
        return this.age > compareAge;
    }

    @Override
    public String toString() {
        return "Fish of species: " + this.species + " and age: " + Integer.toString(this.age) + ".";
    }
}
