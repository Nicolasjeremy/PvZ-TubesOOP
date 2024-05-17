package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDuckyTube extends Zombie implements ZombieFactory{
    public ZDuckyTube (int[] position, GameMap gameMap){
        super("DuckyTube", 100, 100, 1, position, true, true, gameMap);
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZDuckyTube(position, gameMap);
    }

}
