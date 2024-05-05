package Src.MainMenu;

import java.util.ArrayList;

import Src.Entities.Plant.*;

public class Inventory {
    private ArrayList<Plant> Inventory;
    private ArrayList<Plant> Deck;

    public void choosePlant(int index){
        Plant plant = Inventory.remove(index);
        Deck.add(plant);
    }

    public void swapPlant(int index1, int index2){
        

        Plant temPlant = Inventory.get(index1);
        Inventory.set(index1, Inventory.get(index2));
        Inventory.set(index2, temPlant);
    }

    public void deletePlant(int index){
        if (index >= 0 && index < Deck.size()){
            Plant plant = Deck.remove(index);
            Inventory.add(plant);
        }
    }
}
