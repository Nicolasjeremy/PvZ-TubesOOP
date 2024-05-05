package Src.GameMaps;

import java.util.Random;

public class Sun {
    private int SunWaitingTime;
    private long LastSun;
    private int SunPerProduction = 25;

    public void SunProducing(int SunWaitingTime, int SunPerProduction){
        Random rand = new Random();

        SunWaitingTime =  rand.nextInt(6) + 5;

        try {
            Thread.sleep(SunWaitingTime * 1000);
        } catch (InterruptedException ssunn){
            ssunn.printStackTrace();
        }
        
        LastSun = LastSun + SunPerProduction;
    }

    public long getSun(){
        return LastSun;
    }
}
