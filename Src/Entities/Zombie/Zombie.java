package Src.Entities.Zombie;

import java.util.ArrayList;

import Src.Entities.Entities;
import Src.GameMaps.GameMap;
import Src.GameMaps.Tile;
import Src.Entities.Plant.Plant;;

public abstract class Zombie extends Entities {
    private boolean isAquatic;
    private boolean slow;

    public Zombie(String name, int health, int attackDmg, int attackSpd, int[] position, boolean isAquatic) {
        super(name, health, attackDmg, attackSpd, position);
        this.isAquatic = isAquatic;
    }

    public boolean getAquatic() {
        return isAquatic;
    }

    public void setAquatic(boolean isAquatic) {
        this.isAquatic = isAquatic;
    }

    public boolean isSlow() {
        return slow;
    }

    public void setSlow(boolean slow) {
        this.slow = slow;
    }

    // ? ini action yang bakal dilakuin sesuai posisi zombienya
    public void action(GameMap gameMap) {
        boolean isplant = false;
        int[] position = getPosition(); // ? Buat posisi zombie
        Tile tile = gameMap.getTile(position[0], position[1]); // ? Buat Nentuin tile zombienya

        ArrayList<Entities> entity = tile.getEntities();

        for (Entities entities : entity) { // ? Ngecek semua isi tile
            if (entities instanceof Plant) { // ? Kalo ada Plant apa yang dilakuin
                isplant = true;
            }
        }

        if (isplant == true) {
            attack(gameMap);
        }

        else {
            walk(gameMap);
        }
    }

    // if there are no plant in the same tile the zombie walk, if there are plant it
    // attack
    public void attack(GameMap gameMap) {
        int[] position = getPosition(); // ? Buat posisi zombie
        Tile tile = gameMap.getTile(position[0], position[1]); // ? Buat Nentuin tile zombienya

        ArrayList<Entities> entity = tile.getEntities();

        for (Entities entities : entity) {
            if (entities instanceof Plant) {
                Plant plant = (Plant) entities;
                plant.setHealth(plant.getHealth() - this.getAttackDmg()); // ? kalo plant darahnya dikurangin sesuai
                if (plant.getHealth() <= 0) {
                    plant.die(gameMap);
                }

            }
        }
    }

    // the object decrease plant health in the same tile based on its atkdmg
    public void walk(GameMap gameMap) {
        int[] position = getPosition(); // Get zombie's current position
        int row = position[0];
        int col = position[1];
        // Calculate the position of the next tile (moving left)
        int nextCol = col - 1;

        // Check if the next tile is within the bounds of the game map
        if (nextCol >= 0) {
            Tile tile = gameMap.getTile(row, col); // Get the current tile
            Tile nextTile = gameMap.getTile(row, nextCol); // Get the next tile

            // Remove the zombie from the current tile
            tile.removeEntity(this);

            // Update the zombie's position
            int[] nextPosition = { row, nextCol };
            setPosition(nextPosition);
            // Add the zombie to the next tile
            nextTile.addEntity(this);
        } else {
            // Zombie has reached the end of the map, you may want to handle this case
            // For example, remove the zombie from the game or trigger a game over condition
        }
    }

    // the object advance in the game map from right to left

}
