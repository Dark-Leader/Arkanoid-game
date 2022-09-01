package Sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;
import java.awt.Color;

/**
 * represents the background of the DirectHit level.
 */
public class DirectHitBackground implements Sprite {
    private int borderSize;

    /**
     * Constructor.
     * @param borderSize int - border size for the game level on the screen.
     */
    public DirectHitBackground(int borderSize) {
        this.borderSize = borderSize;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int width = d.getWidth();
        int height = d.getHeight();
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, width, height);

        int x = width / 2;
        int y = borderSize * 7;
        d.setColor(Color.BLUE);
        d.drawCircle(x, y, 50);
        d.drawCircle(x, y, 100);
        d.drawCircle(x, y, 150);

        d.drawLine(x - borderSize * 5, y, x - borderSize, y); // horizontal left


        d.drawLine(x + borderSize, y, x + borderSize * 5, y); // horizontal right


        d.drawLine(x, y - borderSize * 5, x, y - borderSize); // vertical top
        d.drawLine(x, y + borderSize, x, y + borderSize * 5); // vertical bottom
    }

    @Override
    public void timePassed() {
        return;
    }
}
