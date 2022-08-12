package Sprites;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
import settings.Counter;
import settings.Game;

import java.awt.Color;

/**
 * responsible for displaying the user score.
 */
public class ScoreIndicator implements Sprite {

    private Counter scoreCounter;
    private Rectangle background;
    private Color backgroundColor = Color.YELLOW;
    private Color fontColor = Color.BLUE;
    private int fontSize = 20;
    private int horizontalOffset;
    private int verticalOffset;

    /**
     * Constructor.
     * @param scoreCounter Counter - counter for user score.
     * @param background Rectangle - background rectangle.
     * @param horizontalOffset int - horizontal offset for score text location.
     * @param verticalOffset int - vertical offset for score text location.
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle background, int horizontalOffset, int verticalOffset) {
        this.scoreCounter = scoreCounter;
        this.background = background;
        this.horizontalOffset = horizontalOffset;
        this.verticalOffset = verticalOffset;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Point topLeft = background.getTopLeft();
        int x = (int) topLeft.getX();
        int y = (int) topLeft.getY();
        int width = (int) background.getWidth();
        int height = (int) background.getHeight();

        String message = "Score: " + scoreCounter.getValue();
        d.setColor(this.backgroundColor);
        d.fillRectangle(x, y, width, height);
        d.setColor(this.fontColor);
        d.drawText((x + width) / 2 - horizontalOffset, y + verticalOffset * 4 / 5, message, this.fontSize);
    }

    /**
     * sprite is constant so we do nothing.
     */
    @Override
    public void timePassed() { }

    /**
     * add the sprite to the game.
     * @param game Game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
