package Repository;

import Model.IAnimal;

public interface IRepository {
    public void addAnimal(IAnimal animal);
    public IAnimal[] getAllAnimals();

    public int getCurrentSize();

    public void deleteAnimalOnPosition(int del_pos);
}
