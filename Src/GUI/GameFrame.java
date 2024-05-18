package Src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Initialize map label
        
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
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("../Image/pvz_image.jpg"));
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
        // Logika untuk menampilkan daftar entity (zombie dan plant)
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
