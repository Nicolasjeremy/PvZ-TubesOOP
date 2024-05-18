package Src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Src.MainMenu.Deck;
import Src.MainMenu.Inventory;
import Src.GameMaps.GameMap;

public class GameFrame extends JFrame {
    private Inventory inventory;
    private Deck deck;
    
    public GameFrame(Inventory inventory, Deck deck) {
        this.inventory = inventory;
        this.deck = deck;
        
        setTitle("Michael vs. Lalapan");
        setSize(800, 600); // Set ukuran sesuai kebutuhan
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Buka di tengah screen
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());
        
        // Judul game
        JLabel titleLabel = new JLabel("Michael vs. Lalapan", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        
        // Panel untuk inventory dan deck
        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new FlowLayout());
        
        JButton showInventoryButton = new JButton("Show Inventory");
        showInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tampilkan inventory
                JOptionPane.showMessageDialog(null, inventory.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        JButton showDeckButton = new JButton("Show Deck");
        showDeckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tampilkan deck
                JOptionPane.showMessageDialog(null, deck.toString(), "Deck", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        inventoryPanel.add(showInventoryButton);
        inventoryPanel.add(showDeckButton);
        add(inventoryPanel, BorderLayout.CENTER);
        
        // Tombol keluar
        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        add(exitButton, BorderLayout.SOUTH);

    }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Inventory inventory = new Inventory(new Deck(new GameMap()));
                    Deck deck = new Deck(new GameMap());
                    JFrame frame = new GameFrame(inventory, deck);
                    frame.setVisible(true);
                }
            });
        }    
}

