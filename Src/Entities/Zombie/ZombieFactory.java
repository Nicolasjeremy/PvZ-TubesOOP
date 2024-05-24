package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public interface ZombieFactory<T extends Zombie> {
    Zombie createZombie(int[] position, GameMap gameMap);
}
