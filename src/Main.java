
import biuoop.GUI;
import interfaces.LevelInformation;
import settings.AnimationRunner;
import settings.GameFlow;
import settings.GameLevelFactory;


import java.util.ArrayList;
import java.util.List;


/**
 * Main class - driver code.
 */
public class Main {
    /**
     * Main function to start the program.
     * @param args String[] - ignored.
     */
    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        int fps = 60;
        // open gui
        GUI gui = new GUI("Arkanoid", width, height);
        AnimationRunner runner = new AnimationRunner(gui, fps);

        int numLives = 3;
        int borderSize = 25;
        // create the controller for game flow.
        GameFlow controller = new GameFlow(runner, gui.getKeyboardSensor(), numLives, width, height, borderSize);
        List<LevelInformation> levels = new ArrayList<>();

        GameLevelFactory factory = new GameLevelFactory();
        // get input from user and create levels
        for (String level: args) {
            LevelInformation levelInfo = factory.generateGameLevel(level, width, height, borderSize);
            if (levelInfo != null) {
                levels.add(levelInfo);
            }
        }
        // run game levels the user provided.
        controller.runLevels(levels);
        // exit.
        gui.close();
    }


}
