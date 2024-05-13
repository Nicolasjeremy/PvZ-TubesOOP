package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZBucketHead extends Zombie {
    public ZBucketHead(int[] position, GameMap gameMap) {
        super("BucketHead", 300, 100, 1, position, false, false, gameMap);
    }
}
