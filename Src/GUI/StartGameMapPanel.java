package Src.GUI;

import javax.swing.*;
import java.awt.*;
import Src.GameMaps.*;
import Src.Entities.Plant.Shooter.*;
import Src.MainMenu.Deck;
import Src.Entities.Entities;
import Src.Entities.Plant.*;
import Src.Entities.Plant.Passive.*;
import java.util.ArrayList;

public class StartGameMapPanel extends JPanel implements TileObserver {
    private JLabel[][] tileLabels;
    private JLayeredPane[][] tilePanes;
    private GameMap gameMap;
    private ZombieManager zombieManager;
    private Deck deck;
    private Plant selectedPlant = null;

    // Constructor for StartGameMapPanel
    public StartGameMapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        this.deck = new Deck(gameMap);
        setLayout(null);

        JLayeredPane mapPanel = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/GameMapImage/pvz_image.png"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, 1100, 770, this);
            }
        };

        mapPanel.setLayout(null);
        mapPanel.setBounds(0, 0, 1100, 770);
        mapPanel.setOpaque(true);

        tileLabels = new JLabel[6][11];
        tilePanes = new JLayeredPane[6][11];
        int xStart = 200;
        int yStart = 225;
        int tileWidth = 65;
        int tileHeight = 65;
        int xGap = 83;
        int yGap = 93;
        int x, y;

        // Initialize the grid of tiles
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 11; col++) {
                x = xStart + (col * xGap);
                y = yStart + (row * yGap);

                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setBounds(x, y, tileWidth, tileHeight);

                JLabel tileLabel = new JLabel();
                tileLabel.setBounds(0, 0, tileWidth, tileHeight); // Setting position and size
                tileLabel.setOpaque(true);
                tileLabel.setBackground(getTileColor(gameMap.getTile(row, col)));

                layeredPane.add(tileLabel, Integer.valueOf(2));
                mapPanel.add(layeredPane);

                tileLabels[row][col] = tileLabel;
                tilePanes[row][col] = layeredPane;

                // Register this panel as an observer for the tile
                gameMap.getTile(row, col).addObserver(this);

                final int tileRow = row;
                final int tileCol = col;

                tileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (selectedPlant != null) {
                            int[] position = { tileRow, tileCol };
                            selectedPlant.setPosition(position);
                            addEntityToTile(selectedPlant.getPosition()[0], selectedPlant.getPosition()[1],
                                    selectedPlant.getimagepath(), selectedPlant);
                            startPlant(selectedPlant);

                            selectedPlant = null;
                        }
                    }
                });
            }
        }

        mapPanel.add(addDeckImage(), Integer.valueOf(1));
        addPlantCards(mapPanel);

        add(mapPanel);

        // Start the zombie manager
        startZombieManager();
    }

    // Method to get the color of a tile based on its type
    private Color getTileColor(Tile tile) {
        if (tile instanceof Home) {
            return Color.PINK;
        } else if (tile instanceof Grass) {
            return Color.GREEN;
        } else if (tile instanceof ZombieSpawn) {
            return Color.ORANGE;
        } else {
            return Color.CYAN; // For Pool
        }
    }

    // Method to update the color of a tile
    public void updateTile(int row, int col, Color color) {
        tileLabels[row][col].setBackground(color);
    }

    // Method to add the deck image to the map panel
    public JLabel addDeckImage() {
        JLabel deckLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/GameMapImage/deck_image.png"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        deckLabel.setBounds(20, 10, 600, 120);
        return deckLabel;
    }

    // Method to add plant cards to the map panel
    public void addPlantCards(JLayeredPane mapPanel) {
        ArrayList<Plant> plants = new ArrayList<>();
        plants.add(new Peashooter(new int[] { 0, 0 }, deck.getGameMap()));
        plants.add(new Repeater(new int[] { 0, 0 }, deck.getGameMap()));
        plants.add(new Snowpea(new int[] { 0, 0 }, deck.getGameMap()));
        plants.add(new Sunflower(new int[] { 0, 0 }, deck.getGameMap()));
        plants.add(new Wallnut(new int[] { 0, 0 }, deck.getGameMap()));
        plants.add(new Tallnut(new int[] { 0, 0 }, deck.getGameMap()));

        int xStart = 130;
        int yStart = 22;
        int cardWidth = 70;
        int cardHeight = 93;
        int xGap = 80;

        for (int i = 0; i < plants.size(); i++) {
            Plant plant = plants.get(i);
            JLabel plantCard = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource(plant.getimagepath()));
                    Image image = imageIcon.getImage();
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            };
            plantCard.setBounds(xStart + (i * xGap), yStart, cardWidth, cardHeight);

            JButton plantButton = new JButton();
            plantButton.setBounds(0, 0, cardWidth, cardHeight);
            plantButton.setContentAreaFilled(false);
            plantButton.setBorderPainted(false);
            plantButton.addActionListener(e -> {
                selectedPlant = plant;
            });
            plantCard.add(plantButton);

            mapPanel.add(plantCard, Integer.valueOf(2));
        }
    }

    // Method to add an entity to a tile
    public void addEntityToTile(int row, int col, String imagePath, Entities entity) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        originalIcon.setDescription(imagePath); // Set description to image path
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(tileLabels[row][col].getWidth(),
                tileLabels[row][col].getHeight(), Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        resizedIcon.setDescription(imagePath); // Set description to image path

        JLabel entityLabel = new JLabel(resizedIcon);
        entityLabel.setBounds(0, 0, tileLabels[row][col].getWidth(), tileLabels[row][col].getHeight());
        tilePanes[row][col].add(entityLabel, JLayeredPane.PALETTE_LAYER);
        tilePanes[row][col].revalidate();
        tilePanes[row][col].repaint();
    }

    // Method to remove an entity from a tile
    public void removeEntityFromTile(int row, int col, String imagePath, Entities entity) {
        for (Component component : tilePanes[row][col].getComponentsInLayer(JLayeredPane.PALETTE_LAYER)) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getIcon() != null && imagePath.equals(label.getIcon().toString())) {
                    tilePanes[row][col].remove(label);
                    tilePanes[row][col].revalidate();
                    tilePanes[row][col].repaint();
                    break;
                }
            }
        }
    }

    // Callback method when an entity is added to a tile
    @Override
    public void entityAdded(int row, int col, Entities entity) {
        addEntityToTile(row, col, entity.getimagepath(), entity);
    }

    // Callback method when an entity is removed from a tile
    @Override
    public void entityRemoved(int row, int col, Entities entity) {
        removeEntityFromTile(row, col, entity.getimagepath(), entity);
    }

    // Method to start the ZombieManager thread
    public void startZombieManager() {
        zombieManager = new ZombieManager(this.gameMap);
        Thread zombieManagerThread = new Thread(zombieManager);
        zombieManagerThread.start();
    }

    public void startPlant(Plant selectedPlant) {
        Thread threadPlan = new Thread(selectedPlant);
        threadPlan.start();
    }
}
