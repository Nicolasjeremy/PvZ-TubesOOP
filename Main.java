import Src.GameMaps.*;
// import Src.Entities.*;
import Src.Entities.Plant.Shooter.Peashooter;
import Src.Entities.Zombie.*;

public class Main {
    public static void main(String[] args) {
        // Membuat GameMap dengan ukuran 6x9
        GameMap gameMap = new GameMap(); // Inisiasi GameMap

        // Membuat entitas baru dengan posisi yang benar
        int[] sunflowerPosition = { 1, 4 }; // Posisi untuk Sunflower
        int[] zombiePosition = { 1, 7 }; // Posisi untuk Zombie

        Peashooter sunflower = new Peashooter("Sunflower", sunflowerPosition); // Tanaman dengan posisi
        ZBucketHead regularZombie = new ZBucketHead("Regular Zombie", zombiePosition); // Zombie dengan posisi
        ZBucketHead regularZombie1 = new ZBucketHead("NJ Zombie", zombiePosition); // Zombie dengan posisi

        Tile koor11 = gameMap.getTile(1, 4);
        Tile koor17 = gameMap.getTile(1, 7);

        gameMap.displayMap();
        // Menambahkan entitas ke GameMap pada posisi yang benar
        koor11.addEntity(sunflower); // Tambah Sunflower ke Lawn
        koor17.addEntity(regularZombie); // Tambah Zombie ke ZombieSpawn

        // Menampilkan GameMap
        System.out.println("GameMap after adding entities:");
        gameMap.displayMap(); // Menampilkan representasi GameMap

        koor11.addEntity(sunflower); // Tambah Sunflower ke Lawn
        koor17.addEntity(regularZombie1);

        System.out.println("GameMap after adding entities:");
        gameMap.displayMap(); // Menampilkan representasi GameMap

        // Menghapus entitas dari GameMap
        koor11.removeEntity(sunflower); // Tambah Sunflower ke Lawn
        koor17.removeEntity(regularZombie); // Tambah Zombie ke ZombieSpawn
        regularZombie1.action(gameMap);

        // Menampilkan GameMap setelah penghapusan entitas
        System.out.println("\nGameMap after removing entities:");
        System.out.println(sunflower.getHealth());
        gameMap.displayMap(); // Menampilkan representasi GameMap setelah penghapusan
        regularZombie1.action(gameMap);
        System.out.println("\nGameMap after removing entities:");
        gameMap.displayMap(); // Menampilkan representasi GameMap setelah penghapusan
        regularZombie1.action(gameMap);
        System.out.println("\nGameMap after removing entities:");
        gameMap.displayMap(); // Menampilkan representasi GameMap setelah penghapusan
        regularZombie1.action(gameMap);
        System.out.println("\nGameMap after removing entities:");
        gameMap.displayMap(); // Menampilkan representasi GameMap setelah penghapusan
        regularZombie1.action(gameMap);
        System.out.println("\nGameMap after removing entities:");
        gameMap.displayMap(); // Menampilkan representasi GameMap setelah penghapusan
        regularZombie1.action(gameMap);
        System.out.println("\nGameMap after removing entities:");
        gameMap.displayMap(); // Menampilkan representasi GameMap setelah penghapusan
        regularZombie1.action(gameMap);
        System.out.println("\nGameMap after removing entities:");
        gameMap.displayMap(); // Menampilkan representasi GameMap setelah penghapusan
        regularZombie1.action(gameMap);
        System.out.println(sunflower.getHealth());
    }
}
