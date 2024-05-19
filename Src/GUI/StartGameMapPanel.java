package Src.GUI;

import javax.swing.*;
import java.awt.*;
import Src.GameMaps.*;
import Src.Entities.Plant.Shooter.*;

public class StartGameMapPanel extends JPanel {
    private Image mapImage;
    private JLabel[][] tileLabels;
    private JLayeredPane[][] tilePanes;
    private GameMap gameMap;

    public StartGameMapPanel(GameMap gameMap) {
        this.gameMap = gameMap;
        setLayout(null); // Set layout to null for StartGameMapPanel itself

        // Panel untuk menampilkan peta PvZ
        JPanel mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Menggambar gambar peta PvZ
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/pvz_image.jpg"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        mapPanel.setLayout(null);
        mapPanel.setBounds(0, 0, 1000, 733);
        mapPanel.setOpaque(false);

        tileLabels = new JLabel[6][11];
        tilePanes = new JLayeredPane[6][11];
        int xStart = 185;
        int yStart = 220;
        int tileWidth = 70;
        int tileHeight = 70;
        int xGap = 75;
        int yGap = 85;

        // Setting Tile
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 11; col++) {
                int x = xStart + col * xGap;
                int y = yStart + row * yGap;

                JLayeredPane layeredPane = new JLayeredPane();
                layeredPane.setBounds(x, y, tileWidth, tileHeight);

                JLabel tileLabel = new JLabel();
                tileLabel.setBounds(0, 0, tileWidth, tileHeight); // Setting position and size
                tileLabel.setOpaque(true);
                tileLabel.setBackground(getTileColor(gameMap.getTile(row, col)));

                layeredPane.add(tileLabel, JLayeredPane.DEFAULT_LAYER);
                mapPanel.add(layeredPane);

                tileLabels[row][col] = tileLabel;
                tilePanes[row][col] = layeredPane;

                // Check if the tile is at (0, 3) and plant Peashooter there
                if (row == 0 && col == 3) {
                    tileLabel.setOpaque(false);
                    int[] position = {0, 3};
                    Peashooter peashooter = new Peashooter(position, gameMap);
                    JLabel peashooterLabel = new JLabel(
                            new ImageIcon(getClass().getResource(peashooter.getimagepath())));
                    peashooterLabel.setBounds(0, 0, tileWidth, tileHeight);
                    gameMap.getTile(row, col).addEntity(peashooter);
                    gameMap.getTile(row, col).setPlanted(true);
                    layeredPane.add(peashooterLabel, JLayeredPane.PALETTE_LAYER);
                }
            }
        }

        add(mapPanel);

        // Perbarui tampilan frame
        revalidate();
        repaint();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void updateTile(int row, int col, Color color) {
        tileLabels[row][col].setBackground(color);
    }

    public void addEntityToTile(int row, int col, String imagePath) {
        JLabel entityLabel = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
        entityLabel.setBounds(0, 0, tileLabels[row][col].getWidth(), tileLabels[row][col].getHeight());
        tilePanes[row][col].add(entityLabel, JLayeredPane.PALETTE_LAYER);
    }
}
