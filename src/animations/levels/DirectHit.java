package animations.levels;

import Sprites.Block;
import Sprites.backgrounds.DirectHitBackground;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a level in the game.
 */
public class DirectHit implements LevelInformation {
    private int numBalls = 1;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    private String levelName = "Direct Hit";
    private Sprite background;
    private int width;
    private int height;
    private int borderSize;
    private int paddleWidth;
    private int paddleSpeed = 10;

    /**
     * Constructor.
     * @param width int - width of screen.
     * @param height int - height of screen.
     * @param borderSize int - border size at edges of the screen.
     */
    public DirectHit(int width, int height, int borderSize) {
        this.width = width;
        this.height = height;
        this.borderSize = borderSize;
        this.background = new DirectHitBackground(borderSize);
        this.paddleWidth = borderSize * 4;
        initialize();
    }

    /**
     * initialize the level.
     */
    public void initialize() {
        createVelocities();
        createLevelBlocks();
    }

    /**
     * create the ball velocities for the balls of the level.
     */
    private void createVelocities() {
        for (int i = 0; i < numBalls; i++) {
            Velocity v = new Velocity(0, -5);
            ballVelocities.add(v);
        }
    }

    /**
     * create the blocks of the level.
     */
    public void createLevelBlocks() {

        Block block = new Block(new Rectangle(new Point(width / 2 - borderSize / 2,
                borderSize * 7 - borderSize / 2), borderSize, borderSize));
        block.setColor(Color.RED);
        blocks.add(block);
    }

    @Override
    public int numberOfBalls() {
        return numBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
