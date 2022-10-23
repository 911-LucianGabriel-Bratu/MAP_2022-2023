package Controller;

import Model.IAnimal;
import Repository.IRepository;

public class Controller {
    private IRepository repository;

    public Controller(IRepository repository){
        this.repository = repository;
    }

    public void addAnimal(IAnimal animal) throws RuntimeException{
        this.repository.addAnimal(animal);
    }

    public void printForAgeCheck(int compare_age){
        IAnimal[] animals = this.repository.getAllAnimals();
        for(int i = 0; i < this.repository.getCurrentSize(); i++){
            if(animals[i].isOlderThan(compare_age)){
                System.out.println(animals[i].toString());
            }
        }
    }

    public void printAllAnimals(){
        IAnimal[] animals = this.repository.getAllAnimals();
        for(int i = 0; i < this.repository.getCurrentSize(); i++){
            System.out.println(Integer.toString(i) + "." + animals[i].toString());
        }
    }

    public void deleteAnimalOnPosition(int del_pos){
        if(del_pos < 0 || del_pos > this.repository.getCurrentSize()){
            System.out.println("Non-existent position in array.");
        }
        this.repository.deleteAnimalOnPosition(del_pos);
    }
}
