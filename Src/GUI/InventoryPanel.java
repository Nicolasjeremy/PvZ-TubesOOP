package Src.GUI;

import javax.swing.*;
import java.awt.*;
import Src.MainMenu.Inventory;
import Src.Entities.Plant.Plant;

public class InventoryPanel extends JPanel {
    private Inventory inventory;

    public InventoryPanel(Inventory inventory) {
        this.inventory = inventory;
        setLayout(new FlowLayout());
        displayPlants();
    }

    private void displayPlants() {
        for (Plant plant : inventory.getInventory()) {
            JPanel plantPanel = new JPanel();
            plantPanel.setLayout(new BorderLayout());
            
            JLabel nameLabel = new JLabel(plant.getName(), SwingConstants.CENTER);
            JLabel iconLabel = new JLabel(plant.getIcon());
            
            plantPanel.add(iconLabel, BorderLayout.CENTER);
            plantPanel.add(nameLabel, BorderLayout.SOUTH);
            
            add(plantPanel);
        }
    }
}

