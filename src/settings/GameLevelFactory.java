package settings;

import animations.levels.DirectHit;
import animations.levels.FinalFour;
import animations.levels.Green3;
import animations.levels.WideEasy;
import interfaces.LevelInformation;

/**
 * represents a factory to construct the array of game levels according to the user input
 * in the command line arguments.
 */
public class GameLevelFactory {
    /**
     * generate a specific game level according to a string value.
     * if value is "1" return DirectHit, else if value is "2" return WideEasy, else if value is "3" return Green3,
     * else if value is "4" return FinalFour, else return null.
     * @param value String - level type.
     * @param width int - width of screen.
     * @param height int - height of screen.
     * @param borderSize int - border size at edges of screen.
     * @return LevelInformation - level chosen by the user.
     */
    public LevelInformation generateGameLevel(String value, int width, int height, int borderSize) {
        LevelInformation level;
        switch (value) {
            case "1":
                level =  new DirectHit(width, height, borderSize);
                break;
            case "2":
                level =  new WideEasy(width, height, borderSize);
                break;
            case "3":
                level = new Green3(width, height, borderSize);
                break;
            case "4":
                level = new FinalFour(width, height, borderSize);
                break;
            default:
                level = null;
        }
        return level;
    }
}
