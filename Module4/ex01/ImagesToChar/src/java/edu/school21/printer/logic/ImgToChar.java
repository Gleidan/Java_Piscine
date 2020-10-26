package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class ImgToChar {
    BufferedImage img;

    public ImgToChar() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("image.bmp");

        try {
            img = ImageIO.read(in);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public char[][] getPixelChar(char black, char white) {
        char[][] imgArr = new char[img.getHeight()][img.getWidth()];

        for (int y = 0; y < imgArr.length; y++) {
            for (int x = 0; x < imgArr[y].length; x++) {
                int color = img.getRGB(x, y);
                if (color == Color.BLACK.getRGB()) {
                    imgArr[y][x] = black;
                }
                else {
                    imgArr[y][x] = white;
                }
            }
        }

        return imgArr;
    }
}
