package interfaces;

import Sprites.Block;
import geometry.Velocity;

import java.util.List;

/**
 * represents a collection of information regarding a game level.
 */
public interface LevelInformation {
    /**
     * returns number of balls in the level.
     * @return int.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return List[Velocity].
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns speed of paddle.
     * @return int.
     */
    int paddleSpeed();

    /**
     * returns width of paddle.
     * @return int.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return String.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return Sprite.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return List[Block].
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return int.
     */
    int numberOfBlocksToRemove();
}
