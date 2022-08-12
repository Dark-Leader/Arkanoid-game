package interfaces;

import Sprites.Ball;
import Sprites.Block;

/**
 * represents a hit listener -> gets updated when a ball hits a block.
 */
public interface HitListener {
    /**
     * represents a hit event -> whenever a ball hits a block we get an update.
     * @param beingHit Block - being hit by ball.
     * @param hitter Ball - ball hitting the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
