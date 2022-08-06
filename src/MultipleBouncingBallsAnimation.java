import Sprites.Ball;
import Geometry.Point;
import Geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Main function - driver code.
     * create balls at random locations and gives them random velocity's, color.
     * @param args String[] - used to set radius of balls.
     */
    public static void main(String[] args) {
        int size = args.length;
        Ball[] balls = new Ball[size];
        int width = 800;
        int height = 600;
        Color[] colors = new Color[]{
                        Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN,
                        Color.BLACK, Color.PINK, Color.ORANGE
                };
        for (int i = 0; i < size; i++) {
            int radius = Integer.parseInt(args[i]);
            Point center = getRandomCenter(width, height, radius);
            Ball current = new Ball(center, radius, getRandomColor(colors));
            current.setVelocity(createVelocityFromRadius(radius));
            balls[i] = current;
        }
        GUI gui = new GUI("MultipleBouncingBalls", width, height);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball: balls) {
                ball.moveOneStep(width, height);
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }

    }

    /**
     * create random center point for ball.
     * @param width int - gui width.
     * @param height int - gui height.
     * @param radius int - ball radius.
     * @return Point - center point for ball.
     */
    public static Point getRandomCenter(int width, int height, int radius) {
        Random rand = new Random();
        double x = radius + rand.nextInt(width - radius * 2);
        double y = radius + rand.nextInt(height - radius * 2);
        return new Point(x, y);
    }

    /**
     * create random velocity for ball -> smaller balls move faster.
     * min velocity value is set for all balls with radius >= 50.
     * @param radius int - ball radius.
     * @return Velocity.
     */
    public static Velocity createVelocityFromRadius(int radius) {
        double max = 5;
        double min = -5;
        double maxSize = 50;
        Random rand = new Random();
        double speedX = rand.nextInt((int) (max - min)) + min;
        double speedY = rand.nextInt((int) (max - min)) + min;

        if (radius < maxSize) {
            speedX *= Math.log(maxSize / radius);
            speedY *= Math.log(maxSize / radius);
        }
        return new Velocity(speedX, speedY);
    }

    /**
     * chooses random color for ball.
     * @param colors Color[] - array of colors to choose from.
     * @return Color.
     */
    public static Color getRandomColor(Color[] colors) {
        Random rand = new Random();
        int max = colors.length;
        int idx = rand.nextInt(max);
        return colors[idx];
    }
}
