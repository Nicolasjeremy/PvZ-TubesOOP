package Src.MainMenu;

public class driverGameplay {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        if (singleton1 == singleton2) {
            System.out.println("Singleton works, both instances are the same");
        } else {
            System.out.println("Singleton failed, instances are different");
        }

        // Now you can use the 'game' object from the singleton
        Gameplay game = singleton1.getGame();
        Thread gameplayThread = new Thread(game);
        gameplayThread.start(); // for example
        Gameplay game2 = singleton2.getGame();
        Thread gameplayThread2 = new Thread(game2);
        gameplayThread2.start();
        
        if (gameplayThread.isAlive() && gameplayThread2.isAlive()) {
            System.out.println("Both threads are running at the same time");
        } else {
            System.out.println("Both threads are not running at the same time");
        }
    }
}
