package animations;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * represents the end screen of the game - display whether the user won or lost, and the score he got.
 */
public class EndScreen implements Animation {
    private boolean running = true;
    private boolean gameResult;
    private int score;

    /**
     * Constructor.
     * @param gameResult Boolean - user won or lost.
     * @param score int - user score.
     */
    public EndScreen(boolean gameResult, int score) {
        this.gameResult = gameResult;
        this.score = score;
    }
    @Override
    public boolean shouldStop() {
        return !running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int width = d.getWidth();
        int height = d.getHeight();

        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, width, height);

        int fontSize = 32;
        String message;
        if (gameResult) {
             message = "You Won! Your Score: " + score;
        } else {
            message = "You Lost! Your Score: " + score;
        }
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 3, height * 2 / 5, message, fontSize);

        String exit = "Press Space to exit";
        d.drawText(d.getWidth() / 3, height * 3 / 5, exit, fontSize);
    }
}
