package animations.levels;

import Sprites.Block;
import Sprites.backgrounds.WideEasyBackground;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * represents a level in the game.
 */
public class WideEasy implements LevelInformation {
    private int numBalls = 10;
    private List<Velocity> ballVelocities = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    private String levelName = "Wide Easy";
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
     * @param borderSize int - border size at edges of screen.
     */
    public WideEasy(int width, int height, int borderSize) {
        this.width = width;
        this.height = height;
        this.borderSize = borderSize;
        this.paddleWidth = width * 2 / 3;
        this.background = new WideEasyBackground(borderSize);
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
     * initialize velocities for balls in the level.
     */
    private void createVelocities() {
        int baseAngle = 300;
        int angleChange = 12;
        int ballSpeed = 5;
        for (int i = 0; i < numBalls; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(baseAngle + i * angleChange, ballSpeed);
            ballVelocities.add(v);
        }
    }

    /**
     * initialize the blocks of the level.
     */
    public void createLevelBlocks() {

        int x = borderSize;
        int y = height * 3 / 7;

        int[] numBlocksPerColor = {2, 2, 2, 3, 2, 2, 2};
        int numBlocks = IntStream.of(numBlocksPerColor).sum();
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};

        int blockWidth = (width - borderSize * 2) / numBlocks;
        System.out.println(blockWidth);
        int idx = 0;

        for (int i = 0; i < numBlocksPerColor.length; i++) {
            for (int j = 0; j < numBlocksPerColor[i]; j++) {
                int curX = x + idx * blockWidth;
                Rectangle rectangle = new Rectangle(new Point(curX, y), blockWidth, borderSize);
                Block block = new Block(rectangle);
                block.setColor(colors[i]);
                this.blocks.add(block);
                idx++;
            }
        }
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
