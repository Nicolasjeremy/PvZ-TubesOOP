package Src.MainMenu;

import java.util.ArrayList;
import Src.Entities.Plant.*;
import Src.Entities.Plant.Melee.Chomper;
import Src.Entities.Plant.Melee.Jalapeno;
import Src.Entities.Plant.Melee.Squash;
import Src.Entities.Plant.Melee.TangleKelp;
import Src.Entities.Plant.Passive.Lilypad;
import Src.Entities.Plant.Passive.Sunflower;
import Src.Entities.Plant.Passive.Tallnut;
import Src.Entities.Plant.Passive.Wallnut;
import Src.Entities.Plant.Shooter.Peashooter;
import Src.Entities.Plant.Shooter.Repeater;
import Src.Entities.Plant.Shooter.Snowpea;

public class Inventory {
    public static final int MAXCAPACITYDECK = 6;
    private ArrayList<Plant> inventory;
    private Deck deck;

    public Inventory(Deck deck) {
        inventory = new ArrayList<>();
        this.deck = deck;

        inventory.add(new Jalapeno(null,null));
        inventory.add(new Squash(null,null));
        inventory.add(new TangleKelp(null,null));
        inventory.add(new Lilypad(null,null));
        inventory.add(new Tallnut(null,null));
        inventory.add(new Wallnut(null,null));
        inventory.add(new Peashooter(null,null));
        inventory.add(new Repeater(null,null));
        inventory.add(new Snowpea(null,null));
        inventory.add(new Sunflower(null,null));
    }

    public ArrayList<Plant> getInventory() {
        return this.inventory;
    }

    public void addDeck(int index) {
        Plant plant = inventory.get(index);

        if (deck.getDeck().contains(plant)) {
            System.out.println("This plant is already in the deck!");
            return;
        }

        if (index >= 0 && index < inventory.size()) {
            Plant tempplant = inventory.get(index);
            deck.addPlantToDeck(tempplant);
            System.out.println("Success!");
        } else {
            System.out.println("Invalid index!");
        }
    }

    public void removeDeck(int index) {
        deck.removePlantFromDeck(index);
    }

    public void swapPlantsInInventory(int index1, int index2) {
        if (index1 >= 0 && index1 < inventory.size() && index2 >= 0 && index2 < inventory.size()) {
            Plant temp = inventory.get(index1);
            inventory.set(index1, inventory.get(index2));
            inventory.set(index2, temp);
            System.out.println("Plants swapped successfully!");
        } else {
            System.out.println("Invalid index(es)!");
        }
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("This Inventory is Empty!");
        } else {
            System.out.println("Inventory contents:");
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println("\t" +  (i + 1) + ". " + inventory.get(i).getName());
            }
        }
    }
}
