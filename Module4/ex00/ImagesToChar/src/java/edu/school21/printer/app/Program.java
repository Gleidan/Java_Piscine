package edu.school21.printer.app;

import edu.school21.printer.logic.ImgToChar;
import java.io.File;

public class Program {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: Program *symbol_for_white* *symbol_for_black* *path_to_file*");
            System.exit(-1);
        }

        File f = new File(args[2]);

        ImgToChar itc = new ImgToChar(f);

        char[][] imgArr;

        imgArr = itc.getPixelChar(args[1].charAt(0), args[0].charAt(0));

        for (int i = 0; i < imgArr.length; i++) {
            for (int j = 0; j < imgArr[i].length; j++) {
                System.out.print(imgArr[i][j]);
            }
            System.out.println();
        }

    }
}
