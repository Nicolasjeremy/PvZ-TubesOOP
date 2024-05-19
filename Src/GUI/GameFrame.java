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
        setSize(1100, 800);
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
        ArrayList<Entities> entities = new ArrayList<>();

        Jalapeno jalapeno = new Jalapeno(null, null);
        jalapeno.setimagepath("../Image/MenuList/ListJalapeno.png");
        entities.add(jalapeno);

        Squash squash = new Squash(null, null);
        squash.setimagepath("../Image/MenuList/ListSquash.png");
        entities.add(squash);

        TangleKelp tangleKelp = new TangleKelp(null, null);
        tangleKelp.setimagepath("../Image/MenuList/ListTangleKelp.png");
        entities.add(tangleKelp);

        Lilypad lilypad = new Lilypad(null, null);
        lilypad.setimagepath("../Image/MenuList/ListLiliypad.png");
        entities.add(lilypad);

        Tallnut tallnut = new Tallnut(null, null);
        tallnut.setimagepath("../Image/MenuList/ListTallnut.png");
        entities.add(tallnut);

        Wallnut wallnut = new Wallnut(null, null);
        wallnut.setimagepath("../Image/MenuList/ListWallnut.png");
        entities.add(wallnut);

        Peashooter peashooter = new Peashooter(null, null);
        peashooter.setimagepath("../Image/MenuList/ListPeaShooter.png");
        entities.add(peashooter);

        Repeater repeater = new Repeater(null, null);
        repeater.setimagepath("../Image/MenuList/ListRepeater.png");
        entities.add(repeater);

        Snowpea snowpea = new Snowpea(null, null);
        snowpea.setimagepath("../Image/MenuList/ListSnowpea.png");
        entities.add(snowpea);

        Sunflower sunflower = new Sunflower(null, null);
        sunflower.setimagepath("../Image/MenuList/ListSunflower.png");
        entities.add(sunflower);

        ZRaul zRaul = new ZRaul(null, null);
        zRaul.setimagepath("../Image/MenuList/ListZRaul.png");
        entities.add(zRaul);

        ZConeHead zConeHead = new ZConeHead(null, null);
        zConeHead.setimagepath("../Image/MenuList/ListZConeHead.png");
        entities.add(zConeHead);

        ZBucketHead zBucketHead = new ZBucketHead(null, null);
        zBucketHead.setimagepath("../Image/MenuList/ListZBucketHead.png");
        entities.add(zBucketHead);

        ZDipsy zDipsy = new ZDipsy(null, null);
        zDipsy.setimagepath("../Image/MenuList/ListZDipsy.png");
        entities.add(zDipsy);

        ZDolphonRider zDolphonRider = new ZDolphonRider(null, null);
        zDolphonRider.setimagepath("../Image/MenuList/ListZDolphonRider.png");
        entities.add(zDolphonRider);

        ZDuckyTube zDuckyTube = new ZDuckyTube(null, null);
        zDuckyTube.setimagepath("../Image/MenuList/ListZDuckyTube.png");
        entities.add(zDuckyTube);

        ZLala zLala = new ZLala(null, null);
        zLala.setimagepath("../Image/MenuList/ListZLala.png");
        entities.add(zLala);

        ZNormal zNormal = new ZNormal(null, null);
        zNormal.setimagepath("../Image/MenuList/ListZNormal.png");
        entities.add(zNormal);

        ZPoleVault zPoleVault = new ZPoleVault(null, null);
        zPoleVault.setimagepath("../Image/MenuList/ListZPoleVault.png");
        entities.add(zPoleVault);

        ZAsep zAsep = new ZAsep(null, null);
        zAsep.setimagepath("../Image/MenuList/ListZAsep.png");
        entities.add(zAsep);

        for (Entities entity : entities) {
            ImageIcon entityIcon = new ImageIcon(getClass().getResource(entity.getimagepath()));
            JButton entityButton = new JButton(entityIcon);
            entityButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Show entity stats
                    JOptionPane.showMessageDialog(entityListFrame,
                            "Name: " + entity.getName() + "\n" +
                                    "Health: " + entity.getHealth() + "\n" +
                                    "Attack Damage: " + entity.getAttackDmg() + "\n" +
                                    "Attack Speed: " + entity.getAttackSpd(),
                            "Entity Stats", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            entityListFrame.add(entityButton);
        }

        // Show the entity list frame
        entityListFrame.setVisible(true);
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
