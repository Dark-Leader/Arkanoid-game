package settings;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;

/**
 * Represents a collection of sprites in a game.
 */
public class SpriteCollection {

    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * add sprite to the collection.
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * move all the sprites on the screen.
     */
    public void notifyAllTimePassed() {
        for (Sprite sp: this.sprites) {
            sp.timePassed();
        }
    }

    /**
     * draw all the sprites to the screen.
     * @param d DrawSurface - to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sp: this.sprites) {
            sp.drawOn(d);
        }
    }
}
