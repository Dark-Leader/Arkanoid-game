package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import settings.SpriteCollection;

import java.awt.Color;

/**
 * represents a pause screen that will be shown if the user pressed a curtain key.
 */
public class PauseScreen implements Animation {
    private boolean running;
    private String stopKey;
    private SpriteCollection sprites;

    /**
     * Constructor.
     * @param stopKey String - key that will stop the pause screen.
     * @param sprites SpriteCollection - allow user to see the level while in pause screen.
     */
    public PauseScreen(String stopKey, SpriteCollection sprites) {
        this.stopKey = stopKey;
        this.running = true;
        this.sprites = sprites;
    }

    /**
     * draw all the level sprites onto the screen and a message to tell the user how to exit the pause screen.
     * @param d DrawSurface - draw on it.
     */
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        int fontSize = 32;
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 4, d.getHeight() * 3 / 4, "paused -- press " + stopKey + " to continue", fontSize);
    }

    /**
     * return true if the animation is finished -> user pressed the stop key, else false.
     * @return Boolean.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}
