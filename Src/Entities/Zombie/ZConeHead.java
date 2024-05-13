package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZConeHead extends Zombie {
    public ZConeHead(int[] position, GameMap gameMap) {
        super("ConeHead", 250, 100, 1, position, false, false, gameMap);
    }
}
