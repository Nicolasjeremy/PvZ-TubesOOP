package Src.Entities.Zombie;

import Src.GameMaps.GameMap;

public class ZDuckyTube extends Zombie{
    public ZDuckyTube (int[] position, GameMap gameMap){
        super("DuckyTube", 100, 100, 1, position, true, true, gameMap);
    }
}
