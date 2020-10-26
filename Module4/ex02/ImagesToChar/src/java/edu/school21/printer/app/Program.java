package edu.school21.printer.app;

import edu.school21.printer.logic.*;
import com.beust.jcommander.JCommander;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

public class Program {

    public static void main(String[] args) {
        Parametrs comLine = new Parametrs();

        JCommander command = JCommander.newBuilder().programName("ImagesToChar").addObject(comLine).build();

        ImgToChar itc = new ImgToChar();

        Attribute[][] imgArr;

        if (args.length < 1 || args.length > 2) {
            command.usage();
            System.exit(-1);
        }
        else {
            command.parse(args);
        }

        try {
            itc.checkAttribute(comLine.getBlack(), comLine.getWhite());
        }
        catch (IllegalAttributeException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        imgArr = itc.getPixelChar(comLine.getBlack(), comLine.getWhite());

        for (int i = 0; i < imgArr.length; i++) {
            for (int j = 0; j < imgArr[i].length; j++) {
                System.out.print(Ansi.colorize(" ", imgArr[i][j]));
            }
            System.out.println();
        }

    }
}
