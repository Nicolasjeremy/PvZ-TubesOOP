import java.util.ArrayList;
import java.util.InputMismatchException;
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

                boolean truinput = false;
                while (truinput == false) {
                    System.out.print("Want Autofill? (Yes/No): ");
                    String inputuser = scanner.next();
                    if (inputuser.equalsIgnoreCase("Yes")) {
                        truinput = true;
                        ArrayList<Plant> inventoryCopy = new ArrayList<>(inventory.getInventory());
                        for (Plant plant : inventoryCopy) {
                            inventory.addDeck(inventory.getInventory().indexOf(plant));
                        }
                    } else if (inputuser.equalsIgnoreCase("No")) {
                        truinput = true;
                        DriverInventory.InventoryDeck(deck, inventory, scanner);
                    } else {
                        System.out.println("Perintah tidak dikenali");
                    }
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
                Gameplay.setIsEnd(false);
                gameplay.setGameMap(gameMap);
                gameplay.setDeck(deck);
                gameplay.setInventory(inventory);
                System.out.println("Data Game Map, Deck, dan Inventory Tersimpan");
                Thread gameplayThread = new Thread(gameplay);
                gameplayThread.start();

                // GamePlay
                while (gameStatus && !Gameplay.getIsEnd()) {
                    System.out.println("\n");
                    System.out.println("|||STATUS GAME|||");
                    System.out.println("Current Time: " + Gameplay.getCurrentTime());
                    System.out.println("Current Sun: " + Sun.getSun());
                    deck.displayDeck();
                    System.out.println("\n|||COMMAND GAME|||");
                    System.out.println("1. Plant");
                    System.out.println("2. Unplant");
                    System.out.println("3. Display Map");
                    System.out.println("4. Quit");
                    System.out.println("5. ShowTime");
                    System.out.print("Choose an option: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        System.out.println("\n|||COMMAND GAME|||");
                        System.out.println("1. Plant");
                        System.out.println("2. Unplant");
                        System.out.println("3. Display Map");
                        System.out.println("4. Quit");
                        System.out.println("5. ShowTime");
                        System.out.print("Choose an option: ");
                        scanner.next(); // Clear invalid input
                    }

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            deck.displayDeck();
                            System.out.print("Enter plant numerical order: ");
                            try {
                                int plantIndexToPlant = scanner.nextInt() - 1;
                                Plant plantToPlant = deck.getPlantFromDeck(plantIndexToPlant);
                                if (plantToPlant != null) {
                                    System.out.println("Column enter range(1-9)");
                                    System.out.println("Row enter range(0-5)");
                                    System.out.print("Enter the column and row to plant (e.g., 2 3): ");
                                    int col = scanner.nextInt();
                                    int row = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    int[] position = { row, col };
                                    deck.planting(plantToPlant, position);
                                } else {
                                    System.out.println("Plant not found in deck!");
                                }
                                gameMap.displayMap();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a number.");
                                scanner.nextLine(); // Consume the invalid input
                            }
                            break;
                        case 2:
                            try {
                                System.out.println("Column enter range(1-9)");
                                System.out.println("Row enter range(0-5)");
                                System.out.print("Enter the column and row to unplant (e.g., 2 3): ");
                                int colToUnplant = scanner.nextInt();
                                int rowToUnplant = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                int[] positionToUnplant = { rowToUnplant, colToUnplant };
                                deck.unPlanting(positionToUnplant);

                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a number.");
                                scanner.nextLine(); // Consume the invalid input
                            }
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
                            System.out.println("Current time: " + Gameplay.getCurrentTime());
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
