package edu.school21.printer.app;

import edu.school21.printer.logic.*;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Parametrs {

    @Parameter(names = { "--white"}, description = "set color for white pixel. Use GREEN, RED, YELLOW, WHITE, BLACK, PURPLE, BLUE.")
    private String white = "WHITE";

    @Parameter(names = { "--black"}, description = "set color for black pixel. Use GREEN, RED, YELLOW, WHITE, BLACK, PURPLE, BLUE.")
    private String black = "BLACK";

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }
}
