package settings;

import Sprites.ScoreIndicator;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import hitListeners.BallRemover;
import hitListeners.BlockRemover;
import hitListeners.ScoreTrackingListener;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;
import java.util.Random;

/**
 * Represents the game - initializes all objects (sprites and collidables) and runs main game loop.
 */
public class Game {

    private SpriteCollection sprites;
    private GameEnvironment environment;

    private GUI gui;
    private static int width = 800;
    private static int height = 600;

    private static int borderSize = 30;

    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTracker;
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter scoreCounter = new Counter();
    private ScoreIndicator scoreIndicator;


    /**
     * Constructor.
     * @param sprites SpriteCollection - collection of all sprites in the game.
     * @param environment GameEnvironment - collection of all collidable objects in the game.
     */
    public Game(SpriteCollection sprites, GameEnvironment environment) {
        this.sprites = sprites;
        this.environment = environment;
    }

    /**
     * add collidable object to the game.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * remove collidable from the game.
     * @param c Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * add sprite to the game.
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove sprite from the game.
     * @param s Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid Game", width, height);
        initializeListeners();
        initializeBlocks();
        initializeSprites();
    }

    /**
     * initialize all the listeners.
     */
    public void initializeListeners() {
        blockRemover = new BlockRemover(this, this.blockCounter);
        ballRemover = new BallRemover(this, this.ballCounter);
        scoreTracker = new ScoreTrackingListener(this.scoreCounter);
    }


    /**
     * Initializes all blocks in the game -> border blocks, game blocks and paddle.
     */
    private void initializeBlocks() {

        Block top = new Block(new Rectangle(new Point(0, borderSize), width, borderSize));
        Block bottom = new Block(new Rectangle(new Point(0, height + borderSize), width, borderSize));
        bottom.addEventListener(ballRemover);
        Block left = new Block(new Rectangle(new Point(0, borderSize * 2), borderSize,
                height));
        Block right = new Block(new Rectangle(new Point(width - borderSize, borderSize * 2),
                borderSize, height));
        Block[] boundry = {top, bottom, left, right};
        for (Block block: boundry) {
            block.addToGame(this);
        }

        int blockWidth = (borderSize * 3) / 2;
        int blockHeight = borderSize / 2;

        int baseX = width - borderSize - blockWidth;
        int baseY = borderSize * 4;
        int space = 1;
        int numRows = 6;
        int numCols = 12;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols - i; j++) {
                int x = baseX - j * (blockWidth + space);
                int y = baseY + i * (blockHeight + space);
                Rectangle rectangle = new Rectangle(new Point(x, y), blockWidth, blockHeight);
                Block block = new Block(rectangle);
                Color color = new Color(50, 100, 150 + i * 20);
                block.setColor(color);
                block.addToGame(this);
                block.addEventListener(this.blockRemover);
                block.addEventListener(this.scoreTracker);
                this.blockCounter.increase(1);
            }
        }

        int paddleHeight = 5;
        Block block = new Block(new Rectangle(new Point(width / 2 - borderSize / 2, height - borderSize - paddleHeight),
                100, paddleHeight));
        block.setColor(Color.orange);
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), block, borderSize, width - borderSize);
        paddle.addToGame(this);
    }

    /**
     * Initialize sprites in the game.
     */
    private void initializeSprites() {

        int numBalls = 3;

        for (int i = 0; i < numBalls; i++) {
            Ball ball = new Ball(new Point(width / 4, height / 2), 5, Color.WHITE);
            ball.setVelocity(getRandomVelocity());
            ball.setGameEnviroment(this.environment);
            ball.addToGame(this);
        }
        this.ballCounter.increase(numBalls);

        int borderSize = 30;

        Rectangle scoreLocation = new Rectangle(new Point(0, 0), width, borderSize);

        this.scoreIndicator = new ScoreIndicator(this.scoreCounter, scoreLocation, borderSize, borderSize);
        this.scoreIndicator.addToGame(this);


    }

    private boolean won() {
        return this.blockCounter.getValue() == 0;
    }

    private boolean lose() {
        return this.ballCounter.getValue() == 0;
    }

    /**
     * main game loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if (won()) {
                System.out.println("PLAYER WON!");
                this.scoreCounter.increase(100); // completed level.
                break;
            }

            if (lose()) {
                System.out.println("PLAYER LOST!");
                break;
            }
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 0, width, height);

            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        System.out.println("Player gained " + scoreCounter.getValue() + " points.");
        sleeper.sleepFor(1000);
        gui.close();
    }

    /**
     * generate random velocity for a ball.
     * @return Velocity.
     */
    private static Velocity getRandomVelocity() {
        Random rand = new Random();
        double rangeMin = 4;
        double rangeMax = 7;
        double dx = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        double dy = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        if (rand.nextBoolean()) {
            dx *= -1;
        }
        dy *= -1; // only allow balls to move up the screen to allow player time to react.

        return new Velocity(dx, dy);
    }
}
