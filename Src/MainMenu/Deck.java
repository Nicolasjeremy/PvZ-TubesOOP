package Src.MainMenu;

import java.util.ArrayList;
import Src.Entities.Plant.*;
import Src.Entities.Plant.Passive.Lilypad;
import Src.GameMaps.*;
import Src.GameMaps.Sun;

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

    public Plant getPlantFromDeck(int index) {
        if (index >= 0 && index < deck.size()) {
            return deck.get(index);
        }
        return null;
    }

    public int indexOf(Plant plant) {
        return deck.indexOf(plant);
    }

    public boolean isCooldownOver() {
        return false;
    }

    public void setFull(boolean isFull) {
        this.full = isFull;
    }

    public boolean getFull() {
        return this.full;
    }

    public void addPlantToDeck(Plant plant) {
        if (deck.size() >= MAXCAPACITYDECK) {
            System.out.println("This Deck Is Full!");
        } else {
            deck.add(plant);
            System.out.println(plant.getName() + " added to the Deck");
        }
    }

    public void removePlantFromDeck(int index) {
        if (index >= 0 && index < deck.size()) {
            Plant removedPlant = deck.remove(index);
            System.out.println(removedPlant.getName() + " removed from the Deck");
        } else {
            System.out.println("Invalid index!");
        }
    }

    public void swapPlantsInDeck(int index1, int index2) {
        if (index1 >= 0 && index1 < deck.size() && index2 >= 0 && index2 < deck.size()) {
            Plant temp = deck.get(index1);
            deck.set(index1, deck.get(index2));
            deck.set(index2, temp);
            System.out.println("Plants swapped successfully!");
        } else {
            System.out.println("Invalid index(es)!");
        }
    }

    public void displayDeck() {
        if (deck.isEmpty()) {
            System.out.println("This Deck is Empty!");
        } else {
            System.out.println("Deck contents:");
            for (int i = 0; i < deck.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + deck.get(i).getName());
            }
        }
    }

    public void planting(Plant plant, int[] position) {
        Tile tile = this.gameMap.getTile(position[0], position[1]);
        if (tile instanceof Home)  {
            System.out.println("Cant Plant On Home!");
        }
        else if (tile instanceof ZombieSpawn) {
            System.out.println("Cant Plant On ZombieSpawn!");
        }
        else if (tile instanceof Pool) {
            Pool tilepool = (Pool) tile;
            if (tilepool.getLilyPad_Plant()) {
                plantManager(plant, position, tilepool);
            }
            else {
                if (plant instanceof Lilypad) {
                    plantManager(plant, position, tilepool);
                    tilepool.Plant_LilyPad();
                }
                else {
                    System.out.println("Needs Lilypad To Plant!");
                }
            }
        }
        else {
            if (plant instanceof Lilypad) {
                System.out.println("Lilypad Cant Planted On Grass");
            }
            else {
                plantManager(plant, position, tile);
            }
        }

    }

    public void plantManager(Plant plant, int[] position, Tile tile) {
        if(Sun.getSun() < plant.getCost()){
            Sun.spendSun(plant.getCost());
        }
        else {
            if (plant instanceof Lilypad) {
                if (tile instanceof Home) {
                    System.out.println("Lilypad");
                }
            }
            if (isCooldownOver()) {
                System.out.println("This Plant is On Cooldown!");
            } else if (tile.hasPlanted()) { 
                if (tile.getEntities(0) instanceof Lilypad && tile.getAllEntities().size() < 2) {
                    plant.setgameMap(gameMap);
                    tile.addEntity(plant);
                    tile.setPlanted(true);
                    Sun.spendSun(plant.getCost());

                    System.out.println("Plant Planted Successfully!");

                    Thread plantThread = new Thread(plant);
                    plantThread.start();
                }
                else {
                    System.out.println("This Tile Has Been Planted!");
                }
            } else {
                plant.setgameMap(gameMap);
                tile.addEntity(plant);
                tile.setPlanted(true);
                Sun.spendSun(plant.getCost());

                System.out.println("Plant Planted Successfully!");

                Thread plantThread = new Thread(plant);
                plantThread.start();
            }
        }
    }

    public void unPlanting(int[] position) {
        Tile tile = this.gameMap.getTile(position[0], position[1]);
        if (!tile.hasPlanted()) {
            System.out.println("This Tile Is Not Planted By Any Plant!");
        } else {
            Plant templant = tile.getTilePlant();
            System.out.println("Plant name : " + templant.getName());
            tile.removeEntity(templant);
            System.out.println("Plant Removed!");
        }
    }
}
