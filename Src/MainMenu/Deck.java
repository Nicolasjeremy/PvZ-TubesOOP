package Src.MainMenu;

import java.util.ArrayList;
import Src.Entities.Plant.*;
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
import Src.GameMaps.*;

public class Deck {
    public static final int MAXCAPACITYDECK = 6;
    private ArrayList<Plant> deck;
    private GameMap gameMap;
    private boolean full;
    private boolean cooldownover;

    public Deck(GameMap gameMap) {
        this.gameMap = gameMap;
        this.deck = new ArrayList<>();
        this.full = false;
        this.cooldownover = true;
    }

    public int getSizeDeck() {
        return deck.size();
    }

    public GameMap getGameMap() {
        return this.gameMap;
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
        return this.cooldownover;
    }

    public void setCooldownOver(boolean cooldownover) {
        this.cooldownover = cooldownover;
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
                if (deck.get(i).getCooldown() <= Math
                        .abs(Gameplay.getCurrentTime() - deck.get(i).getLastPlantedTime())) {
                    System.out.println("\t" + (i + 1) + ". " + deck.get(i).getName() + "\t Cooldown : Ready");
                } else if (deck.get(i).getCooldown() == 0) {
                    System.out.println("\t" + (i + 1) + ". " + deck.get(i).getName() + "\t Cooldown : Ready");
                } else {
                    System.out.println("\t" + (i + 1) + ". " + deck.get(i).getName() + "\t Cooldown : Ready at "
                            + Math.abs(deck.get(i).getLastPlantedTime() - deck.get(i).getcooldowntanaman()));
                }
            }
        }
    }

    public void planting(Plant plant, int[] position) {
        Plant newPlant = createNewPlantInstance(plant);
        Tile tile = this.gameMap.getTile(position[0], position[1]);

        if (tile instanceof Home) {
            System.out.println("Can't Plant On Home!");
        } else if (tile instanceof ZombieSpawn) {
            System.out.println("Can't Plant On ZombieSpawn!");
        } else if (tile instanceof Pool) {
            Pool tilepool = (Pool) tile;
            if (tilepool.getLilyPad_Plant()) {
                if (plant instanceof TangleKelp) {
                    System.out.println("Can't Plant TangleKelp On Lilypad");
                } else {
                    plantManager(newPlant, position, tilepool);
                }
            } else {
                if (plant instanceof Lilypad) {
                    plantManager(newPlant, position, tilepool);
                    tilepool.Plant_LilyPad();
                } else {
                    if (plant instanceof TangleKelp) {
                        plantManager(newPlant, position, tilepool);
                    } else {
                        System.out.println("Needs Lilypad To Plant!");
                    }
                }
            }
        } else {
            if (plant instanceof Lilypad || plant instanceof TangleKelp) {
                System.out.println("Lilypad And TangleKelp Can't Be Planted On Grass");
            } else {
                plantManager(newPlant, position, tile);
            }
        }
    }

    public void plantManager(Plant plant, int[] position, Tile tile) {
        int temp = plant.getCooldown(); // ! ini variable nya ngaruh ke plant yang baru, harusnya ganti statik
        plant.setcooldowntanaman(plant.getCooldown());
        plant.setCooldown(Math.abs(Gameplay.getCurrentTime() - plant.getLastPlantedTime()));

        if (plant.getCooldown() >= temp) {
            setCooldownOver(true);
        }

        // // masih suka inkosisten cooldownnya

        // if (Gameplay.getCurrentTime() - plant.getLastPlantedTime() >
        // plant.getCooldown()){
        // setCooldownOver(true);
        // }
        // else if (Gameplay.getCurrentTime() - plant.getLastPlantedTime() < 0 -
        // plant.getCooldown()){
        // setCooldownOver(true);
        // }
        // else {
        // setCooldownOver(false);
        // }

        if (Sun.getSun() < plant.getCost()) {
            Sun.spendSun(plant.getCost());
        } else {
            if (!isCooldownOver()) {
                System.out.println("This Plant is On Cooldown!");
                System.out.println("Gameplay time : " + Gameplay.getCurrentTime());
                System.out.println("Plant time : " + plant.getLastPlantedTime());
                System.out.println("Cooldown plant : " + plant.getCooldown());
            } else if (tile.hasPlanted()) {
                if (tile.getAllPlant().getFirst() instanceof Lilypad && tile.getAllPlant().size() < 2) {
                    plant.setgameMap(gameMap);
                    plant.setPosition(position);
                    tile.addEntity(plant);
                    tile.setPlanted(true);
                    Sun.spendSun(plant.getCost());

                    System.out.println("Gameplay time : " + Gameplay.getCurrentTime());
                    System.out.println("Plant time : " + plant.getLastPlantedTime());
                    System.out.println("Cooldown plant : " + plant.getCooldown());

                    plant.setLastPlantedTime(Gameplay.getCurrentTime());
                    System.out.println("Plant time : " + plant.getLastPlantedTime());
                    setCooldownOver(false);

                    System.out.println("Plant Planted Successfully!");

                    Thread plantThread = new Thread(plant);
                    plantThread.start();
                } else if (tile.getEntities(0) instanceof Plant) { 
                    System.out.println("This Tile Has Been Planted!");
                }
            } else {
                plant.setgameMap(gameMap);
                plant.setPosition(position);
                tile.addEntity(plant);
                tile.setPlanted(true);
                Sun.spendSun(plant.getCost());

                System.out.println("Gameplay time : " + Gameplay.getCurrentTime());
                System.out.println("Plant time : " + plant.getLastPlantedTime());
                System.out.println("Cooldown plant : " + plant.getCooldown());

                plant.setLastPlantedTime(Gameplay.getCurrentTime());
                System.out.println("Plant time : " + plant.getLastPlantedTime());
                setCooldownOver(false);

                System.out.println("Plant Planted Successfully!");

                Thread plantThread = new Thread(plant);
                plantThread.start();
            }
        }
    }

    private Plant createNewPlantInstance(Plant plant) {
        if (plant instanceof Jalapeno) {
            return new Jalapeno(null, null);
        } else if (plant instanceof Squash) {
            return new Squash(null, null);
        } else if (plant instanceof TangleKelp) {
            return new TangleKelp(null, null);
        } else if (plant instanceof Lilypad) {
            return new Lilypad(null, null);
        } else if (plant instanceof Tallnut) {
            return new Tallnut(null, null);
        } else if (plant instanceof Wallnut) {
            return new Wallnut(null, null);
        }
        if (plant instanceof Peashooter) {
            return new Peashooter(null, null);
        } else if (plant instanceof Repeater) {
            return new Repeater(null, null);
        } else if (plant instanceof Snowpea) {
            return new Snowpea(null, null);
        } else if (plant instanceof Sunflower) {
            return new Sunflower(null, null);
        }
        return null;
    }

    public void unPlanting(int[] position) {
        Tile tile = this.gameMap.getTile(position[0], position[1]);
        if (!tile.hasPlanted()) {
            System.out.println("This Tile Is Not Planted By Any Plant!");
        } else {
            Plant templant = tile.getTilePlant();
            System.out.println("Plant name : " + templant.getName());
            tile.removeEntity(templant);
            tile.setPlanted(false);
            System.out.println("Plant Removed!");
        }
    }
}
