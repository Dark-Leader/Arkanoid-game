package Sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * represents a cloud to be drawn as background of a game level.
 */
public class Cloud implements Sprite {

    private int x;
    private int y;
    private int radius;

    private Color gray = new Color(204, 204, 204);
    private Color middleGray = new Color(187, 187, 187);
    private Color darkGray = new Color(170, 170, 170);

    /**
     * Constructor.
     * @param x int - start x coordinate of cloud.
     * @param y int - start y coordinate of cloud.
     * @param radius int - radius of circles that represents the cloud.
     */
    public Cloud(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
     * is is just background so it does nothing.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * draw rain from the cloud.
     * @param d DrawSurface - draw on it.
     */
    private void drawRain(DrawSurface d) {
        int height = d.getHeight();
        // draw rain
        double angle = 15;
        double angleRadians = Math.toRadians(angle);
        double adjacent = height - y;
        double hypotenuse = adjacent / Math.cos(angleRadians);
        double opposite = hypotenuse * Math.abs(angleRadians);

        d.setColor(Color.WHITE);

        int x2Base = x - (int) opposite;
        int x1Base = x;
        int y1Base = y;
        int y2Base = height;
        int numLines = 10;
        int space = radius / 4;
        for (int i = 0; i < numLines; i++) {
            d.drawLine(x1Base - i * space, y1Base, x2Base - i * space, y2Base);
        }
    }

    /**
     * draw the cloud.
     * @param d DrawSurface - draw on it.
     */
    private void drawCloud(DrawSurface d) {
        d.setColor(gray);
        d.fillCircle((int) (x - radius * 2.25), y - radius * 2 / 3, radius * 2 / 3);
        d.fillCircle(x - radius * 2, y + radius / 2, radius * 2 / 3);

        d.setColor(middleGray);
        d.fillCircle((int) (x - radius * 1.25), y - radius / 5, radius);

        d.setColor(darkGray);
        d.fillCircle(x, y, radius);
        d.fillCircle(x - radius / 2, y + radius / 2, radius * 2 / 3);
    }

    /**
     * draw the entire cloud onto the DrawSurface provided.
     * @param d DrawSurface - to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {

        drawRain(d);
        drawCloud(d);

    }
}
