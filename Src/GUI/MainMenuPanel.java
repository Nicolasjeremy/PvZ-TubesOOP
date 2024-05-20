package Src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private JButton startButton;
    private JButton entityListButton;
    private JButton helpButton;
    private JButton exitButton;

    public MainMenuPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridLayout(4, 1));

        // Tombol "Start Game"
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "gameMap");
            }
        });
        add(startButton);

        // Tombol "Entity List"
        entityListButton = new JButton("Entity List");
        entityListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tambahkan logika untuk menampilkan daftar entity di sini
            }
        });
        add(entityListButton);

        // Tombol "Help"
        helpButton = new JButton("Help");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tambahkan logika untuk menampilkan menu bantuan di sini
            }
        });
        add(helpButton);

        // Tombol "Exit"
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tambahkan logika untuk keluar dari permainan di sini
                System.exit(0);
            }
        });
        add(exitButton);
    }
}

