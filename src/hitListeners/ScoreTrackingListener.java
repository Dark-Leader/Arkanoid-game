package hitListeners;

import Sprites.Ball;
import Sprites.Block;
import interfaces.HitListener;
import settings.Counter;

/**
 * responsible for keeping track of the score -> listener for hits and updates score counter.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private int pointsPerBlock = 5;

    /**
     * Constructor.
     * @param scoreCounter Counter - counter for score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * update score because a hit happened.
     * @param beingHit Block - being hit by ball.
     * @param hitter Ball - ball hitting the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(pointsPerBlock);
    }
}
