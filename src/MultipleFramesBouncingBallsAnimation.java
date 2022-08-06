import Sprites.Ball;
import Geometry.Point;
import Geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Animation of Multiple balls bouncing in 2 frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    private Color[] colors = new Color[]{
            Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN,
            Color.BLACK, Color.PINK, Color.ORANGE
    };
    private String[] args;

    /**
     * driver code.
     * @param args String[] - command line arguments.
     */
    public static void main(String[] args) {
        MultipleFramesBouncingBallsAnimation animation = new MultipleFramesBouncingBallsAnimation();
        animation.args = args;
        animation.play();
    }

    /**
     * plays the animation.
     */
    public void play() {
        int f1x1 = 50;
        int f1y1 = 50;
        int f1x2 = 500;
        int f1y2 = 500;
        int f2x1 = 450;
        int f2y1 = 450;
        int f2x2 = 600;
        int f2y2 = 600;
        int size = this.args.length;
        int cut = size / 2;
        ArrayList<Ball> f1Balls = new ArrayList<>();
        ArrayList<Ball> f2Balls = new ArrayList<>();
        insertBallsToFrame(f1x1, f1y1, f1x2, f1y2, 0, cut, f1Balls);
        insertBallsToFrame(f2x1, f2y1, f2x2, f2y2, cut, size, f2Balls);
        GUI gui = new GUI("Multiple Frames Multiple Bouncing Balls", 1000, 700);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(f1x1, f1y1, f1x2 - f1x1, f1y2 - f1y1);
            d.setColor(Color.YELLOW);
            d.fillRectangle(f2x1, f2y1, f2x2 - f2x1, f2y2 - f2y1);
            for (Ball ball: f1Balls) {
                ball.moveOneStep(f1x1, f1y1, f1x2, f1y2);
                ball.drawOn(d);
            }
            for (Ball ball: f2Balls) {
                ball.moveOneStep(f2x1, f2y1, f2x2, f2y2);
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * inserts balls to frame.
     * @param startX int - start x pos of frame.
     * @param startY int - start y pos of frame.
     * @param endX int - end x pos of frame.
     * @param endY int - end y pos of frame.
     * @param startIdx int - index to start adding balls from command line arguments.
     * @param endIdx int - end index of balls to add from command line arguments.
     * @param balls ArrayList[Ball] - list of balls in current frame -> all the balls will be added to it.
     */
    public void insertBallsToFrame(int startX, int startY, int endX, int endY,
                                   int startIdx, int endIdx, ArrayList<Ball> balls) {
        for (int i = startIdx; i < endIdx; i++) {
            int radius = Integer.parseInt(this.args[i]);
            Point center = getRandomCenter(startX, startY, endX, endY, radius);
            Ball current = new Ball(center, radius, getRandomColor());
            current.setVelocity(createVelocityFromRadius(radius));
            balls.add(current);
        }
    }

    /**
     * generate random center point for ball.
     * @param startX int - min x pos.
     * @param startY int - min y pos.
     * @param endX int - max x pos.
     * @param endY int - max y pos.
     * @param radius int - ball radius.
     * @return Point - ball center point.
     */
    public static Point getRandomCenter(int startX, int startY, int endX, int endY, int radius) {
        Random rand = new Random();
        double x = startX + radius + rand.nextInt(endX - startX - radius * 2);
        double y = startY + radius + rand.nextInt(endY - startY - radius * 2);
        return new Point(x, y);
    }

    /**
     * according to the size of the ball we set a velocity to it:
     * smaller balls move faster while bigger balls move slower (min velocity is shared by all
     * balls with radius larger than 50).
     * values are generated randomly.
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
     * generate random color for ball out of set list of available colors.
     * @return Java.awt.Color - color of ball.
     */
    public Color getRandomColor() {
        Random rand = new Random();
        int max = this.colors.length;
        int idx = rand.nextInt(max);
        return this.colors[idx];
    }
}
