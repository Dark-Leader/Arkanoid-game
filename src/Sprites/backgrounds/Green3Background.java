package Sprites.backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * represents the background of the Green3 level.
 */
public class Green3Background implements Sprite {
    private int borderSize;
    private Color background = new Color(0, 100, 0);
    private Color buildingColor = new Color(25, 25, 25);
    private Color antenaBase = new Color(35, 35, 35);
    private Color antennaColor = new Color(45, 45, 45);
    private Color lightOrange = new Color(240, 200, 160);

    /**
     * Constructor.
     * @param borderSize int - border size at edges of screen.
     */
    public Green3Background(int borderSize) {
        this.borderSize = borderSize;
    }

    /**
     * draw the background onto the screen.
     * @param d DrawSurface - draw on it.
     */
    private void drawBackground(DrawSurface d) {
        d.setColor(background);
        int width = d.getWidth();
        int height = d.getHeight();
        // background color
        d.fillRectangle(0, 0, width, height);
    }

    private void drawBuilding(DrawSurface d) {
        int height = d.getHeight();

        int space = borderSize / 5;
        int windowWidth = space * 2;
        int windowHeight = space * 4;
        int numWindowsPerRow = 5;
        int numRows = 5;

        int buildingWidth = (windowWidth + space + 1) * numWindowsPerRow;
        int buildingHeight = (windowHeight + space) * numRows;

        int buildingX = (int) (borderSize * 2.5);
        int buildingY = height - buildingHeight + windowHeight / 2;
        // draw building
        d.setColor(buildingColor);
        d.fillRectangle(buildingX, buildingY, buildingWidth, buildingHeight);

        d.setColor(Color.WHITE);
        // draw windows on building
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numWindowsPerRow; j++) {
                int curX = buildingX + space + j * (windowWidth + space);
                int curY = buildingY + space + i * (windowHeight + space);
                d.fillRectangle(curX, curY, windowWidth, windowHeight);
            }
        }

        drawAntenna(d, buildingX, buildingY, buildingWidth, windowHeight);
    }

    private void drawAntenna(DrawSurface d, int buildingX, int buildingY, int buildingWidth, int windowHeight) {
        // draw base of antenna
        d.setColor(antenaBase);
        int antennaBaseWidth = buildingWidth / 3;
        int antennaBaseHeight = windowHeight * 2;
        int antennaBaseX = buildingX + buildingWidth / 2 - antennaBaseWidth / 2;
        int antennaBaseY = buildingY - antennaBaseHeight;
        d.fillRectangle(antennaBaseX, antennaBaseY, antennaBaseWidth, antennaBaseHeight);
        // draw antenna
        d.setColor(antennaColor);
        int antennaWidth = antennaBaseWidth / 3;
        int antennaHeight = antennaBaseHeight * 6;
        int antennaX = antennaBaseX + antennaBaseWidth / 2 - antennaWidth / 2;
        int antennaY = antennaBaseY - antennaHeight;
        d.fillRectangle(antennaX, antennaY, antennaWidth, antennaHeight);

        // draw beacon
        int beaconRadius = (int) (antennaBaseWidth / 2.5);
        int beaconX = antennaX + antennaWidth / 2;
        int beaconY = antennaY;
        d.setColor(lightOrange);
        d.fillCircle(beaconX, beaconY, beaconRadius);
        beaconRadius *= 0.75;

        d.setColor(Color.RED);
        d.fillCircle(beaconX, beaconY, beaconRadius);
        beaconRadius *= 0.5;

        d.setColor(Color.WHITE);
        d.fillCircle(beaconX, beaconY, beaconRadius);
    }

    @Override
    public void drawOn(DrawSurface d) {

        drawBackground(d);
        drawBuilding(d);

    }

    @Override
    public void timePassed() {
        return;
    }
}
