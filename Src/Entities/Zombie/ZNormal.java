package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZNormal extends Zombie {
    public ZNormal(int[] position, GameMap gameMap) {
        super("Normal", 125, 100, 1, position, false, false, gameMap);
    }
}
