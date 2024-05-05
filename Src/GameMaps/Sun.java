package Src.GameMaps;

import java.util.Random;

public class Sun {
    private int sunWaitingTime;
    private static int lastSun;
    private int sunPerProduction = 25;

    public static void SunProducing(int sunWaitingTime, int sunPerProduction){
        Random rand = new Random();

        sunWaitingTime =  rand.nextInt(6) + 5;

        try {
            Thread.sleep(sunWaitingTime * 1000);
        } catch (InterruptedException ssunn){
            ssunn.printStackTrace();
        }
        
        lastSun = lastSun + sunPerProduction;
    }

    public long getSun(){
        return lastSun;
    }
}
