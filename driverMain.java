// import Src.GameMaps.*;
// import Src.MainMenu.Deck;
// import Src.MainMenu.Inventory;

// import java.util.ArrayList;
// import java.util.Scanner;

// import Src.Entities.Plant.Plant;
// // import Src.Entities.*;
// import Src.Entities.Plant.Shooter.Peashooter;
// import Src.Entities.Zombie.*;
// import Src.GameMaps.*;
// // import Src.Entities.Plant.Projectile.*;

// // Driver Pertama !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// // public class Main {
// //     public static boolean gameEnd = false;

// //     public static void main(String[] args) {
// //         // Membuat GameMap dengan ukuran 6x9
// //         GameMap gameMap = new GameMap(); // Inisiasi GameMap
// //         int i = 0;
// //         // Membuat entitas baru dengan posisi yang benar
// //         int[] sunflowerPosition = { 1, 4 }; // Posisi untuk Sunflower
// //         int[] zombiePosition = { 1, 7 }; // Posisi untuk Zombie
// //         ZombieManager zombieManager = new ZombieManager(gameMap);
// //         Thread zombieManagerThread = new Thread(zombieManager);
// //         Peashooter PeaShooter = new Peashooter("Sunflower", sunflowerPosition, gameMap); // Tanaman dengan posisi
// //         ZBucketHead regularZombie1 = new ZBucketHead("NJ Zombie", zombiePosition, gameMap); // Zombie dengan posisi
// //         Thread zombiThreadd = new Thread(regularZombie1);
// //         Tile koor11 = gameMap.getTile(1, 4);
// //         Tile koor17 = gameMap.getTile(1, 7);
// //         for (int j = 0; j < 5; j++) {
// //             zombieManager.spawnZombie();
// //         }
// //         gameMap.displayMap();
// //         // Menambahkan entitas ke GameMap pada posisi yang benar
// //         koor11.addEntity(PeaShooter); // Tambah PeaShooter ke Lawn
// //         koor17.addEntity(regularZombie1); // Tambah Zombie ke ZombieSpawn
// //         System.out.println("GameMap after adding entities:");
// //         gameMap.displayMap(); // Menampilkan representasi GameMap
// //         zombiThreadd.start();
// //         zombieManagerThread.start();
// //         while (i < 8) {
// //             Scanner scanner = new Scanner(System.in);
// //             System.out.println("Masukan command anda:");
// //             int perintah = scanner.nextInt();
// //             if (perintah == 1) {
// //                 System.out.println("taro peashooter");
// //             } else if (perintah == 2) {
// //                 System.out.println("taro wallnut");
// //             }
// //             gameMap.displayMap();
// //             i++;
// //         }
// //     }
// // }


// // Driver Kedua !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// // public class Main {
// //     public static boolean gameEnd = false;

// //     public static void main(String[] args) {
// //         // Initialize GameMap
// //         GameMap gameMap = new GameMap();

// //         // Initialize plant position
// //         int[] peaPosition = { 2, 2 };
        
// //         Peashooter peashooter = new Peashooter(peaPosition, gameMap);
// //         Thread plantThread = new Thread(peashooter);
// //         plantThread.start();

// //         // Add plant to the map
// //         gameMap.getTile(peaPosition[0], peaPosition[1]).addEntity(peashooter);

// //         // Initialize ZombieManager
// //         ZombieManager zombieManager = new ZombieManager(gameMap);
// //         Thread zombieManagerThread = new Thread(zombieManager);
// //         zombieManagerThread.start();

// //         // Main game loop
// //         int i = 0;
// //         while (i < 8) {
// //             try {
// //                 Thread.sleep(3000);
// //                 gameMap.displayMap();
// //             } catch (InterruptedException e) {
// //                 e.printStackTrace();
// //             }
// //             i++;
// //         }
// //     }
// // }


// // Driver Ketiga !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// public class driverMain {
//     public static boolean gameEnd = false;

//     public static void main(String[] args) {
//         // Initialize GameMap
//         GameMap gameMap = new GameMap();

//         // Initialize Inventory, Deck, And Filling Deck
//         Deck deck = new Deck(gameMap);
//         Inventory inventory = new Inventory(deck);
        
//         Scanner scanner = new Scanner(System.in);
//         System.out.print("Want Autofill? (Yes/No): ");
//         String inputuser = scanner.next();
//         if (inputuser.equals("Yes")) {
//             ArrayList<Plant> inventoryCopy = new ArrayList<>(inventory.getInventory());
//             for (int i = 0; i < inventoryCopy.size(); i++) {
//                 inventory.addDeck(i);
//             }
//         }
//         else {
//             DriverInventory.InventoryDeck(deck, inventory);
//         }

//         // Playing the game
//         System.out.println("Prepare For Playing The Game In 5 Sec");
//         int count = 5;
//         for (int i = 0; i < 5; i++) {
//             try {
//                 Thread.sleep(1000);
//                 System.out.println(count);
//                 count--;
//             }
//             catch (Exception e) {
//             }
//         }

//         try {
//             Thread.sleep(1000);
//             System.out.println("Game Start!"); 
//         }
//         catch (Exception e) {
//         }

//         // Initialize ZombieManager
//         ZombieManager zombieManager = new ZombieManager(gameMap);
//         Thread zombieManagerThread = new Thread(zombieManager);
//         zombieManagerThread.start();

//         // Thread displayThread = new Thread(() -> {
//         //     while (!gameEnd) {
//         //         try {
//         //             Thread.sleep(3000);
//         //             gameMap.displayMap();
//         //         } catch (InterruptedException e) {
//         //             e.printStackTrace();
//         //         }
//         //     }
//         // });
//         // displayThread.start();

//         // GamePlay
//         while (!gameEnd) {
//             System.out.println("\n1. Plant");
//             System.out.println("2. Unplant");
//             System.out.println("3. Display Map");
//             System.out.println("4. Quit");
//             System.out.println("5. ShowDeck");
//             System.out.print("Choose an option: ");

//             while (!scanner.hasNextInt()) {
//                 System.out.println("Please enter a valid number.");
//                 scanner.next(); // Clear invalid input
//             }

//             int choice = scanner.nextInt();
//             scanner.nextLine(); // Consume newline

//             switch (choice) {
//                 case 1:
//                     System.out.print("Enter the name of the plant to plant: ");
//                     String plantNameToPlant = scanner.nextLine();
//                     Plant plantToPlant = deck.getPlantFromDeck(deck, plantNameToPlant);
//                     if (plantToPlant != null) {
//                         System.out.print("Enter the row and column to plant (e.g., 2 3): ");
//                         int row = scanner.nextInt();
//                         int col = scanner.nextInt();
//                         scanner.nextLine(); // Consume newline
//                         int[] position = { row, col };
//                         deck.planting(plantToPlant, position);
//                     } else {
//                         System.out.println("Plant not found in deck!");
//                     }
//                     break;
//                 case 2:
//                     System.out.print("Enter the row and column to unplant (e.g., 2 3): ");
//                     int row = scanner.nextInt();
//                     int col = scanner.nextInt();
//                     scanner.nextLine(); // Consume newline
//                     int[] position = { row, col };
//                     deck.unPlanting(position);
//                     break;
//                 case 3:
//                     gameMap.displayMap();
//                     break;
//                 case 4:
//                     System.out.println("Exiting...");
//                     gameEnd = true;
//                     break;
//                 case 5:
//                     deck.displayDeck();
//                 default:
//                     System.out.println("Invalid option!");
//                     break;
//             }
//         }

//         scanner.close();
//         // Main game loop
        
//     }
// }
