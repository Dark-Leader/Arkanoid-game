package Sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * represents the background of the FinalFour level.
 */
public class FinalFourBackground implements Sprite {

    private int borderSize;
    private Color background = new Color(0, 191, 255);
    private List<Cloud> clouds = new ArrayList<>();

    /**
     * Constructor.
     * @param borderSize int - border size at the edges of the screen.
     */
    public FinalFourBackground(int borderSize) {
        this.borderSize = borderSize;
    }

    /**
     * add a cloud to the background.
     * @param x int - x coordinate of start of cloud.
     * @param y int - y coordinate of start of cloud.
     * @param r int - radius -> size the cloud.
     */
    public void addCloud(int x, int y, int r) {
        clouds.add(new Cloud(x, y, r));
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(background);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        for (Cloud c: clouds) {
            c.drawOn(d);
        }
    }

    @Override
    public void timePassed() {
        return;
    }
}
