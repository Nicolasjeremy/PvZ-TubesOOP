package Src.Entities.Plant.Melee;
import Src.GameMaps.*;
import Src.Entities.Entities;
import Src.Entities.Plant.Plant;
import Src.Entities.Zombie.*;

public class MeleePlant extends Plant{
    public static final int attackDmg = 5000;
    public static final int attackSpd = 0;
    public static final int range = 1;

    public MeleePlant(String name, int health, int attackDmg, int attackSpd, int[] position, int cost, int range, int cooldown, GameMap gameMap, String imagepath) {
        super(name, health, attackDmg, attackSpd, position, cost, range, cooldown, gameMap, imagepath);
    }

    public void attack() {};

    public void run() {
        while (true) {
            try {
                boolean cek = false;
                Tile zombieDetected = this.getGameMap().getTile(getPosition()[0], getPosition()[1]);
                for (Entities zombie : zombieDetected.getAllEntities()) {
                    if (zombie instanceof Zombie) {
                        cek = true;
                    }
                }
                if (cek == true) {
                    this.attack();
                }
            }
            catch (Exception e) {
            }
        }
    }
}
