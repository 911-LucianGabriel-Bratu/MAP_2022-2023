package Repository;

import Model.IAnimal;

public class Repository implements IRepository{
    private IAnimal[] animals;
    private int current_size;

    public Repository(int max_size){
        this.current_size = 0;
        this.animals = new IAnimal[max_size];
    }

    @Override
    public void addAnimal(IAnimal animal) throws RuntimeException{
        try{
            this.animals[this.current_size] = animal;
            this.current_size++;
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    @Override
    public IAnimal[] getAllAnimals(){
        return this.animals;
    }

    @Override
    public int getCurrentSize(){
        return this.current_size;
    }

    @Override
    public void deleteAnimalOnPosition(int del_pos){
        IAnimal[] new_animals = new IAnimal[this.current_size-1];
        int index = 0;
        for(int i = 0; i < this.current_size; i++){
            if(i != del_pos){
                new_animals[index] = this.animals[i];
                index++;
            }
        }
        this.animals = new_animals;
        this.current_size -= 1;
    }
}
