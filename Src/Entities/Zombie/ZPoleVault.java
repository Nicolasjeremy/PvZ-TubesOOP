package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZPoleVault extends Zombie {
    public ZPoleVault(int[] position, GameMap gameMap) {
        super("PoleVault", 175, 100, 1, position, false, true, gameMap);
    }
}