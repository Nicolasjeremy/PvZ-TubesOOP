

import java.util.ArrayList;
import java.util.Scanner;

import Src.Entities.Plant.Plant;
import Src.GameMaps.GameMap;
import Src.MainMenu.Command;
import Src.MainMenu.Deck;
import Src.MainMenu.Gameplay;
import Src.MainMenu.Inventory;
import Src.MainMenu.Singleton;

public class Main {    public static void main(String[] args) {
        System.out.println("Selamat datang di game pvz");
        // Thread.sleep(1000);
        Boolean Menu_status = true;
        Boolean game_status = false;

        Scanner scanner = new Scanner(System.in);
        while (Menu_status) {
            System.out.println("Masukan command anda:");
            String perintah = scanner.nextLine();
            // StringBuilder sb = new StringBuilder(perintah);
            // System.out.println(sb);
            Command command = new Command();

            if (perintah.equals("Start")) {
                if (game_status == false) {
                    game_status = true;
                } else {
                    System.out.println("Game sudah berjalan");
                }
            } else if (perintah.equals("Help")) {
                command.help();
            } else if (perintah.equals("Zombie List")) {
                command.zombielist();
            } else if (perintah.equals("Plant List")) {
                command.plantlist();
            } else if (perintah.equals("Exit")) {
                command.exit();
                Menu_status = false;
                game_status = false;
                
            } else {
                System.out.println("Perintah tidak dikenali");
            }
            while (game_status) {
                command.start();
                Singleton singleton = Singleton.getInstance();
                Gameplay gameplay = singleton.getGame();
                
                GameMap gameMap = new GameMap();

                // Initialize Inventory, Deck, And Filling Deck
                Deck deck = new Deck(gameMap);
                Inventory inventory = new Inventory(deck);
                
                System.out.print("Want Autofill? (Yes/No): ");
                String inputuser = scanner.next();
                if (inputuser.equals("Yes")) {
                    ArrayList<Plant> inventoryCopy = new ArrayList<>(inventory.getInventory());
                    for (Plant i : inventoryCopy) {
                        inventory.addDeck(i);
                    }
                }
                else {
                    DriverInventory.InventoryDeck(deck, inventory);
                }

                // Playing the game
                System.out.println("Prepare For Playing The Game In 5 Sec");
                int count = 5;
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(count);
                        count--;
                    }
                    catch (Exception e) {
                    }
                }

                try {
                    Thread.sleep(1000);
                    System.out.println("Game Start!"); 
                }
                catch (Exception e) {
                }
                gameplay.setGameMap(gameMap);
                gameplay.setDeck(deck);
                gameplay.setInventory(inventory);
                System.out.println("Data Game Map, Deck, dan Inventory Tersimpan");
                Thread gameplayThread = new Thread(gameplay);
                gameplayThread.start();
                
                // GamePlay
                while (game_status && !gameplay.getIsEnd()) {
                    System.out.println("\n1. Plant");
                    System.out.println("2. Unplant");
                    System.out.println("3. Display Map");
                    System.out.println("4. Quit");
                    System.out.println("5. ShowDeck");
                    System.out.println("6. ShowTime");
                    System.out.print("Choose an option: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        scanner.next(); // Clear invalid input
                    }

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            System.out.print("Enter the name of the plant to plant: ");
                            String plantNameToPlant = scanner.nextLine();
                            Plant plantToPlant = deck.getPlantFromDeck(deck, plantNameToPlant);
                            if (plantToPlant != null) {
                                System.out.print("Enter the row and column to plant (e.g., 2 3): ");
                                int row = scanner.nextInt();
                                int col = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                int[] position = { row, col };
                                deck.planting(plantToPlant, position);
                            } else {
                                System.out.println("Plant not found in deck!");
                            }
                            break;
                        case 2:
                            System.out.print("Enter the row and column to unplant (e.g., 2 3): ");
                            int row = scanner.nextInt();
                            int col = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            int[] position = { row, col };
                            deck.unPlanting(position);
                            break;
                        case 3:
                            gameMap.displayMap();
                            break;
                        case 4:
                            System.out.println("Exiting...");
                            game_status = false;
                            gameplayThread.interrupt();
                            System.out.println("Kembali ke Main Menu...");
                            break;
                        case 5:
                            deck.displayDeck();
                            break;
                        case 6:
                            System.out.println("Current time: " + gameplay.getCurrentTime());
                            break;
                        default:
                            System.out.println("Invalid option!");
                            break;
                    }
                    
                }
                if (gameplay.getIsEnd()) {
                    System.out.println("Game Over ;(");
                    game_status = false;
                    gameplayThread.interrupt();
                    gameplay.resetAttributes();
                }

            }
            
        }
        scanner.close();
    }
}
