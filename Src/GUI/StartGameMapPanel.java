    package Src.GUI;

    import javax.swing.*;
    import java.awt.*;
    import Src.GameMaps.*;
    import Src.Entities.Plant.Shooter.*;
    import Src.MainMenu.Deck;
import Src.Entities.Entities;
import Src.Entities.Plant.*;
    import Src.Entities.Plant.Projectile.Projectile;

    public class StartGameMapPanel extends JPanel {
        private JLabel[][] tileLabels;
        private Deck deck;
        private Plant selectedPlant = null;

        public StartGameMapPanel(GameMap gameMap) {
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
            int xStart = 200;
            int yStart = 225;
            int tileWidth = 65;
            int tileHeight = 65;
            int xGap = 83;
            int yGap = 93;
            int x, y;

            for (int row = 0; row < 2; row++) {
                for (int col = 0; col < 11; col++) {
                    x = xStart + (col * xGap);
                    y = yStart + (row * yGap);

                    JLabel tileLabel = new JLabel();
                    tileLabel.setBounds(x, y, tileWidth, tileHeight);  // Setting position and size
                    tileLabel.setOpaque(true);
                    tileLabel.setBackground(getTileColor(gameMap.getTile(row, col)));

                    mapPanel.add(tileLabel, Integer.valueOf(1));
                    tileLabels[row][col] = tileLabel;

                    final int tileRow = row;
                    final int tileCol = col;
                    tileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            if (selectedPlant != null) {
                                int[] position = {tileRow, tileCol};
                                deck.planting(selectedPlant, position);
                                placePlant(tileRow, tileCol);
                                mapPanel.remove(tileLabels[tileRow][tileCol]); // Menghapus gambar tile yang lama
                                mapPanel.revalidate(); // Memperbarui tampilan panel

                                // Menambahkan gambar tanaman peashooter
                                JLabel peashooter = new JLabel() {
                                    @Override
                                    protected void paintComponent(Graphics g) {
                                        super.paintComponent(g);
                                        ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/PlantImage/peashooterfigma.png"));
                                        Image image = imageIcon.getImage();
                                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                                    }
                                };
                                peashooter.setBounds(tileLabels[tileRow][tileCol].getBounds()); // Menyesuaikan posisi label baru dengan tile yang lama
                                mapPanel.add(peashooter, Integer.valueOf(4)); // Menambahkan label baru ke panel
                                mapPanel.repaint();
                            }
                        }
                    });
                    
                    tileLabels[row][col] = tileLabel;
                    // Check if the tile is at (0, 3) and plant Peashooter there
                    // if (row == 0 && col == 3) {
                    //     tileLabel.setOpaque(false);
                    //     int[] position = {0,3};
                    //     Peashooter peashooter = new Peashooter(position, gameMap, "../Image/PlantImage/image1.jpg");
                    //     JLabel peashooterlabel = new JLabel(new ImageIcon(getClass().getResource("../Image/PlantImage/image1.jpg")));
                    //     peashooterlabel.setBounds(x, y, tileWidth, tileHeight);
                    //     gameMap.getTile(row, col).addEntity(peashooter);
                    //     gameMap.getTile(row, col).setPlanted(true);
                    //     mapPanel.add(peashooterlabel);
                    // }
                }
            }

            for (int row = 2; row < 4; row++) {
                for (int col = 0; col < 11; col++) {
                    x = xStart + (col * xGap);
                    y = yStart + (row * yGap);

                    if (row == 2) {
                        y += 13;  // Apply the offset for rows 2 and 3 initially
                    } else if (row == 3) {
                        y -= 5;  // Apply a different offset for row 3 if needed
                    }

                    JLabel tileLabel = new JLabel();
                    tileLabel.setBounds(x, y, tileWidth, tileHeight);  // Setting position and size
                    tileLabel.setOpaque(true);
                    tileLabel.setBackground(getTileColor(gameMap.getTile(row, col)));

                    mapPanel.add(tileLabel);
                    tileLabels[row][col] = tileLabel;

                    final int tileRow = row;
                    final int tileCol = col;
                    tileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            if (selectedPlant != null) {
                                int[] position = {tileRow, tileCol};
                                deck.planting(selectedPlant, position);
                                placePlant(tileRow, tileCol);
                                tileLabels[tileRow][tileCol].setIcon(new ImageIcon(selectedPlant.getIcon().getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
                                tileLabels[tileRow][tileCol].repaint();
                            }
                        }
                    });
                    tileLabels[row][col] = tileLabel;
                }
            }

            for (int row = 4; row < 6; row++) {
                for (int col = 0; col < 11; col++) {
                    x = xStart + (col * xGap);
                    y = yStart + (row * yGap);

                    if (row == 4) {
                        y -= 10;  // Apply the offset for rows 2 and 3 initially
                    } else if (row == 5) {
                        y -= 25;  // Apply a different offset for row 3 if needed
                    }

                    JLabel tileLabel = new JLabel();
                    tileLabel.setBounds(x, y, tileWidth, tileHeight);  // Setting position and size
                    tileLabel.setOpaque(true);
                    tileLabel.setBackground(getTileColor(gameMap.getTile(row, col)));

                    mapPanel.add(tileLabel);
                    tileLabels[row][col] = tileLabel;

                    final int tileRow = row;
                    final int tileCol = col;
                    tileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            if (selectedPlant != null) {
                                int[] position = {tileRow, tileCol};
                                deck.planting(selectedPlant, position);
                                placePlant(tileRow, tileCol);
                                tileLabels[tileRow][tileCol].setIcon(new ImageIcon(selectedPlant.getIcon().getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
                                tileLabels[tileRow][tileCol].repaint();
                            }
                        }
                    });
                    tileLabels[row][col] = tileLabel;
                }
            }

            mapPanel.add(addDeckImage(), Integer.valueOf(1));
            mapPanel.add(addPlantCard(), Integer.valueOf(2));

            add(mapPanel);
    }

    private Color getTileColor(Tile tile) {
        if (tile instanceof Home) {
            return Color.PINK;
        } else if (tile instanceof Grass) {
            return Color.GREEN;
        } else if (tile instanceof ZombieSpawn) {
            return Color.ORANGE;
        } else {
            return Color.CYAN;
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
            int[] position = {0, 0}; // Dummy position
            selectedPlant = new Peashooter(position, deck.getGameMap());
        });
        plantCard.add(plantButton);

        return plantCard;
    }

    private void placePlant(int row, int col) {
        if (selectedPlant != null && !deck.getGameMap().getTile(row, col).hasPlanted()) {
            JLabel plantLabel = new JLabel(new ImageIcon(selectedPlant.getIcon().getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH)));
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
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/GameMapImage/greenProjectile.png"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 275, 220, getWidth(), getHeight(), this);
            }
        };
        this.add(greenProjectile, Integer.valueOf(3));
        this.repaint();
    }

    public void addEntityToTile(int row, int col, String imagePath, Entities entity) {
        JLabel entityLabel = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
        entityLabel.setBounds(0, 0, tileLabels[row][col].getWidth(), tileLabels[row][col].getHeight());
        // tilePanes[row][col].add(entityLabel, JLayeredPane.PALETTE_LAYER);
    }
    
}
