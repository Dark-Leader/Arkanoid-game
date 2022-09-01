package settings;

import Sprites.ScoreIndicator;
import animations.CountDownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import biuoop.DrawSurface;
import hitListeners.BallRemover;
import hitListeners.BlockRemover;
import hitListeners.ScoreTrackingListener;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * Represents the game - initializes all objects (sprites and collidables) and runs main game loop.
 */
public class GameLevel implements Animation {

    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private boolean running = false;
    private int width;
    private int height;

    private int borderSize;

    private KeyboardSensor sensor;

    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTracker;
    private Counter blockCounter = new Counter();
    private Counter ballCounter = new Counter();
    private Counter scoreCounter;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;

    private LevelInformation info;
    private Counter livesLeft;


    /**
     * Constructor.
     * @param sensor KeyboardSensor - sensor for user input.
     * @param runner AnimationRunner - will run the game.
     * @param scoreCounter Counter - counter for game score.
     * @param info LevelInformation - information on how to initialize the level.
     * @param width int - width of screen.
     * @param height int - height of screen.
     * @param borderSize int - border size at edges of the screen.
     */
    public GameLevel(LevelInformation info, KeyboardSensor sensor, AnimationRunner runner, Counter scoreCounter,
                     int width, int height, int borderSize) {
        this.sensor = sensor;
        this.runner = runner;
        this.scoreCounter = scoreCounter;
        this.info = info;
        this.width = width;
        this.height = height;
        this.borderSize = borderSize;
    }

    /**
     * setter for number of lives left.
     * @param livesLeft int - number of extra lives.
     */
    public void setLivesLeft(Counter livesLeft) {
        this.livesLeft = livesLeft;
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

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        if (this.sensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(sensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(KeyboardSensor.SPACE_KEY, sprites)));
        }

        if (won()) {
            this.scoreCounter.increase(100); // completed level.
            this.running = false;
        }
        if (lose()) {
            if (!spawnNewBall()) {
                this.running = false;
            }
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * add sprite to the game.
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    private boolean spawnNewBall() {

        if (this.livesLeft.getValue() <= 0) {
            return false;
        }
        Random rand = new Random();
        int ballSpeed = 5;
        int x = width / 2;
        int y = height - borderSize * 2;
        int radius = 5;
        int angle = 330;
        if (rand.nextBoolean()) {
            angle = 30;
        }
        Ball ball = new Ball(new Point(x, y), radius, Color.WHITE);
        ball.setGameEnviroment(this.environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(angle, ballSpeed));
        ball.addToGame(this);
        this.livesLeft.decrease(1);
        this.ballCounter.increase(1);
        return true;
    }

    /**
     * remove sprite from the game.
     * @param s Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * check if the user finished the level, meaning all block are removed.
     * @return Boolean, true if finished, else false.
     */
    public boolean finished() {
        return blockCounter.getValue() == 0;
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.running = true;
        initializeListeners();
        loadBlocks();
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

    private void loadBlocks() {
        this.addSprite(this.info.getBackground());
        createBorder();
        List<Block> blocksList = this.info.blocks();
        for (Block block: blocksList) {
            block.addToGame(this);
            block.addEventListener(this.blockRemover);
            block.addEventListener(this.scoreTracker);
        }
        this.blockCounter.increase(this.info.numberOfBlocksToRemove());

        int paddleHeight = 5;
        Rectangle paddleBody = new Rectangle(new Point(width / 2 - info.paddleWidth() / 2, height - borderSize),
                info.paddleWidth(), paddleHeight);
        Block paddleBlock = new Block(paddleBody);
        paddleBlock.setColor(Color.YELLOW);

        Paddle paddle = new Paddle(this.sensor, paddleBlock, borderSize, width - borderSize);
        paddle.setSpeed(info.paddleSpeed());
        paddle.addToGame(this);

    }

    private void createBorder() {
        Block top = new Block(new Rectangle(new Point(0, borderSize), width, borderSize));
        Block bottom = new Block(new Rectangle(new Point(0, height + borderSize), width, borderSize));
        bottom.addEventListener(ballRemover);
        Block left = new Block(new Rectangle(new Point(0, borderSize * 2), borderSize,
                height));
        Block right = new Block(new Rectangle(new Point(width - borderSize, borderSize * 2),
                borderSize, height));
        Block[] boundary = {top, bottom, left, right};
        for (Block block: boundary) {
            block.addToGame(this);
        }
    }

    /**
     * Initialize sprites in the game.
     */
    private void initializeSprites() {

        List<Velocity> ballsVelocities = this.info.initialBallVelocities();

        int numBalls = this.info.numberOfBalls();
        int x = width / 2;
        int y = height - borderSize * 2;
        int radius = 5;

        for (int i = 0; i < numBalls; i++) {
            Ball ball = new Ball(new Point(x, y), radius, Color.WHITE);
            ball.setGameEnviroment(this.environment);

            ball.setVelocity(ballsVelocities.get(i));
            ball.addToGame(this);
        }
        this.ballCounter.increase(numBalls);

        Rectangle scoreLocation = new Rectangle(new Point(0, 0), width, borderSize);

        this.scoreIndicator = new ScoreIndicator(this.scoreCounter, scoreLocation, borderSize,
                livesLeft, info.levelName());
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
        this.runner.run(new CountDownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }
}
