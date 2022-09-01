package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * represents a wrapper of an animation that can be stopped by a key press of the user.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String stopKey;
    private boolean running = true;
    private boolean alreadyPressed = true;
    private Animation animation;

    /**
     * Constructor.
     * @param sensor KeyBoardSensor - get user input.
     * @param key String - key that will stop the animation.
     * @param animation Animation - animation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.stopKey = key;
        this.animation = animation;
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // check if key is pressed
        if (sensor.isPressed(stopKey)) {
            // if the key is pressed we need to check it wasn't already pressed before the animation begun.
            if (!alreadyPressed) {
                this.running = false;
            }
        } else {
            alreadyPressed = false;
        }
        animation.doOneFrame(d);
    }
}
