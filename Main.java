import Src.GameMaps.*;
import java.util.Scanner;
// import Src.Entities.*;
import Src.Entities.Plant.Shooter.Peashooter;
import Src.Entities.Zombie.*;
import Src.GameMaps.*;

// public class Main {
//     public static boolean gameEnd = false;

//     public static void main(String[] args) {
//         // Membuat GameMap dengan ukuran 6x9
//         GameMap gameMap = new GameMap(); // Inisiasi GameMap
//         int i = 0;
//         // Membuat entitas baru dengan posisi yang benar
//         int[] sunflowerPosition = { 1, 4 }; // Posisi untuk Sunflower
//         int[] zombiePosition = { 1, 7 }; // Posisi untuk Zombie
//         ZombieManager zombieManager = new ZombieManager(gameMap);
//         Thread zombieManagerThread = new Thread(zombieManager);
//         Peashooter PeaShooter = new Peashooter("Sunflower", sunflowerPosition, gameMap); // Tanaman dengan posisi
//         ZBucketHead regularZombie1 = new ZBucketHead("NJ Zombie", zombiePosition, gameMap); // Zombie dengan posisi
//         Thread zombiThreadd = new Thread(regularZombie1);
//         Tile koor11 = gameMap.getTile(1, 4);
//         Tile koor17 = gameMap.getTile(1, 7);
//         for (int j = 0; j < 5; j++) {
//             zombieManager.spawnZombie();
//         }
//         gameMap.displayMap();
//         // Menambahkan entitas ke GameMap pada posisi yang benar
//         koor11.addEntity(PeaShooter); // Tambah PeaShooter ke Lawn
//         koor17.addEntity(regularZombie1); // Tambah Zombie ke ZombieSpawn
//         System.out.println("GameMap after adding entities:");
//         gameMap.displayMap(); // Menampilkan representasi GameMap
//         zombiThreadd.start();
//         zombieManagerThread.start();
//         while (i < 8) {
//             Scanner scanner = new Scanner(System.in);
//             System.out.println("Masukan command anda:");
//             int perintah = scanner.nextInt();
//             if (perintah == 1) {
//                 System.out.println("taro peashooter");
//             } else if (perintah == 2) {
//                 System.out.println("taro wallnut");
//             }
//             gameMap.displayMap();
//             i++;
//         }
//     }
// }

public class Main {
    public static boolean gameEnd = false;

    public static void main(String[] args) {
        // Membuat GameMap dengan ukuran 6x9
        GameMap gameMap = new GameMap(); // Inisiasi GameMap
        int i = 0;
        ZombieManager zombieManager = new ZombieManager(gameMap);
        Thread zombieManagerThread = new Thread(zombieManager);
        zombieManagerThread.start();
        while (i < 8) {
            try {
                Thread.sleep(3000);
                gameMap.displayMap();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}