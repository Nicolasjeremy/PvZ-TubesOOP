package Src.GameMaps;

import java.util.Random;

public class Sun {
    // private long sunWaitingTime;
    private static int lastSun;
    private int sunPerProduction = 25;
    private boolean running = true;
    
    public void addSun(int sun){
        synchronized (this){
            lastSun += sun;
            System.out.println("Sun ditambah: " + sun + ", Total sun: " + lastSun);
        }
    }

    public boolean spendSun(int sun) {
        synchronized (this){
            if (lastSun >= sun){
                lastSun -= sun;
                System.out.println("Sun yang digunakan: " + sun + ", Sisa sun: " + lastSun);
                return true;
            } else {
                System.out.println("Sun tidak cukup. Diperlukan: " + sun + ", Sun yang tersedia: " + lastSun);
                return false;
            }
        }
    }

    public void run(){
        try {
            while (running) {
                Thread.sleep((long) (Math.random() * (10000 - 5000) + 5000));
                addSun(25);
            }
        } catch (InterruptedException e) {
                System.out.println("Sun error");
        }
    }

    public void sunerror() {
        running = false;
    }

    public int getSun(){
        return lastSun;
    }
}
