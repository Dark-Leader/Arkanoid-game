package settings;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprites.Ball;
import Sprites.Block;
import Sprites.Paddle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
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
     * add sprite to the game.
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid Game", width, height);
        initializeBlocks();
        initializeSprites();
    }

    /**
     * Initializes all blocks in the game -> border blocks, game blocks and paddle.
     */
    private void initializeBlocks() {
        Block top = new Block(new Rectangle(new Point(0, 0), width, borderSize));
        Block bottom = new Block(new Rectangle(new Point(0, height - borderSize), width, borderSize));
        Block left = new Block(new Rectangle(new Point(0, borderSize + 1), borderSize,
                height - borderSize * 2 - 2));
        Block right = new Block(new Rectangle(new Point(width - borderSize, borderSize + 1),
                borderSize, height - borderSize * 2 - 2));
        Block[] boundry = {top, bottom, left, right};
        for (Block block: boundry) {
            block.addToGame(this);
        }

        int blockWidth = (borderSize * 3) / 2;
        int blockHeight = borderSize / 2;

        int baseX = width - borderSize - blockWidth;
        int baseY = borderSize * 4;
        int space = 1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                int x = baseX - j * (blockWidth + space);
                int y = baseY + i * (blockHeight + space);
                Rectangle rectangle = new Rectangle(new Point(x, y), blockWidth, blockHeight);
                Block block = new Block(rectangle);
                Color color = new Color(50, 100, 150 + i * 20);
                block.setColor(color);
                block.addToGame(this);
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
        Ball ball = new Ball(new Point(width / 4, height / 2), 5, Color.WHITE);
        ball.setVelocity(getRandomVelocity());
        ball.setGameEnviroment(this.environment);
        ball.addToGame(this);
    }

    /**
     * main game loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
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
    }

    /**
     * generate random velocity for a ball.
     * @return Velocity.
     */
    private static Velocity getRandomVelocity() {
        Random rand = new Random();
        double rangeMin = 5;
        double rangeMax = 8;
        double dx = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        double dy = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        if (rand.nextBoolean()) {
            dx *= -1;
        }
        if (rand.nextBoolean()) {
            dy *= -1;
        }

        return new Velocity(dx, dy);
    }
}
