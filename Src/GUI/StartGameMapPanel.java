package Src.GUI;

import javax.swing.*;
import java.awt.*;
import Src.GameMaps.*;
import Src.Entities.*;
import Src.Entities.Plant.Shooter.*;
import Src.MainMenu.Deck;
import Src.Entities.Entities;
import Src.Entities.Plant.*;
import Src.Entities.Plant.Projectile.*;

public class StartGameMapPanel extends JPanel implements TileObserver {
    private Image mapImage;
    private JLabel[][] tileLabels;
    private JLayeredPane[][] tilePanes;
    private GameMap gameMap;
    private ZombieManager zombieManager;
    private Deck deck;
    private Plant selectedPlant = null;

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

                // Register as an observer
                gameMap.getTile(row, col).addObserver(this);

                final int tileRow = row;
                final int tileCol = col;
                tileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (selectedPlant != null) {
                            int[] position = { tileRow, tileCol };
                            deck.planting(selectedPlant, position);
                            placePlant(tileRow, tileCol, selectedPlant.getimagepath());

                            // Add plant image
                            selectedPlant = new Peashooter(null, gameMap);

                            JLabel plantLabel = new JLabel(
                                    new ImageIcon(getClass().getResource(selectedPlant.getimagepath())));
                            plantLabel.setBounds(0, 0, tileWidth, tileHeight); // Match position
                            tilePanes[tileRow][tileCol].add(plantLabel, Integer.valueOf(4)); // Add to tilePane
                            tilePanes[tileRow][tileCol].revalidate();
                            tilePanes[tileRow][tileCol].repaint();
                        }
                    }
                });
            }
        }

        mapPanel.add(addDeckImage(), Integer.valueOf(1));
        mapPanel.add(addPlantCard(), Integer.valueOf(2));

        add(mapPanel);

        // Start the zombie manager
        startZombieManager();
    }

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

    public void updateTile(int row, int col, Color color) {
        tileLabels[row][col].setBackground(color);
    }

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

    public JLabel addPlantCard() {
        JLabel plantCard = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/GameMapImage/peashooter.png"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        plantCard.setBounds(130, 22, 70, 93);

        JButton plantButton = new JButton();
        plantButton.setBounds(0, 0, 70, 93);
        plantButton.setContentAreaFilled(false);
        plantButton.setBorderPainted(false);
        plantButton.addActionListener(e -> {
            int[] position = { 0, 0 }; // Dummy position
            selectedPlant = new Peashooter(position, deck.getGameMap());
        });
        plantCard.add(plantButton);

        return plantCard;
    }

    private void placePlant(int row, int col, String imagePath) {
        if (selectedPlant != null && !deck.getGameMap().getTile(row, col).hasPlanted()) {
            JLabel plantLabel = new JLabel(
                    new ImageIcon(selectedPlant.getIcon().getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
            plantLabel.setBounds(tileLabels[row][col].getBounds());
            ((JLayeredPane) tileLabels[row][col].getParent()).add(plantLabel, Integer.valueOf(1));
            tileLabels[row][col].getParent().repaint();
            selectedPlant = null;
        }
    }

    public void addProjectile(Projectile projectile) {
        JLabel greenProjectile = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(
                        getClass().getResource("../Image/GameMapImage/greenProjectile.png"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 275, 220, getWidth(), getHeight(), this);
            }
        };
        this.add(greenProjectile, Integer.valueOf(3));
        this.repaint();
    }

    public void addEntityToTile(int row, int col, String imagePath, Entities entity) {
        System.out.println("Adding entity to tile (" + row + ", " + col + ")");
        JLabel entityLabel = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
        entityLabel.setBounds(0, 0, tileLabels[row][col].getWidth(), tileLabels[row][col].getHeight());
        tilePanes[row][col].add(entityLabel, JLayeredPane.PALETTE_LAYER);
    }

    public void removeEntityFromTile(int row, int col, String imagePath, Entities entity) {
        for (Component component : tilePanes[row][col].getComponentsInLayer(JLayeredPane.PALETTE_LAYER)) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getIcon() != null && ((ImageIcon) label.getIcon()).getDescription()
                        .equals(getClass().getResource(imagePath).toString())) {
                    tilePanes[row][col].remove(label);
                    tilePanes[row][col].revalidate();
                    tilePanes[row][col].repaint();
                    break;
                }
            }
        }
    }

    @Override
    public void entityAdded(int row, int col, Entities entity) {
        addEntityToTile(row, col, entity.getimagepath(), entity);
    }

    @Override
    public void entityRemoved(int row, int col, Entities entity) {
        removeEntityFromTile(row, col, entity.getimagepath(), entity);
    }

    public void startZombieManager() {
        System.out.println("Starting ZombieManager");
        zombieManager = new ZombieManager(this.gameMap);
        Thread zombieManagerThread = new Thread(zombieManager);
        zombieManagerThread.start();
    }
}
