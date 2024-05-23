package Src.GUI;

import Src.MainMenu.*;
import Src.GameMaps.*;
import Src.Entities.Plant.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryDeckPanel extends JFrame {
    private Deck deck;
    private Inventory inventory;
    private JLayeredPane backgroundMap;
    private JPanel deckPanel;
    private int heightCard = 85;
    private int widthCard = 65;

    public InventoryDeckPanel(GameMap gameMap) {
        deck = new Deck(gameMap);
        inventory = new Inventory(deck);

        setTitle("Deck Selection");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Main Panel
        backgroundMap = new JLayeredPane() {
            @Override 
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/InventoryDeckImage/backGround.png"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, 1100, 770, this);
            }
        };

        backgroundMap.setLayout(null);
        backgroundMap.setBounds(0, 0, getWidth(), getHeight());
        backgroundMap.setOpaque(false);
        add(backgroundMap, JLayeredPane.DEFAULT_LAYER);

        // Creating Deck Panel
        deckPanel = new JPanel(new GridBagLayout());
        deckPanel.setBounds(115, 5, 565, 95);
        backgroundMap.add(deckPanel, Integer.valueOf(2));

        deckPanel.setOpaque(false);

        showInventoryCard(backgroundMap);
        setVisible(true);
    }

    public void showInventoryCard(JLayeredPane background) {
        ArrayList<Plant> listPlants = inventory.getInventory();
        int gapx = 7;
        int gapy = 10;

        int x = 33;
        int y = 165;
        int counter = 0;

        for (Plant plant : listPlants) {
            final int cardX = x; // Final variables to be used inside ActionListener
            final int cardY = y;

            JLayeredPane plantCard = new JLayeredPane() {
                @Override
                protected void paintComponent(Graphics g) {
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/InventoryDeckImage/" + plant.getName() + ".png"));
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            };
            background.add(plantCard, Integer.valueOf(2));
            plantCard.setBounds(x, y, widthCard, heightCard);

            // Button to add card to deck
            JButton button = new JButton();
            button.setBounds(0, 0, widthCard, heightCard);
            button.setContentAreaFilled(false);
            button.setBorderPainted(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deck.getSizeDeck() < 6) {
                        deck.addPlantToDeck(plant);
                        JLayeredPane grayCard = new JLayeredPane() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/InventoryDeckImage/" + plant.getName() + "Choosed.png"));
                                Image image = imageIcon.getImage();
                                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                            }
                        };
                        background.add(grayCard, Integer.valueOf(3));
                        grayCard.setBounds(cardX, cardY, widthCard, heightCard);

                        createDeckCard(plant, plantCard, grayCard, cardX, cardY);
                    }
                }
            });
            plantCard.add(button);

            x += gapx + widthCard;
            counter++;

            if (counter % 8 == 0) {
                y += gapy + heightCard;
                x = 33;
            }
        }
    }

    public void createDeckCard(Plant plant, JLayeredPane plantCard, JLayeredPane grayCard, int cardX, int cardY) {
        ImageIcon originalImageIcon = new ImageIcon(getClass().getResource("../Image/InventoryDeckImage/" + plant.getName() + ".png"));
        Image originalImage = originalImageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(widthCard, heightCard, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel cloneCard = new JLabel(scaledImageIcon);
        cloneCard.setPreferredSize(new Dimension(widthCard, heightCard));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        gbc.gridx = deck.getSizeDeck(); // Position in row
        gbc.gridy = 0; // Always in the first row
        gbc.insets = new Insets(0, 20, 0, 0); // Adjust gaps here
        gbc.fill = GridBagConstraints.NONE; // Do not resize the component
        gbc.weightx = 1.0; // Ensure components are stretched horizontally if needed

        deckPanel.add(cloneCard, gbc);
        deckPanel.revalidate();
        deckPanel.repaint();

        JButton deleteButton = new JButton();
        deleteButton.setBounds(0, 0, widthCard, heightCard);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorderPainted(true);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deck.removePlantFromDeck(deck.indexOf(plant));
                deckPanel.remove(cloneCard);
                backgroundMap.remove(grayCard);
                deckPanel.revalidate();
                deckPanel.repaint();
                backgroundMap.revalidate();
                backgroundMap.repaint();
            }
        });
        cloneCard.add(deleteButton);

        deckPanel.revalidate();
        deckPanel.repaint();
    }

    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        new InventoryDeckPanel(gameMap);
    }
}
