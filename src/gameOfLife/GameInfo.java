package gameOfLife;

/**
 * Contain information of game.
 *
 * @author Narawish Sangsiriwut
 */
public class GameInfo {
    /**
     * Rule of game.
     */
    public final static String RULE = "For a space that is populated:\n" +
            "\tEach cell with one or no neighbors dies, as if by solitude.\n" +
            "\tEach cell with four or more neighbors dies, as if by overpopulation.\n" +
            "\tEach cell with two or three neighbors survives.\n" +
            "For a space that is empty or unpopulated:\n" +
            "\tEach cell with three neighbors becomes populated.";
    public final static String CONTROL = "draw: \tclick draw button before drawing canvas to set cell on board to alive\n" +
            "erase: \tclick erase button before drawing canvas to set cell on board to dead\n" +
            "clear: \tchange all cell on board to dead\n" +
            "random: \trandom all cell on board\n" +
            "speed: \tchange the speed from fast-slow\n" +
            "start: \tplay the simulation\n" +
            "stop: \tstop the simulation\n" +
            "step: \tdo one step of game";
}
