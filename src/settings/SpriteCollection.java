package settings;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of sprites in a game.
 */
public class SpriteCollection {

    private ArrayList<Sprite> sprites = new ArrayList<>();

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
        List<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite sp: copy) {
            sp.timePassed();
        }
    }

    /**
     * remove Sprite from collection.
     * @param s Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * draw all the sprites to the screen.
     * @param d DrawSurface - to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copy = new ArrayList<>(this.sprites);
        for (Sprite sp: copy) {
            sp.drawOn(d);
        }
    }
}
