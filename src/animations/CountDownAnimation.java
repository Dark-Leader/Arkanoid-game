package animations;

import biuoop.DrawSurface;
import interfaces.Animation;
import settings.SpriteCollection;

import java.awt.Color;

/**
 * Give user a couple seconds before each level starts to be acclimated with the game level.
 */
public class CountDownAnimation implements Animation {

    private boolean running;
    private long startTime = -1;
    private double duration = 2;
    private int countFrom;

    private SpriteCollection sprites;

    /**
     * Constructor.
     * @param numSeconds length of count down timer.
     * @param countFrom start value for count down.
     * @param sprites sprites of the game level - we want to show the user the game level - meaning block
     * locations and balls to prepare.
     */
    public CountDownAnimation(double numSeconds, int countFrom, SpriteCollection sprites) {
        this.running = true;
        this.sprites = sprites;
        this.duration = numSeconds;
        this.countFrom = countFrom;
    }

    /**
     * check if count down is over.
     * @return Boolean - true if count down is over, else false.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * draw the game level sprites onto the screen.
     * @param d DrawSurface - draw on it.
     */
    private void drawAllSprites(DrawSurface d) {
        this.sprites.drawAllOn(d);
    }

    /**
     * update the count down animation.
     * @param d DrawSurface - draw the animation on it.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        long cur = System.currentTimeMillis();
        if (this.startTime == -1) {
            this.startTime = cur;
        }
        if ((cur - this.startTime) / 1000 > this.duration) {
            this.running = false;
        }
        this.drawAllSprites(d);
        this.drawRemainingTime(d, cur);

    }

    /**
     * draw remaining time of the count down onto the screen.
     * @param d DrawSurface - draw on it.
     * @param currentTime long - number of seconds left.
     */
    public void drawRemainingTime(DrawSurface d, long currentTime) {

        long passed = (currentTime - this.startTime) / 1000;
        long displayTime = countFrom - passed;
        int width = d.getWidth();
        int height = d.getHeight();
        String message = displayTime + "...";
        d.setColor(Color.RED);
        d.drawText(width / 2, height / 2, message, 40);
    }
}
