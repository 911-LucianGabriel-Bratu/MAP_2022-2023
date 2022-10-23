/*

8. Intr-un acvariu traiesc pesti si broaste testoase.
Sa se afiseze toate vietuitoarele din acvariu care sunt
mai batrine de 1 an.

 */

package View;

import Controller.Controller;
import Model.Fish;
import Model.IAnimal;
import Model.Turtle;
import Repository.IRepository;
import Repository.Repository;

import java.io.IOError;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOError, RuntimeException {
        int option;
        IAnimal a1 = new Fish("Guppy", 2);
        IAnimal a2 = new Fish("Neon Tetra", 1);
        IAnimal a3 = new Turtle("Alligator Turtle", true, 20);
        IAnimal a4 = new Turtle("Common Turtle", false, 1);
        IAnimal a5 = new Fish("Goldfish", 4);
        IAnimal a6 = new Fish("Zebrafish", 2);
        IAnimal a7 = new Turtle("Leatherback Turtle", true, 5);
        IAnimal a8 = new Turtle("Radiated Turtle", true, 5);
        IAnimal a9 = new Fish("Pictus Catfish", 1);
        IAnimal a10 = new Fish("Bala Shark", 2);

        IRepository repository = new Repository(11);
        Controller controller = new Controller(repository);

        controller.addAnimal(a1);
        controller.addAnimal(a2);
        controller.addAnimal(a3);
        controller.addAnimal(a4);
        controller.addAnimal(a5);
        controller.addAnimal(a6);
        controller.addAnimal(a7);
        controller.addAnimal(a8);
        controller.addAnimal(a9);
        controller.addAnimal(a10);

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("~Fish and Turtles aquarium~");
            System.out.println("Available operations:");
            System.out.println("1. Print all animals.");
            System.out.println("2. Print all animals older than a given age.");
            System.out.println("3. Add a new animal to the aquarium.");
            System.out.println("4. Delete an animal entry on a position in the array.");
            System.out.println("5. Exit.");
            System.out.print("Option: ");
            option = sc.nextInt();
            System.out.print("\n");
            
            if(option == 1){
                controller.printAllAnimals();
            }
            else if(option == 2){
                System.out.print("Enter the age to compare with: ");
                int compare_age = sc.nextInt();
                System.out.print("\n");
                controller.printForAgeCheck(compare_age);
            }
            else if(option == 3){
                System.out.println("Which type of animal do you want to add?");
                System.out.println("1. Fish");
                System.out.println("2. Turtle");
                System.out.print("Option: ");
                option = sc.nextInt();
                if(option == 1){
                    System.out.print("Species: ");
                    sc.nextLine();
                    String species = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    System.out.print("\n");
                    IAnimal animal = new Fish(species, age);
                    controller.addAnimal(animal);
                }
                else if(option == 2){
                    System.out.print("Species: ");
                    sc.nextLine();
                    String species = sc.nextLine();
                    System.out.print("Endangered: ");
                    boolean endangered = sc.nextBoolean();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    System.out.print("\n");
                    IAnimal animal = new Turtle(species, endangered, age);
                    controller.addAnimal(animal);
                }
                else{
                    System.out.println("Invalid add option.");
                }
            }
            else if(option == 4){
                System.out.print("Enter the position of the animal entry in the array: ");
                int del_pos = sc.nextInt();
                System.out.print("\n");
                controller.deleteAnimalOnPosition(del_pos);
            }
            else if(option == 5){
                return;
            }
            else{
                System.out.println("Invalid option.");
            }
        }
    }
}
