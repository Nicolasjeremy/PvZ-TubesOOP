package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZLala extends Zombie implements ZombieFactory{
    public ZLala(int[] position, GameMap gameMap) {
        super("Lala", 100, 200, 1, position, false, false, gameMap);
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZLala(position, gameMap);
    }

}
