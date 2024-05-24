package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZNormal extends Zombie implements ZombieFactory<ZNormal>{
    public ZNormal(int[] position, GameMap gameMap) {
        super("Normal", 125, 100, 1, position, false, false, gameMap, "../Image/ZombieImage/NormalZ.png");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZNormal(position, gameMap);
    }

}
