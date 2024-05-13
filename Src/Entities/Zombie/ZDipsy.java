package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDipsy extends Zombie{
    public ZDipsy(int[] position, GameMap gameMap) {
        super("Dipsy", 200, 125, 1, position, false, false, gameMap);
    }
}
