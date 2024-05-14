package Src.Entities.Plant.Melee;
import Src.GameMaps.*;

public class TangleKelp extends MeleePlant{
    public TangleKelp(int[] position, GameMap gameMap) {
        super("TangleKelp", 100, attackDmg, attackSpd, position, 50, range, 20, gameMap);
    }    
}
