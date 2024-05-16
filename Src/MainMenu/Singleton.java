package Src.MainMenu;

public class Singleton {
    private static Singleton instance;
    private Gameplay game;

    private Singleton() {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        else {
            game = new Gameplay();
        }
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
            System.out.println("Singleton created");
        }
        return instance;
    }

    public Gameplay getGame() {
        return game;
    }
}
  
