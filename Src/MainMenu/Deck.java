package Src.MainMenu;

import java.util.ArrayList;

import Src.Entities.Plant.*;
import Src.GameMaps.*;

public class Deck {
    public static final int MAXCAPACITYDECK = 6; 
    private ArrayList<Plant> deck;
    private GameMap gameMap;
    private boolean full;

    public Deck(GameMap gameMap) {
        this.gameMap = gameMap;
        this.deck = new ArrayList<>();
        this.full = false;
    }

    public int getSizeDeck() {
        return deck.size();
    }

    public ArrayList<Plant> getDeck() {
        return this.deck;
    }

    public Plant getPlantFromDeck(Deck deck, String plantName) {
        for (Plant plant : deck.getDeck()) {
            if (plant.getName().equalsIgnoreCase(plantName)) {
                return plant;
            }
        }
        return null;
    }

    public int indexOf(Plant plant) {
        return deck.indexOf(plant);
    }
    //MASIH NGASAL
    public boolean isCooldownOver () {
        return false;
    }

    public void setFull(boolean isFull) {
        this.full = isFull;
    }
    
    public boolean getFull () {
        return this.full;
    }

    public void addPlantToDeck(Plant plant) {
        if (deck.size() >= MAXCAPACITYDECK) {
            System.out.println("This Deck Is Full!");
        } else {
            deck.add(plant);
        }
    }
    

    public void removePlantFromDeck(Plant plant) {
        boolean found = false;
        for (Plant p : deck) {
            if (p.getName().equals(plant.getName())) {
                found = true;
                deck.remove(p);
                System.out.println(plant.getName() + " Removed From The Deck");
                System.out.println("Success!");
                break;
            }
        }
        if (found == false) {
            System.out.println("This Plant Is Not In The Deck!");
        } 
    }

    public void displayDeck() {
        if (deck.isEmpty()) {
            System.out.println("This Deck is Empty!");
        }
        else {
            System.out.print("Deck contents: [");
            for (int i = 0; i < deck.size() - 1; i++) {
                System.out.print(deck.get(i).getName() + ", ");
            }
            System.out.println(deck.get(deck.size()-1).getName() + "]");
        }
        
    }

    public void planting(Plant plant, int[] position) {
            Tile tile = this.gameMap.getTile(position[0], position[1]);
            if (isCooldownOver()) {
                System.out.println("This Plant is On Cooldown!");
            }
            else if (tile.hasPlanted()) {
                System.out.println("This Tile Has Been Planted!");
            }
            else {
                plant.setPosition(position);
                tile.addEntity(plant);
                tile.setPlanted(true);
                System.out.println("Plant Planted Successfully!");

                Thread plantThread = new Thread(plant);
                plantThread.start();
            }
            
    }

    public void unPlanting(int[] position) {
        Tile tile = this.gameMap.getTile(position[0], position[1]);
        if (tile.hasPlanted() == false) {
            System.out.println("This Tile Is Not Planted By Any Plant!");
        }
        else {
            Plant templant = tile.getTilePlant();
            System.out.println("Plant name : " + templant.getName());
            tile.removeEntity(templant);
            System.out.println("Plant Removed!");
        }
    }

}