package hitListeners;

import Sprites.Ball;
import Sprites.Block;
import interfaces.HitListener;

/**
 * test listener to debug hits.
 */
public class PrintingHitListener implements HitListener {
    /**
     * print to console that a hit happened.
     * @param beingHit Block - being hit by ball.
     * @param hitter Ball - ball hitting the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
