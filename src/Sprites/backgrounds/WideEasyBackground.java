package Sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * represents the background of the WideEasy level.
 */
public class WideEasyBackground implements Sprite {
    private int borderSize;

    /**
     * Constructor.
     * @param borderSize int - border size at edges of screen.
     */
    public WideEasyBackground(int borderSize) {
        this.borderSize = borderSize;
    }
    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void drawOn(DrawSurface d) {

        double width = d.getWidth();
        double height = d.getHeight();
        // background color
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, (int) width, (int) height);

        // draw sun

        int x = borderSize * 5;
        int y = borderSize * 5;

        Color inner = new Color(255, 255, 24);
        Color middle = new Color(236, 215, 73);
        Color outer = new Color(239, 231, 176);

        d.setColor(outer);
        int distanceBetweenLines = 10;
        int numLines = (int) width / distanceBetweenLines;
        int blocksY = (int) height * 3 / 7;
        for (int i = 0; i < numLines; i++) {
            d.drawLine(x, y, i * distanceBetweenLines, blocksY);
        }


        d.setColor(outer);
        d.fillCircle(x, y, borderSize * 2);

        d.setColor(middle);
        d.fillCircle(x, y, (int) (borderSize * 1.75));


        d.setColor(inner);
        d.fillCircle(x, y, (int) (borderSize * 1.5));



    }
}
