package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZNormal extends Zombie implements ZombieFactory{
    public ZNormal(int[] position, GameMap gameMap, String imagepath) {
        super("Normal", 125, 100, 1, position, false, false, gameMap, imagepath);
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZNormal(position, gameMap, null);
    }

}
