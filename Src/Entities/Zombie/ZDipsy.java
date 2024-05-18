package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDipsy extends Zombie implements ZombieFactory{
    public ZDipsy(int[] position, GameMap gameMap) {
        super("Dipsy", 200, 125, 1, position, false, false, gameMap, "../Image/ZombieImage/Dipsy.png");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZDipsy(position, gameMap);
    }

}
