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
    public void die(GameMap gameMap) {
        int[] position = getPosition();
        Tile tile = gameMap.getTile(position[0], position[1]);
        tile.removeEntity(this);
        ZombieManager.ZombieCounter--;
    }

    // ? ini action yang bakal dilakuin sesuai posisi zombienya
    public void action() {
        Plant plantInTile = null;
        boolean isplant = false;
        int[] position = getPosition(); // ? Buat posisi zombie
        Tile tile = getGameMap().getTile(position[0], position[1]); // ? Buat Nentuin tile zombienya

        ArrayList<Entities> entity = tile.getEntities();

        for (Entities entities : entity) { // ? Ngecek semua isi tile
            if (entities instanceof Plant) { // ? Kalo ada Plant apa yang dilakuin
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
                special(getGameMap(), plantInTile);
                setSpecial(false);
            } else {
                attack(getGameMap(), plantInTile);
            }
        }

        else {
            walk(getGameMap());
        }
    }

    // if there are no plant in the same tile the zombie walk, if there are plant it
    // attack
    public void attack(GameMap gameMap, Plant plant) {
        int dmg = this.getAttackDmg();
        plant.setHealth(plant.getHealth() - dmg); // ? kalo plant, darahnya dikurangin sesuai
        if (plant.getHealth() <= 0) {
            plant.die(gameMap);
        }

    }

    // the object decrease plant health in the same tile based on its atkdmg
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
            //todo kalo zombie dah ampe akhir blom dibikin menang
            // Zombie gua bikin mati
            this.die(gameMap);
            Gameplay.setIsEnd(true);
            Gameplay.setWinningState(false);
        }
    }

    // the object advance in the game map from right to left

    public void special(GameMap gameMap, Plant plant) {
        plant.die(gameMap);
    }

    public void run() { // ? Methode RUN Zombie
        try {
            while (true) { // todo: ni Blom diubah whilenya harusnya selama blom menang
                if (this.isSlow() == true) { // ! Blom dites
                    Thread.sleep(7500);// todo: jadi kalo udah 3 detik ga di slow, efek slownya ilang
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
