import Sprites.Ball;
import Geometry.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Bouncing ball animation with center and velocity.
 */
public class BouncingBallAnimation {
    /**
     * Main function - driver code.
     * @param args String[] - used to set ball position and velocity.
     */
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double dx = Double.parseDouble(args[2]);
        double dy = Double.parseDouble(args[3]);
        drawAnimation(new Point(x, y), dx, dy);
    }

    /**
     * draws the animation on to the screen.
     * @param start Point - center point of ball.
     * @param dx double - change in x coordinate speed.
     * @param dy double - change in y coordinate speed.
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        int width = 200;
        int height = 200;
        GUI gui = new GUI("title", width, height);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(width, height);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
