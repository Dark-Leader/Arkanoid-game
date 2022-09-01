package animations.levels;

import Sprites.Block;
import Sprites.backgrounds.FinalFourBackground;
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
public class FinalFour implements LevelInformation {

    private int borderSize;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private List<Block> levelBlocks = new ArrayList<>();
    private String levelName = "Final Four";
    private int numBalls = 3;
    private FinalFourBackground background;
    private int paddleSpeed = 10;
    private int paddleWidth;
    private int width;
    private int height;

    /**
     * Constructor.
     * @param width int - screen width.
     * @param height int - screen height.
     * @param borderSize int - border size at edges of screen.
     */
    public FinalFour(int width, int height, int borderSize) {
        this.borderSize = borderSize;
        this.width = width;
        this.height = height;
        this.background = new FinalFourBackground(borderSize);
        this.paddleWidth = borderSize * 4;
        initialize();
    }

    /**
     * initialize the game level.
     */
    private void initialize() {
        initializeBallVelocities();
        initializeBlocks();
        initializeClouds();
    }

    /**
     * initialize the clouds in the background.
     */
    private void initializeClouds() {
        int firstCloudX = borderSize * 7;
        int firstCloudY = height / 2 + borderSize * 2;

        int secondCloudX = width - borderSize * 5;
        int secondCloudY = height - borderSize * 4;

        int radius = (int) (borderSize * 1.5);

        background.addCloud(firstCloudX, firstCloudY, radius);
        background.addCloud(secondCloudX, secondCloudY, radius);
    }

    /**
     * initialize ball velocities of balls in the level.
     */
    private void initializeBallVelocities() {
        int base = 300;
        int jump = 15;
        int speed = 5;
        for (int i = 0; i < numBalls; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(base + i * jump, speed);
            ballVelocities.add(v);
        }
    }

    /**
     * initialize blocks of the level.
     */
    private void initializeBlocks() {
        int numRows = 7;
        Color[] colors = {Color.DARK_GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        int blockWidth = borderSize * 2;
        int blockHeight = borderSize;
        int baseY = borderSize * 5;
        int baseX = borderSize;
        int numBlocksPerRow = (width - borderSize * 2) / blockWidth;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numBlocksPerRow; j++) {
                int curX = baseX + j * blockWidth;
                int curY = baseY + i * blockHeight;
                Rectangle rectangle = new Rectangle(new Point(curX, curY), blockWidth, blockHeight);
                Block block = new Block(rectangle);
                block.setColor(colors[i]);
                levelBlocks.add(block);
            }
        }
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballVelocities;
    }

    @Override
    public int numberOfBalls() {
        return numBalls;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public List<Block> blocks() {
        return levelBlocks;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

}
