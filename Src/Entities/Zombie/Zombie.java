package Src.Entities.Zombie;

import java.util.ArrayList;
import Src.Entities.Entities;
import Src.Entities.Plant.Plant;
import Src.GameMaps.*;
import Src.MainMenu.Gameplay;

public abstract class Zombie extends Entities implements Runnable {
    private boolean isAquatic;
    private boolean slow;
    private boolean special;

    public Zombie(String name, int health, int attackDmg, int attackSpd, int[] position, boolean isAquatic,
            boolean special, GameMap gameMap) {
        super(name, health, attackDmg, attackSpd, position, gameMap);
        this.isAquatic = isAquatic;
        this.special = special;
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

    public boolean getSpecial() {
        return special;
    }

    public void setSpecial(boolean bool) {
        special = bool;
    }

    @Override
    public void die() {
        synchronized (ZombieManager.class) {
            int[] position = this.getPosition();
            Tile tile = getGameMap().getTile(position[0], position[1]);
            tile.removeEntity(this);
            this.stop();
        }
    }

    public void action() {
        Plant plantInTile = null;
        boolean isplant = false;
        int[] position = getPosition();
        Tile tile = getGameMap().getTile(position[0], position[1]);

        ArrayList<Entities> entity = tile.getAllEntities();

        for (Entities entities : entity) {
            if (entities instanceof Plant) {
                isplant = true;
                Plant plant = (Plant) entities;
                plantInTile = plant;
                break;
            } else {
                isplant = false;
            }
        }

        if (isplant == true) {
            if (getSpecial() == true) {
                special(plantInTile);
                setSpecial(false);
            } else {
                attack(plantInTile);
            }
        } else {
            walk(getGameMap());
        }
    }

    // if there are no plant in the same tile the zombie walk, if there are plant it
    // attack
    public void attack(Plant plant) {
        int dmg = this.getAttackDmg();
        plant.setHealth(plant.getHealth() - dmg);
        if (plant.getHealth() <= 0) {
            plant.die();
        }
    }

    public void walk(GameMap gameMap) {
        int[] position = getPosition();
        int row = position[0];
        int col = position[1];
        int nextCol = col - 1;
        if (nextCol >= 0) {
            Tile tile = gameMap.getTile(row, col);
            Tile nextTile = gameMap.getTile(row, nextCol);
            tile.removeEntity(this);
            int[] nextPosition = { row, nextCol };
            setPosition(nextPosition);
            nextTile.addEntity(this);
        } else {
            // todo kalo zombie dah ampe akhir blom dibikin menang
            // Zombie gua bikin mati
            this.die();
            Gameplay.setIsEnd(true);
            Gameplay.setWinningState(false);
        }
    }

    // the object advance in the game map from right to left

    public void special(Plant plant) {
        plant.die();
    }

    public void run() {
        try {
            while (!Gameplay.getIsEnd() && this.getHealth() > 0) {
                if (this.isSlow()) {
                    Thread.sleep(7500);
                    action();
                } else {
                    Thread.sleep(5000);
                    action();
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
