package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZConeHead extends Zombie implements ZombieFactory{
    public ZConeHead(int[] position, GameMap gameMap) {
        super("ConeHead", 250, 100, 1, position, false, false, gameMap);
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZConeHead(position, gameMap);
    }

}
