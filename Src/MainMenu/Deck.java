package Src.MainMenu;

import java.util.ArrayList;

import Src.Entities.Plant.*;
import Src.GameMaps.*;

public class Deck {
    public static final int MAXCAPACITYDECK = 6; 
    private ArrayList<Plant> deck;
    private GameMap gameMap;

    public Deck(GameMap gameMap) {
        this.deck = new ArrayList<>();
    }

    //MASIH NGASAL
    public boolean isCooldownOver () {
        return true;
    }

    public void addPlant(Plant plant, int[] position) {
        try {
            Tile tile = gameMap.getTile(position[0], position[1]);
            if (tile.hasPlanted()) {
                throw new ExceptionIsPlanted("This Tile Has Been Planted!");
            }
            else if (isCooldownOver()) {
                throw new ExceptionPlantStillCooldown("This Plant is On Cooldown!");
            }
            else {
                tile.addEntity(plant);
            }
        }
        catch (ExceptionIsPlanted e) {
            System.out.println(e.getMessage());
        }
        catch (ExceptionPlantStillCooldown e) {
            System.out.println(e.getMessage());
        }
    }

    public void removePlant(int[] position) {
        try {
            Tile tile = gameMap.getTile(position[0], position[1]);
            if (!tile.hasPlanted()) {
                throw new ExceptionIsNotPlanted(null);
            }
            else {
                    tile.removeEntity(null);
            }
        }
        catch (ExceptionIsNotPlanted e) {
            System.out.println(e.getMessage());
        }
    }

}

class ExceptionIsPlanted extends Exception {
    public ExceptionIsPlanted(String message) {
        super(message);
    }
}

class ExceptionIsNotPlanted extends Exception {
    public ExceptionIsNotPlanted(String message) {
        super(message);
    }
}

class ExceptionPlantStillCooldown extends Exception {
    public ExceptionPlantStillCooldown(String message) {
        super(message);
    }
}