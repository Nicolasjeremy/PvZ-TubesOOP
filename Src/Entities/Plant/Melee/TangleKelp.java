package Src.Entities.Plant.Melee;
import Src.GameMaps.*;

public class TangleKelp extends MeleePlant{
    public TangleKelp(String name, int[] position, GameMap gameMap) {
        super(name, 100, attackDmg, attackSpd, position, 50, range, 20, gameMap);
    }    
}
