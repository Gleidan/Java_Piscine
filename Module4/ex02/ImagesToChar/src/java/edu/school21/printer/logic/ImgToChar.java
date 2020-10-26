package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import com.diogonunes.jcolor.Attribute;
import java.util.Map;
import edu.school21.printer.app.*;

public class ImgToChar {
    BufferedImage img;
    Map<String, Attribute> mapColor;

    public ImgToChar() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("image.bmp");
        mapInitialized();
        try {
            img = ImageIO.read(in);
        }
        catch (Exception e) {
            System.err.println("No input file");
            System.exit(-1);
        }
    }

    public Attribute[][] getPixelChar(String black, String white) {
        Attribute[][] imgArr = new Attribute[img.getHeight()][img.getWidth()];

        Attribute bl = Attribute.BLACK_BACK();

        Attribute wh = Attribute.WHITE_BACK();

        for (Map.Entry<String, Attribute> tmp : mapColor.entrySet()) {
            if (tmp.getKey().equals(black)) {
                bl = tmp.getValue();
            }
            else if (tmp.getKey().equals(white)) {
                wh = tmp.getValue();
            }
        }

        for (int y = 0; y < imgArr.length; y++) {
            for (int x = 0; x < imgArr[y].length; x++) {
                int color = img.getRGB(x, y);
                if (color == Color.BLACK.getRGB()) {
                    imgArr[y][x] = bl;
                }
                else {
                    imgArr[y][x] = wh;
                }
            }
        }

        return imgArr;
    }

    private void mapInitialized() {
        mapColor = new HashMap<>();

        mapColor.put("GREEN", Attribute.GREEN_BACK());
        mapColor.put("RED", Attribute.RED_BACK());
        mapColor.put("YELLOW", Attribute.YELLOW_BACK());
        mapColor.put("WHITE", Attribute.WHITE_BACK());
        mapColor.put("BLACK", Attribute.BLACK_BACK());
        mapColor.put("PURPLE", Attribute.BACK_COLOR(199, 21, 133));
        mapColor.put("BLUE", Attribute.BACK_COLOR(0, 0, 255));
    }

    public void checkAttribute(String black, String white) {
        if (!mapColor.containsKey(black) || !mapColor.containsKey(white)) {
            throw new IllegalAttributeException("This color is not supported");
        }
    }
}
