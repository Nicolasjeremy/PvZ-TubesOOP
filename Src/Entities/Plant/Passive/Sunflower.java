package Src.Entities.Plant.Passive;

public class Sunflower extends PassivePlant{
    private int sunWaitingTime;
    private int lastSun;
    private int sun;

    public Sunflower(String name, int[] position, int sunWaitingTime, int lastSun) {
        super(name, 300, attackDmg, attackSpd, position, 50, range, 10);
        this.sunWaitingTime = 10;
        this.lastSun = lastSun;
        this.sun = 25;
    }

    public int generateSun() {
		int currentCountDown = generateSunCountdown(lastSun);
		if (currentCountDown == 0) {
			lastSun = sunWaitingTime;
			return sun;
		}
		else {
			return 0;
		}
	}

	private int generateSunCountdown(int start) {
		if (start != 0) {
			lastSun--;
			start = lastSun;
		}
		return start;
	}
}
