package Src.MainMenu;
import java.util.ArrayList;
import Src.Entities.Plant.*;
import Src.Entities.Plant.Melee.Chomper;
import Src.Entities.Plant.Melee.Squash;
import Src.Entities.Plant.Melee.TangleKelp;
import Src.Entities.Plant.Passive.Lilypad;
// import Src.Entities.Plant.Passive.Sunflower;
import Src.Entities.Plant.Passive.Tallnut;
import Src.Entities.Plant.Passive.Wallnut;
import Src.Entities.Plant.Shooter.Peashooter;
import Src.Entities.Plant.Shooter.Repeater;
import Src.Entities.Plant.Shooter.Snowpea;
// import Src.Entities.Plant.Projectile.*;

public class Inventory {
    public static final int MAXCAPACITYDECK = 6;
    private ArrayList<Plant> inventory;
    private Deck deck;

    public Inventory(Deck deck) {
        inventory = new ArrayList<>();
        this.deck = deck;

        inventory.add(new Chomper(null, null));
        inventory.add(new Squash(null, null, null));
        inventory.add(new TangleKelp(null, null));
        inventory.add(new Lilypad(null, null));
        // inventory.add(new Sunflower(null, MAXCAPACITYDECK, MAXCAPACITYDECK, null));
        inventory.add(new Tallnut(null, null));
        inventory.add(new Wallnut(null, null));
        // inventory.add(new Peashooter(null, null));
        // inventory.add(new Repeater(null, null));
        // inventory.add(new Snowpea(null, null));
    }

    public ArrayList<Plant> getInventory() {
        return this.inventory;
    }

    // Objects.equals(this.getName(), otherPlant.getName());
    public void addDeck(Plant plant) {
        boolean found = false;
        for (Plant p : inventory) {
            if (p.getName().equals(plant.getName())) {
                found = true;
                deck.addPlantToDeck(plant);
                inventory.remove(p);
                System.out.println("Success!");
                break;
            }
        }
        if (!found) {
            System.out.println("This Plant Is Not In The Inventory!");
        }
    }

    public void swapPlant(Plant ondeck, Plant oninventory){
        boolean found1 = false;
        boolean found2 = false; 
        Plant temp1 = null;
        Plant temp2 = null;
        for (Plant p : deck.getDeck()) {
            if (p.getName().equals(ondeck.getName())) {
                temp1 = p;
                found1 = true;
                break;
            }
        }
        for (Plant p : inventory) {
            if (p.getName().equals(oninventory.getName())) {
                temp2 = p;
                found2 = true;
                break;
            }
        } 
        if (found1 && found2) {
            deck.removePlantFromDeck(temp1);
            inventory.remove(temp2);
            inventory.add(temp1);
            deck.addPlantToDeck(temp2);
            System.out.println("Success!");
        }
        else {
            System.out.println("One or both plants are not in the deck or inventory!");
        }

    }

    public void removeDeck(Plant plant) {
        boolean found = false;
        for (Plant p : deck.getDeck()) {
            if (p.getName().equals(plant.getName())) {
                found = true;
                deck.removePlantFromDeck(plant);
                inventory.add(p);
                System.out.println("Success!");
                break;
            }
        }   
        if (found == false) {
            System.out.println("This Plant Is Not In The Deck!");
        }
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("This Inventory is Empty!");
        }
        else {
            System.out.print("Inventory contents: [");
            for (int i = 0; i < inventory.size() - 1; i++) {
                System.out.print(inventory.get(i).getName() + ", ");
            }
            System.out.println(inventory.get(inventory.size()-1).getName() + "]");
        }
    }
}
