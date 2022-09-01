package interfaces;

import biuoop.DrawSurface;

/**
 * Represents a game animation -> game level or curtain screen such as pause or end screen.
 */
public interface Animation {
    /**
     * update the animation -> generate new frame.
     * @param d DrawSurface - draw the animation on it.
     */
    void doOneFrame(DrawSurface d);

    /**
     * check if the animation is finished.
     * @return Boolean - true if it is finished, else false.
     */
    boolean shouldStop();
}
