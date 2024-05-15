import java.util.Scanner;
import Src.MainMenu.*;
import Src.Entities.Plant.*;
import Src.Entities.Plant.Melee.*;
import Src.Entities.Plant.Passive.Lilypad;
import Src.Entities.Plant.Passive.Sunflower;
import Src.Entities.Plant.Passive.Tallnut;
import Src.Entities.Plant.Passive.Wallnut;
import Src.Entities.Plant.Shooter.Peashooter;
import Src.Entities.Plant.Shooter.Repeater;
import Src.Entities.Plant.Shooter.Snowpea;


public class DriverInventory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        Inventory inventory = new Inventory(deck);

        while (true) {
            System.out.println("\n1. Add Plant to Deck");
            System.out.println("2. Remove Plant from Deck");
            System.out.println("3. Swap Plants in Deck");
            System.out.println("4. Display Deck");
            System.out.println("5. Display Inventory");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline PeaShooter

            // PeaShooter String --> Plant
            // Cek Inventory ada gak? -> deck

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the plant to add to the deck: ");
                    String plantNameToAdd = scanner.nextLine();
                    Plant plantToAdd = createPlant(plantNameToAdd);
                    if (plantToAdd != null) {
                        inventory.addDeck(plantToAdd);
                    }
                    break;
                case 2:
                    System.out.print("Enter the name of the plant to remove from the deck: ");
                    String plantNameToRemove = scanner.nextLine();
                    Plant plantToRemove = createPlant(plantNameToRemove);
                    if (plantToRemove != null) {
                        inventory.removeDeck(plantToRemove);
                    }
                    break;
                case 3:
                    System.out.print("Enter the name of plant in deck to swap: ");
                    String plantdeck = scanner.nextLine();
                    Plant plant1 = createPlant(plantdeck);
                    System.out.print("Enter the name of the plant in invetory to swap: ");
                    String plantinventory = scanner.nextLine();
                    Plant plant2 = createPlant(plantinventory);
                    inventory.swapPlant(plant1, plant2);
                    break;
                case 4:
                    deck.displayDeck();
                    break;
                case 5:
                    inventory.displayInventory();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    // Helper method to create plant object based on the name
    private static Plant createPlant(String plantName) {
    switch (plantName.toLowerCase()) {
        case "chomper":
            return new Chomper(null, null);
        case "squash":
            return new Squash(null, null, null);
        case "tanglekelp":
            return new TangleKelp(null, null);
        case "lilypad":
            return new Lilypad(null, null);
        case "sunflower":
            return new Sunflower(null, 2, 3, null);
        case "tallnut":
            return new Tallnut(null, null);
        case "wallnut":
            return new Wallnut(null, null);
        case "peashooter":
            return new Peashooter(null, null);
        case "repeater":
            return new Repeater(null, null);
        case "snowpea":
            return new Snowpea(null, null);
        default:
            System.out.println("Invalid plant name!");
            return null;
    }
}

}
