package Src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Src.Entities.*;
import Src.Entities.Plant.Plant;
import Src.Entities.Plant.Melee.Jalapeno;
import Src.Entities.Plant.Melee.Squash;
import Src.Entities.Plant.Melee.TangleKelp;
import Src.Entities.Plant.Passive.Lilypad;
import Src.Entities.Plant.Passive.Sunflower;
import Src.Entities.Plant.Passive.Tallnut;
import Src.Entities.Plant.Passive.Wallnut;
import Src.Entities.Plant.Shooter.Peashooter;
import Src.Entities.Plant.Shooter.Repeater;
import Src.Entities.Plant.Shooter.Snowpea;
import Src.Entities.Zombie.ZAsep;
import Src.Entities.Zombie.ZBucketHead;
import Src.Entities.Zombie.ZConeHead;
import Src.Entities.Zombie.ZDipsy;
import Src.Entities.Zombie.ZDolphonRider;
import Src.Entities.Zombie.ZDuckyTube;
import Src.Entities.Zombie.ZLala;
import Src.Entities.Zombie.ZNormal;
import Src.Entities.Zombie.ZPoleVault;
import Src.Entities.Zombie.ZRaul;
import Src.Entities.Zombie.Zombie;
import Src.GameMaps.*;

public class GameFrame extends JFrame {
    private JButton startButton;
    private JButton entityListButton;
    private JButton helpButton;
    private JButton exitButton;

    public GameFrame() {
        setTitle("Plants vs Zombies");
        setSize(1100, 806);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new GridLayout(4, 1));

        // Tombol "Start Game"
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Jalankan game PvZ
                startPvZGame();
            }
        });
        add(startButton);

        // Tombol "Entity List"
        entityListButton = new JButton("Entity List");
        entityListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tampilkan daftar entity (zombie dan plant)
                showEntityList();
            }
        });
        add(entityListButton);

        // Tombol "Help"
        helpButton = new JButton("Help");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tampilkan menu bantuan
                showHelp();
            }
        });
        add(helpButton);

        // Tombol "Exit"
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Keluar dari aplikasi
                System.exit(0);
            }
        });
        add(exitButton);
    }

    private void startPvZGame() {
        // Hapus semua komponen dari frame kecuali tombol-tombol di sisi atas
        getContentPane().removeAll();

        // Panel untuk menampilkan peta PvZ
        JPanel mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setSize(1100, 806);
                // Menggambar gambar peta PvZ
                ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("Image/pvz_image.jpg"));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Tambahkan panel peta ke tengah frame
        add(mapPanel, BorderLayout.CENTER);

        // Perbarui tampilan frame
        revalidate();
        repaint();
    }

    private void showEntityList() {
        // Create a new frame for the entity list
        JFrame entityListFrame = new JFrame("Entity List");
        entityListFrame.setSize(1100, 806);
        entityListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        entityListFrame.setLayout(new GridLayout(4, 5));

        // Create entity list
        ArrayList<Object> entities = new ArrayList<>();
        entities.add(new Jalapeno(null, null));
        entities.add(new Squash(null, null));
        entities.add(new TangleKelp(null, null));
        entities.add(new Lilypad(null, null));
        entities.add(new Tallnut(null, null));
        entities.add(new Wallnut(null, null));
        entities.add(new Peashooter(null, null));
        entities.add(new Repeater(null, null));
        entities.add(new Snowpea(null, null));
        entities.add(new Sunflower(null, null));
        entities.add(new ZRaul(null, null));
        entities.add(new ZConeHead(null, null));
        entities.add(new ZBucketHead(null, null));
        entities.add(new ZDipsy(null, null));
        entities.add(new ZDolphonRider(null, null));
        entities.add(new ZDuckyTube(null, null));
        entities.add(new ZLala(null, null));
        entities.add(new ZNormal(null, null));
        entities.add(new ZPoleVault(null, null));
        entities.add(new ZAsep(null, null));

        for (Object entity : entities) {
            if (entity instanceof Plant) {
                Plant plant = (Plant) entity;
                ImageIcon plantIcon = new ImageIcon(getClass().getResource(plant.getimagepath()));
                JButton entityButton = new JButton(plant.getName(), plantIcon);
                entityButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Show entity stats
                        JOptionPane.showMessageDialog(entityListFrame,
                                "Name: " + plant.getName() + "\n" +
                                        "Attack Damage: " + plant.getAttackDmg() + "\n" +
                                        "Attack Speed: " + plant.getAttackSpd() + "\n" +
                                        "Cooldown: " + plant.getCooldown() + "\n" +
                                        "Cost: " + plant.getCost() + "\n" +
                                        "Health: " + plant.getHealth() + "\n" +
                                        "Range: " + plant.getRange(),
                                "Plant Stats", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                entityListFrame.add(entityButton);
            } else if (entity instanceof Zombie) {
                Zombie zombie = (Zombie) entity;
                ImageIcon zombieIcon = new ImageIcon(getClass().getResource(zombie.getimagepath()));
                JButton entityButton = new JButton(zombie.getName(), zombieIcon);
                entityButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Show entity stats
                        JOptionPane.showMessageDialog(entityListFrame,
                                "Name: " + zombie.getName() + "\n" +
                                        "Health: " + zombie.getHealth() + "\n" +
                                        "Attack Damage: " + zombie.getAttackDmg() + "\n" +
                                        "Attack Speed: " + zombie.getAttackSpd() + "\n" +
                                        "Special: " + zombie.getSpecial() + "\n" +
                                        "Aquatic: " + zombie.getAquatic(),
                                "Zombie Stats", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                entityListFrame.add(entityButton);
            }
        }

        // Show the entity list frame
        entityListFrame.setVisible(true);
    }

    private ImageIcon getImageIcon(String path) {
        java.net.URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return new ImageIcon(); // Return an empty icon or a default placeholder image
        }
    }

    private void showHelp() {
        // Logika untuk menampilkan menu bantuan
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new GameFrame();
                frame.setVisible(true);
            }
        });
    }
}
