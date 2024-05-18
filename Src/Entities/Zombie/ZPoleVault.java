package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZPoleVault extends Zombie implements ZombieFactory {
    public ZPoleVault(int[] position, GameMap gameMap) {
        super("PoleVault", 175, 100, 1, position, false, true, gameMap, "../Image/ZombieImage/PoleZ.png");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZPoleVault(position, gameMap);
    }

}