package Src.MainMenu;

public class Command {
    String[] headersZombie = {"Zombie Name", "Attribute", "Value"};
    String[][][] dataZombie = {
            {
                {"Normal", "Health", "125"},
                {"", "Attack Damage", "100"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","false"}
            },
            {
                {"Conehead Zombie", "Health", "250"},
                {"", "Attack Damage", "100"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","false"},
            },
            {
                {"Pole Vaulting Zombie", "Health", "175"},
                {"", "Attack Damage", "100"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","false"},
            },
            {
                {"Buckethead Zombie", "Health", "300"},
                {"", "Attack Damage", "100"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","false"},
            },
            {
                {"Ducky Tube Zombie", "Health", "100"},
                {"", "Attack Damage", "100"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","True"},
            },
            {
                {"Doplhin Rider Zombie", "Health", "175"},
                {"", "Attack Damage", "100"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","True"},
            },
            {
                {"Raul Zombie", "Health", "250"},
                {"", "Attack Damage", "100"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","False"},
            },
            {
                {"Lala Zombie", "Health", "100"},
                {"", "Attack Damage", "200"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","False"},
            },
            {
                {"Dispy Zombie", "Health", "200"},
                {"", "Attack Damage", "125"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","False"},
            },
            {
                {"Asep Zombie", "Health", "125"},
                {"", "Attack Damage", "100"},
                {"", "Attack Speed", "1"},
                {"", "Aquatic","False"},
            },
        };
        String[] headerPlant = {"Plant Name", "Attribute", "Value"};
        String[][][] dataPlant =  {
            {
                {"Sunflower", "Cost", "50"},
                {"", "Health", "100"},
                {"", "Attack Damage", "0"},
                {"", "Attack Speed", "0"},
                {"", "Range","0"},
                {"", "Cooldown","10"}
            },
            {
                {"Peashooter", "Cost", "100"},
                {"", "Health", "100"},
                {"", "Attack Damage", "25"},
                {"", "Attack Speed", "4"},
                {"", "Range","-1"},
                {"", "Cooldown","10"}
            },
            {
                {"Wall-nut", "Cost", "50"},
                {"", "Health", "1000"},
                {"", "Attack Damage", "0"},
                {"", "Attack Speed", "0"},
                {"", "Range","0"},
                {"", "Cooldown","20"}
            },
            {
                {"Snow Pea", "Cost", "175"},
                {"", "Health", "100"},
                {"", "Attack Damage", "25"},
                {"", "Attack Speed", "4"},
                {"", "Range","-1"},
                {"", "Cooldown","10"}
            },
            {
                {"Snow Pea", "Cost", "175"},
                {"", "Health", "100"},
                {"", "Attack Damage", "25"},
                {"", "Attack Speed", "4"},
                {"", "Range","-1"},
                {"", "Cooldown","10"}
            },
            {
                {"Squash", "Cost", "50"},
                {"", "Health", "100"},
                {"", "Attack Damage", "5000"},
                {"", "Attack Speed", "0"},
                {"", "Range","1"},
                {"", "Cooldown","20"}
            },
            {
                {"Lilypad", "Cost", "25"},
                {"", "Health", "100"},
                {"", "Attack Damage", "0"},
                {"", "Attack Speed", "0"},
                {"", "Range","0"},
                {"", "Cooldown","10"}
            },
            {
                {"Jalapeno", "Cost", "50"},
                {"", "Health", "100"},
                {"", "Attack Damage", "5000"},
                {"", "Attack Speed", "0"},
                {"", "Range","0"},
                {"", "Cooldown","20"}
            },
            {
                {"TangleKelp", "Cost", "50"},
                {"", "Health", "100"},
                {"", "Attack Damage", "5000"},
                {"", "Attack Speed", "0"},
                {"", "Range","1"},
                {"", "Cooldown","20"}
            },
            {
                {"Tallnut", "Cost", "50"},
                {"", "Health", "20000"},
                {"", "Attack Damage", "0"},
                {"", "Attack Speed", "0"},
                {"", "Range","1"},
                {"", "Cooldown","30"}
            },
            {
                {"Repeater", "Cost", "150"},
                {"", "Health", "100"},
                {"", "Attack Damage", "25"},
                {"", "Attack Speed", "4"},
                {"", "Range","-1"},
                {"", "Cooldown","10"}
            },
        };
        int idWidth = 20;
        int nameWidth = 15;
        int ageWidth = 10;
        int totalWidth = idWidth + nameWidth + ageWidth + 6;
        // Method to print a single row
    public static void printRow(String[] columns, int idWidth, int nameWidth, int ageWidth) {
            System.out.print("|");
            System.out.printf("%-" + idWidth + "s|", columns[0]);
            System.out.printf("%-" + nameWidth + "s|", columns[1]);
            System.out.printf("%-" + ageWidth + "s|%n", columns[2]);
        }
    
        // Method to print horizontal borders
    public static void printHorizontalBorder(int totalWidth) {
            System.out.println("+" + "-".repeat(totalWidth - 2) + "+");
        }

    public void start() {
        System.out.println("Game Started");
    }
    public void help() {
        System.out.println("List of Commands:");
        System.out.println("1. Start: Start the game");
        System.out.println("2. Help: Show the list of commands");
        System.out.println("3. Exit: Exit the game");
        System.out.println("4. Plant List: List of available plants");
        System.out.println("5. Zombie List: List of available zombies");
    }
    public void exit() {
        System.out.println("Game Exited");
    }
    public void zombielist() {
        printHorizontalBorder(totalWidth);

        // Print headers with vertical borders
        printRow(headersZombie, idWidth, nameWidth, ageWidth);
        printHorizontalBorder(totalWidth);

        // Print rows of data with borders
        for (String[][] row : dataZombie) {
            for (String[] element : row) {
                printRow(element, idWidth, nameWidth, ageWidth);
            }
            printHorizontalBorder(totalWidth);
        }
    }
    public void plantlist() {
        printHorizontalBorder(totalWidth);

        // Print headers with vertical borders
        printRow(headerPlant, idWidth, nameWidth, ageWidth);
        printHorizontalBorder(totalWidth);

        // Print rows of data with borders
        for (String[][] row : dataPlant) {
            for (String[] element : row) {
                printRow(element, idWidth, nameWidth, ageWidth);
            }
            printHorizontalBorder(totalWidth);
        }
    }
}
