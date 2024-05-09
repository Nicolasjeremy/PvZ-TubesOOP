package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZAsep extends Zombie { // ! Zombie special dimana gabisa di freeze
    public ZAsep(String name, int[] position) {
        super(name, 125, 100, 1, position, false, false);
    }

    @Override
    public void setSlow(boolean slow) {
        super.setSlow(false);
    }
}
