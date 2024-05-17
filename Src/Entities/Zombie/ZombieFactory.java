package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public interface ZombieFactory {
    Zombie createZombie(int[] position, GameMap gameMap);
}
