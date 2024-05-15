package Src.Entities.Plant.Projectile;

import Src.GameMaps.*;

import java.util.ArrayList;

import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;

public class Projectile extends Entities implements Runnable {
    private ArrayList<Zombie> ListZombie;

    public Projectile(int damage, int[] Position, GameMap gameMap) {
        super("Projetiles", 0, damage, 0, Position, gameMap);
        this.ListZombie = new ArrayList<>();
    }

    public void action() {
        boolean isZombie = false;
        int[] position = getPosition();
        Tile tile = getGameMap().getTile(position[0], position[1]);

        ArrayList<Entities> entity = tile.getEntities();

        for (Entities entities : entity) { // ? Ngecek semua isi tile
            if (entities instanceof Zombie) { // ? Kalo ada Plant apa yang dilakuin
                isZombie = true;
                Zombie zombie = (Zombie) entities;
                ListZombie.add(zombie);
            } else {
            }
        }

        if (isZombie == true) {
            for (Zombie zombie : ListZombie) {
                zombie.setHealth(getHealth() - this.getAttackDmg());
                if (zombie.getHealth() <= 0) {
                    zombie.die(zombie.getGameMap());
                } else {
                }
            }
            getGameMap().getTile(position[0], position[1]).removeEntity(this);
        }

        else {
            walk(getGameMap());
        }

    }

    public void walk(GameMap gameMap) {
        int[] position = getPosition(); // Get zombie's current position
        int row = position[0];
        int col = position[1];
        // Calculate the position of the next tile (moving left)
        int nextCol = col + 1;

        // Check if the next tile is within the bounds of the game map
        if (nextCol <= 9) {
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

    public void run() {
        int i = 0;
        try {
            while (i < 15) {
                Thread.sleep(2000);
                action();
                i++;

            }
        } catch (InterruptedException e) {
        }
    }

}
