package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZAsep extends Zombie { // ! Zombie special dimana gabisa di freeze
    public ZAsep(String name, int[] position, GameMap gameMap) {
        super(name, 125, 100, 1, position, false, false, gameMap);
    }

    @Override
    public void setSlow(boolean slow) {
        super.setSlow(false);
    }
}
