package Src.MainMenu;

import java.util.Scanner;


//! javac -d bin Src/Entities/*.java Src/Entities/Plant/Plant.java Src/Entities/Plant/Melee/*.java Src/Entities/Plant/Passive/*.java Src/Entities/Plant/Projectile/*.java Src/Entities/Plant/Shooter/*.java Src/Entities/Zombie/*.java Src/Exception/*.java Src/GameMaps/*.java Src/MainMenu/*.java
//! java -cp bin Src.MainMenu.Main
public class Main {
    public static void main(String[] args) {
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
                    command.start();
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
            
        }
        scanner.close();
    }
}
