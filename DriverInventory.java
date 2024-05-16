import java.util.Scanner;
import Src.MainMenu.*;
// import Src.Entities.Plant.*;
// import Src.Entities.Plant.Melee.*;
// import Src.Entities.Plant.Passive.*;

public class DriverInventory {
    public static void InventoryDeck(Deck deck, Inventory inventory, Scanner scanner) {
        boolean full = false;

        while (!full) {
            if (deck.getDeck().size() >= 6) {
                System.out.println("Deck is full! You can either swap plants or quit.");
                System.out.println("\n1. Swap Plants");
                System.out.println("2. Quit");
                System.out.print("Choose an option: ");
                int fullChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (fullChoice) {
                    case 1:
                    System.out.println("Where Do You Want To Do The Swap?");
                    System.out.println("1. Deck");
                    System.out.println("2. Inventory");
                    System.out.print("Choose an option: ");
                    int swapOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (swapOption == 1) {
                        deck.displayDeck();
                        System.out.print("Enter the first index to swap in deck: ");
                        int index1Deck = scanner.nextInt() - 1;
                        System.out.print("Enter the second index to swap in deck: ");
                        int index2Deck = scanner.nextInt() - 1;
                        scanner.nextLine();
                        deck.swapPlantsInDeck(index1Deck, index2Deck);
                    }
                    else if (swapOption == 2) {
                        inventory.displayInventory();
                        System.out.print("Enter the first index to swap in inventory: ");
                        int index1Inventory = scanner.nextInt() - 1;
                        System.out.print("Enter the second index to swap in inventory: ");
                        int index2Inventory = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume newline
                        inventory.swapPlantsInInventory(index1Inventory, index2Inventory);
                    }
                    else {
                        System.out.println("Invalid Option!");
                    }
                    break;
                    case 2:
                        System.out.println("Exiting...");
                        full = true;
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } else {
                System.out.println("\n1. Add Plant to Deck");
                System.out.println("2. Remove Plant from Deck");
                System.out.println("3. Swap Plants");
                System.out.println("4. Display Deck");
                System.out.println("5. Display Inventory");
                System.out.println("6. Quit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        inventory.displayInventory();
                        System.out.print("Enter the index of the plant to add to the deck: ");
                        int indexToAdd = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume newline
                        inventory.addDeck(indexToAdd);
                        break;
                    case 2:
                        deck.displayDeck();
                        System.out.print("Enter the index of the plant to remove from the deck: ");
                        int indexToRemove = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume newline
                        inventory.removeDeck(indexToRemove);
                        break;
                    case 3:
                        System.out.println("Where Do You Want To Do The Swap?");
                        System.out.println("1. Deck");
                        System.out.println("2. Inventory");
                        System.out.print("Choose an option: ");
                        int swapOption = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (swapOption == 1) {
                            deck.displayDeck();
                            System.out.print("Enter the first index to swap in deck: ");
                            int index1Deck = scanner.nextInt() - 1;
                            System.out.print("Enter the second index to swap in deck: ");
                            int index2Deck = scanner.nextInt() - 1;
                            scanner.nextLine();
                            deck.swapPlantsInDeck(index1Deck, index2Deck);
                        }
                        else if (swapOption == 2) {
                            inventory.displayInventory();
                            System.out.print("Enter the first index to swap in inventory: ");
                            int index1Inventory = scanner.nextInt() - 1;
                            System.out.print("Enter the second index to swap in inventory: ");
                            int index2Inventory = scanner.nextInt() - 1;
                            scanner.nextLine(); // Consume newline
                            inventory.swapPlantsInInventory(index1Inventory, index2Inventory);
                        }
                        else {
                            System.out.println("Invalid Option!");
                        }
                        break;
                    case 4:
                        deck.displayDeck();
                        break;
                    case 5:
                        inventory.displayInventory();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        full = true;
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            }
        }
    }
}
