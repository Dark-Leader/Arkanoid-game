package settings;

import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;

import java.util.List;

/**
 * represents a controller of the flow of the game -> runs the game levels in the game.
 */
public class GameFlow {

    private AnimationRunner runner;
    private KeyboardSensor sensor;
    private Counter scoreCounter = new Counter();
    private Counter livesLeft = new Counter();
    private int width;
    private int height;
    private int borderSize;

    /**
     * Constructor.
     * @param runner AnimationRunner - responsible for running all the game level on the GUI.
     * @param sensor KeyBoardSensor - responsible for user input such a enter a pause screen or exit the game
     *               at the end screen.
     * @param backupBalls int - number of extra lives.
     * @param width int - screen width.
     * @param height int - screen height.
     * @param borderSize int - border size at edges of the screen.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor sensor, int backupBalls, int width,
                    int height, int borderSize) {
        this.runner = runner;
        this.sensor = sensor;
        this.livesLeft.increase(backupBalls);
        this.width = width;
        this.height = height;
        this.borderSize = borderSize;
    }

    /**
     * run the game, meaning create the game levels in the list of levels and run them
     * one after the other, as long as the user didn't lose.
     * @param levels
     */
    public void runLevels(List<LevelInformation> levels) {

        boolean playerWon = true;

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.sensor,
                    this.runner,
                    this.scoreCounter,
                    this.width,
                    this.height,
                    this.borderSize
               );
            level.setLivesLeft(livesLeft);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (!level.finished()) {
                playerWon = false;
                break;
            }
        }
        // end screen to display if user won or lost, and display user score.
        this.runner.run(new KeyPressStoppableAnimation(sensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(playerWon, scoreCounter.getValue())));
    }
}
