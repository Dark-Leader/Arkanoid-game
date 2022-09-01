package Sprites;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import settings.Counter;
import settings.GameLevel;

import java.awt.Color;

/**
 * responsible for displaying the current score, number of lives left, name of current level.
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreCounter;
    private Rectangle background;
    private Color backgroundColor = Color.YELLOW;
    private Color fontColor = Color.BLUE;
    private int fontSize = 20;
    private int borderSize;
    private Counter livesLeft;
    private String levelName;

    /**
     * Constructor.
     * @param scoreCounter Counter - counter for user score.
     * @param background Rectangle - background rectangle.
     * @param borderSize int - border size at edges of screen.
     * @param livesLeft Counter - number of extra lives.
     * @param levelName String - name of the level.
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle background, int borderSize, Counter livesLeft,
                          String levelName) {
        this.scoreCounter = scoreCounter;
        this.background = background;
        this.borderSize = borderSize;
        this.livesLeft = livesLeft;
        this.levelName = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        Point topLeft = background.getTopLeft();
        int x = (int) topLeft.getX();
        int y = (int) topLeft.getY();
        int width = (int) background.getWidth();
        int height = (int) background.getHeight();

        String livesLeftMessage = "Lives: " + livesLeft.getValue();
        String scoreMessage = "Score: " + scoreCounter.getValue();
        String levelNameMessage = "Level Name: " + levelName;

        d.setColor(this.backgroundColor);
        d.fillRectangle(x, y, width, height);

        d.setColor(this.fontColor);
        // draw lives left
        d.drawText(width / 6, y + borderSize * 4 / 5, livesLeftMessage, this.fontSize);
        d.drawText(width * 3 / 7, y + borderSize * 4 / 5, scoreMessage, this.fontSize);
        d.drawText(width * 2 / 3, y + borderSize * 4 / 5, levelNameMessage, this.fontSize);
    }

    /**
     * sprite is constant so we do nothing.
     */
    @Override
    public void timePassed() { }

    /**
     * add the sprite to the game.
     * @param game GameLevel.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
