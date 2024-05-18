package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDuckyTube extends Zombie implements ZombieFactory{
    public ZDuckyTube (int[] position, GameMap gameMap, String imagepath){
        super("DuckyTube", 100, 100, 1, position, true, true, gameMap, imagepath);
    }

    public Zombie createZombie(int[] position, GameMap gameMap) {
        return new ZDuckyTube(position, gameMap, null);
    }

}
