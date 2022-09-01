package settings;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * represents a class that runs animations on the GUI provided.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * Constructor.
     * @param gui GUI - display the animations on the gui.
     * @param fps int - number of times the screen updates per second.
     */
    public AnimationRunner(GUI gui, int fps) {
        this.gui = gui;
        this.framesPerSecond = fps;
    }

    /**
     * run an animation on the gui, meaning while the animation is not finished, run it
     * and display it on the gui provided.
     * @param animation Animation - to be run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
