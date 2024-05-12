package Src.MainMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Selamat datang di game pvz");
        // Thread.sleep(1000);
        Boolean game_status = true;
        Scanner scanner = new Scanner(System.in);
        while (game_status) {
            System.out.println("Masukan command anda:");
            String perintah = scanner.nextLine();
            // StringBuilder sb = new StringBuilder(perintah);
            // System.out.println(sb);
            Command command = new Command();

            if (perintah.equals("Start")) {
                command.start();
            } else if (perintah.equals("Help")) {
                command.help();
            } else if (perintah.equals("Zombie List")) {
                command.zombielist();
            } else if (perintah.equals("Plant List")) {
                command.plantlist();
            } else if (perintah.equals("Exit")) {
                command.exit();
                game_status = false;
                
            } else {
                System.out.println("Perintah tidak dikenali");
            }


            
        }
        scanner.close();
    }
}
