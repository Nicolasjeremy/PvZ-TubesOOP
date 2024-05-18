package Src.GUI;

import javax.swing.*;
import java.awt.*;
import Src.GameMaps.*;

public class GUIStart extends JFrame {

    public GUIStart() {
        setTitle("Plants vs Zombies");
        setSize(1100, 806);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        GameMap gameMap = new GameMap();
        MainMenuPanel mainMenuPanel = new MainMenuPanel(cardLayout, mainPanel);
        StartGameMapPanel gameMapPanel = new StartGameMapPanel(gameMap);

        mainPanel.add(mainMenuPanel, "mainMenu");
        mainPanel.add(gameMapPanel, "gameMap");

        add(mainPanel);

        cardLayout.show(mainPanel, "mainMenu");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIStart guiStart = new GUIStart();
            guiStart.setVisible(true);
        });
    }
}
