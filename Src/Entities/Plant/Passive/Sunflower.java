package Src.Entities.Plant.Passive;
import Src.GameMaps.*;

public class Sunflower extends PassivePlant{
    private int sunWaitingTime;
    private int sun;
	private Sun sunInstance;

    public Sunflower(int[] position, int sunWaitingTime, int lastSun, GameMap gameMap) {
        super("Sunflower", 300, attackDmg, attackSpd, position, 50, range, 10, gameMap);
        this.sunWaitingTime = 10;
        this.sun = 25;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(sunWaitingTime * 1000);
                sunInstance.addSun(sun);
            }
        } catch (InterruptedException e) {
        }
    }
}
