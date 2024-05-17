import java.util.ArrayList;
import java.util.Scanner;

import Src.Entities.Plant.Plant;
import Src.GameMaps.GameMap;
import Src.GameMaps.Sun;
import Src.MainMenu.Command;
import Src.MainMenu.Deck;
import Src.MainMenu.Gameplay;
import Src.MainMenu.Inventory;
import Src.MainMenu.Singleton;

public class Main {
    public static void main(String[] args) {
        System.out.println("Selamat datang di game pvz");
        Boolean menuStatus = true;
        Boolean gameStatus = false;

        Scanner scanner = new Scanner(System.in);
        while (menuStatus) {
            System.out.println("Masukan command anda:");
            String perintah = scanner.nextLine();
            Command command = new Command();

            if (perintah.equalsIgnoreCase("Start")) {
                if (!gameStatus) {
                    gameStatus = true;
                } else {
                    System.out.println("Game sudah berjalan");
                }
            } else if (perintah.equalsIgnoreCase("Help")) {
                command.help();
            } else if (perintah.equalsIgnoreCase("Zombie List")) {
                command.zombielist();
            } else if (perintah.equalsIgnoreCase("Plant List")) {
                command.plantlist();
            } else if (perintah.equalsIgnoreCase("Exit")) {
                command.exit();
                menuStatus = false;
                gameStatus = false;
            } else {
                System.out.println("Perintah tidak dikenali");
            }

            while (gameStatus) {
                command.start();
                Singleton singleton = Singleton.getInstance();
                Gameplay gameplay = singleton.getGame();

                GameMap gameMap = new GameMap();


                // Initialize Inventory, Deck, And Filling Deck
                Deck deck = new Deck(gameMap);
                Inventory inventory = new Inventory(deck);

                System.out.print("Want Autofill? (Yes/No): ");
                String inputuser = scanner.next();
                if (inputuser.equalsIgnoreCase("Yes")) {
                    ArrayList<Plant> inventoryCopy = new ArrayList<>(inventory.getInventory());
                    for (Plant plant : inventoryCopy) {
                        inventory.addDeck(inventory.getInventory().indexOf(plant));
                    }
                } else {
                    // Manual fill deck
                    // boolean autofill = false;
                    // while (!autofill) {
                    //     inventory.displayInventory();
                    //     System.out.print("Enter the index of the plant to add to the deck (0 to stop): ");
                    //     int index = scanner.nextInt() - 1;
                    //     if (index == -1) {
                    //         autofill = true;
                    //     } else {
                    //         inventory.addDeck(index);
                    //     }
                    // }
                    DriverInventory.InventoryDeck(deck, inventory, scanner);
                }

                // Playing the game
                System.out.println("Prepare For Playing The Game In 3 Sec");
                int count = 3;
                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(count);
                        count--;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Countdown gagal berjalan");
                    }
                }

                try {
                    Thread.sleep(1000);
                    System.out.println("Game Start!");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Game gagal berjalan");
                }

                gameplay.setGameMap(gameMap);
                gameplay.setDeck(deck);
                gameplay.setInventory(inventory);
                System.out.println("Data Game Map, Deck, dan Inventory Tersimpan");
                Thread gameplayThread = new Thread(gameplay);
                gameplayThread.start();

                // GamePlay
                while (gameStatus && !Gameplay.getIsEnd()) {
                    System.out.println("\n1. Plant");
                    System.out.println("2. Unplant");
                    System.out.println("3. Display Map");
                    System.out.println("4. Quit");
                    System.out.println("5. ShowDeck");
                    System.out.println("6. ShowTime");
                    System.out.println("7. Display Sun");
                    System.out.print("Choose an option: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        scanner.next(); // Clear invalid input
                    }

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            deck.displayDeck();
                            System.out.print("Enter the index of the plant to plant: ");
                            int plantIndexToPlant = scanner.nextInt() - 1;
                            Plant plantToPlant = deck.getPlantFromDeck(plantIndexToPlant);
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
                            int rowToUnplant = scanner.nextInt();
                            int colToUnplant = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            int[] positionToUnplant = { rowToUnplant, colToUnplant };
                            deck.unPlanting(positionToUnplant);
                            break;
                        case 3:
                            gameMap.displayMap();
                            break;
                        case 4:
                            System.out.println("Exiting...");
                            gameStatus = false;
                            System.out.println("Kembali ke Main Menu...");
                            gameplayThread.interrupt();
                            break;
                        case 5:
                            deck.displayDeck();
                            break;
                        case 6:
                            System.out.println("Current time: " + gameplay.getCurrentTime());
                            break;
                        case 7: 
                            System.out.println("Current Sun: " + Sun.getSun());
                            break;
                        default:
                            System.out.println("Invalid option!");
                            break;
                    }
                }
                if (Gameplay.getIsEnd()) {
                    System.out.println("Game Ended!");
                    gameStatus = false;
                    gameplayThread.interrupt();
                    gameplay.resetAttributes();
                }
                if (Gameplay.getWinningState()) {
                    System.out.println("You Win!");
                    gameStatus = false;
                    gameplayThread.interrupt();
                    gameplay.resetAttributes();
                    System.out.println("Game Restarted!");
                } else {
                    System.out.println("You Lose!");
                    gameStatus = false;
                    gameplayThread.interrupt();
                    gameplay.resetAttributes();
                }
            }
        }
        scanner.close();
    }
}
