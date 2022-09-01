package hitListeners;

import Sprites.Ball;
import Sprites.Block;
import interfaces.HitListener;
import settings.Counter;
import settings.GameLevel;

/**
 * responsible for removing blocks from the game when a ball hits a block.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game GameLevel.
     * @param counter Counter - counter for number of remaining blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter counter) {
        this.game = game;
        this.remainingBlocks = counter;
    }

    /**
     * remove the block from the game and update number of remaining blocks in the game.
     * @param beingHit Block - got hit.
     * @param hitter Ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeEventListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
