package hitListeners;

import Sprites.Ball;
import Sprites.Block;
import interfaces.HitListener;
import settings.Counter;
import settings.GameLevel;

/**
 * responsible for removing balls from the game if a ball goes out of bounds.
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter ballsRemaining;

    /**
     * Counstructor.
     * @param game GameLevel.
     * @param ballsRemaining Counter - counter for number of balls remaining in the game.
     */
    public BallRemover(GameLevel game, Counter ballsRemaining) {
        this.game = game;
        this.ballsRemaining = ballsRemaining;
    }

    /**
     * remove the ball from the game and update number of remaining blocks.
     * @param beingHit Block - killer block -> block that removes balls from the game.
     * @param hitter - Ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.ballsRemaining.decrease(1);
    }
}
