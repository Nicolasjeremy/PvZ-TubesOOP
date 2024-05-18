package Src.GUI;

import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    private static Map<String, ImageIcon> images = new HashMap<>();

    static {
        images.put("Peashooter", new ImageIcon("path/to/peashooter.png"));
        images.put("Sunflower", new ImageIcon("path/to/sunflower.png"));
        // Tambahkan entitas lain sesuai kebutuhan
    }

    public static ImageIcon getImage(String key) {
        return images.get(key);
    }
}
