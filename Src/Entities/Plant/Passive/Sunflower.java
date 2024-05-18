package Src.Entities.Plant.Passive;
import Src.GameMaps.*;

public class Sunflower extends PassivePlant{
    private int sunWaitingTime;
    Sun sun = new Sun();

    public Sunflower(int[] position, GameMap gameMap) {
        super("Sunflower", 300, attackDmg, attackSpd, position, 50, range, 10, gameMap);
        this.sunWaitingTime = 3;
    }

    @Override
    public void attack() {
        try {
            while (true) {
                System.out.println("REGAININGG SUNNNN!!!!!!!!!!!");
                Thread.sleep(sunWaitingTime * 1000);
                Sun.addSun(25);
            }
        } catch (InterruptedException e) {
            System.out.println("Sunflower gagal");
        }
    }
}
