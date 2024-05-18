package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZBucketHead extends Zombie implements ZombieFactory{
    public ZBucketHead(int[] position, GameMap gameMap) {
        super("BucketHead", 300, 100, 1, position, false, false, gameMap, "../Image/ZombieImage/BucketZ.png");
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZBucketHead(position, gameMap);
    }

}
