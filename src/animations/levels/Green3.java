package animations.levels;

import Sprites.Block;
import Sprites.backgrounds.Green3Background;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * represents a game level in the game.
 */
public class Green3 implements LevelInformation {
    private int borderSize;
    private int width;
    private int height;
    private List<Block> blocks = new ArrayList<>();
    private String levelName = "Green 3";
    private Sprite background;
    private int paddleWidth;
    private int paddleSpeed = 10;
    private int numBalls = 2;
    private List<Velocity> ballVelocities = new ArrayList<>();

    /**
     * Constructor.
     * @param width int - width of screen.
     * @param height int - height of screen.
     * @param borderSize int - border size at edges of screen.
     */
    public Green3(int width, int height, int borderSize) {
        this.borderSize = borderSize;
        this.width = width;
        this.height = height;
        this.background = new Green3Background(borderSize);
        this.paddleWidth = borderSize * 4;
        initialize();
    }

    /**
     * initialize the game level.
     */
    private void initialize() {
        initializeBallVelocities();
        initializeBlocks();
    }

    /**
     * create the blocks of the level.
     */
    private void initializeBlocks() {
        int numRows = 5;
        int numBlocksInRow = 10;
        int blockWidth = borderSize * 2;
        int blockHeight = borderSize;
        int x = width / 2 - borderSize * 5;
        int y = height / 3;

        Color[] colors = {Color.DARK_GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numBlocksInRow; j++) {
                int curX = x + j * blockWidth;
                int curY = y + i * blockHeight;
                Rectangle rectangle = new Rectangle(new Point(curX, curY), blockWidth, blockHeight);
                Block block = new Block(rectangle);
                block.setColor(colors[i]);
                blocks.add(block);
            }
            numBlocksInRow--;
            x += blockWidth;
        }
    }

    /**
     * create velocities for the balls of the level.
     */
    private void initializeBallVelocities() {
        int ballSpeed = 5;
        int[] angles = {315, 30};
        for (int angle: angles) {
            Velocity v = Velocity.fromAngleAndSpeed(angle, ballSpeed);
            ballVelocities.add(v);
        }
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
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
    public int numberOfBalls() {
        return numBalls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
