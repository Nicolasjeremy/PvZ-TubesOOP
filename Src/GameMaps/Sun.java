package Src.GameMaps;

public class Sun implements Runnable {
    // private long sunWaitingTime;
    private static int sun;
    // private int sunPerProduction = 25;
    private boolean Sunrunning = true;

    public Sun(){
        this.Sunrunning = true;
        Sun.sun = 25;
    }
    
    public void addSun(int addsun){
        synchronized (this){
            sun += addsun;
            // System.out.println("Sun ditambah: " + addsun + ", Total sun: " + sun);
        }
    }

    public boolean spendSun(int spendsun) {
        synchronized (this){
            if (sun >= spendsun){
                sun -= spendsun;
                System.out.println("Sun yang digunakan: " + spendsun + ", Sisa sun: " + sun);
                return true;
            } else {
                System.out.println("Sun tidak cukup. Diperlukan: " + spendsun + ", Sun yang tersedia: " + sun);
                return false;
            }
        }
    }

    public void sunerror() {
        Sunrunning = false;
    }

    public int getSun(){
        return sun;
    }

    @Override
    public void run() {
        try {
            while (Sunrunning) {
                Thread.sleep((long) (Math.random() * (10000 - 5000) + 5000));
                addSun(25);
            }
        } catch (InterruptedException e) {
                System.out.println("Sun error");
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
