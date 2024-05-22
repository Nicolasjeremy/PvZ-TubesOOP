package Src.Entities.Plant.Projectile;

import Src.GameMaps.*;

import Src.GUI.*;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Zombie.Zombie;

public class Projectile extends Entities implements Runnable {
    private ArrayList<Zombie> ListZombie;
    private StartGameMapPanel gamePanel;

    public Projectile(int damage, int[] Position, GameMap gameMap) {
        super("Projectile", 0, damage, 0, Position, gameMap, "../Image/PlantImage/Pea.png");
        this.ListZombie = new ArrayList<>();
    }

    public synchronized void action() {
        boolean isZombie = false;
        int[] position = getPosition();
        Tile tile = getGameMap().getTile(position[0], position[1]);

        ArrayList<Entities> entity = tile.getAllEntities();

        for (Entities entities : entity) { // ? Ngecek semua isi tile
            if (entities instanceof Zombie) { // ? Kalo ada zombie apa yang dilakuin
                isZombie = true;
                Zombie zombie = (Zombie) entities;
                ListZombie.add(zombie);
            } else {
            }
        }

        if (isZombie == true) {
            for (Zombie zombie : ListZombie) {
                zombie.setHealth(zombie.getHealth() - this.getAttackDmg());
                if (zombie.getHealth() <= 0) {
                    zombie.die();
                } else {
                }
            }
            this.die();
            this.stop();
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
            Tile tile = gameMap.getTile(row, col);
            Tile nextTile = gameMap.getTile(row, nextCol);
            tile.removeEntity(this);
            int[] nextPosition = { row, nextCol };
            setPosition(nextPosition);
            nextTile.addEntity(this);
        } else {
            this.die();
            this.stop();
        }
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                action();

            }
        } catch (InterruptedException e) {
        }
    }

}
