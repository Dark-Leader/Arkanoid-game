package interfaces;

import biuoop.DrawSurface;

/**
 * Represents a sprite in a game.
 */
public interface Sprite {

    /**
     * draw the sprite onto a draw surface.
     * @param d DrawSurface - to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * move the sprite on the screen.
     */
    void timePassed();
}
