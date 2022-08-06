import Geometry.Line;
import Geometry.Point;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * produces image while randomly generated line segments and their intersection points, middle points.
 */
public class AbstractArtDrawing {
    private GUI gui;
    private DrawSurface drawSurface;
    private ArrayList<Line> lines;
    private int width;
    private int height;

    /**
     * constructor.
     * @param numLines int - number of lines to generate.
     */
    public AbstractArtDrawing(int numLines) {
        String title = "Abstract Art Drawing";
        width = 800;
        height = 600;
        this.gui = new GUI(title, width, height);
        this.drawSurface = this.gui.getDrawSurface();
        this.lines = new ArrayList<>();
        generateRandomLines(numLines);
    }

    /**
     * driver code.
     * @param args string[] args.
     */
    public static void main(String[] args) {
        int numLines = 10;
        AbstractArtDrawing art = new AbstractArtDrawing(numLines);
        art.updateDisplay();
    }

    /**
     * generates random lines within the frame and adds them to a list.
     * @param numLines
     */
    private void generateRandomLines(int numLines) {
        Random rand = new Random();
        for (int i = 0; i < numLines; i++) {
            double x1 = rand.nextInt(this.width);
            double y1 = rand.nextInt(this.height);
            double x2 = rand.nextInt(this.width);
            double y2 = rand.nextInt(this.height);
            Line l = new Line(x1, y1, x2, y2);
            this.lines.add(l);
        }
    }

    /**
     * prints the lines on to the display.
     * red circles indicate intersection points, blue circles indicate middle point of line segment.
     */
    public void updateDisplay() {
        this.drawSurface.setColor(Color.WHITE);
        this.drawSurface.fillRectangle(0, 0, this.width, this.height);
        for (Line line: this.lines) {
            drawLine(line);
        }
        for (int i = 0; i < this.lines.size(); i++) {
            for (int j = i + 1; j < this.lines.size(); j++) {
                Point intersection = this.lines.get(i).intersectionWith(this.lines.get(j));
                if (intersection != null) {
                    drawIntersection(intersection);
                }
            }
        }
        this.gui.show(this.drawSurface);
    }

    /**
     * draw line on surface.
     * @param line
     */
    private void drawLine(Line line) {
        int radius = 3;
        this.drawSurface.setColor(Color.BLACK);
        this.drawSurface.drawLine((int) line.start().getX(), (int) line.start().getY(),
                (int) line.end().getX(), (int) line.end().getY());
        this.drawSurface.setColor(Color.BLUE);
        Point middle = line.middle();
        this.drawSurface.drawCircle((int) middle.getX(), (int) middle.getY(), radius);
    }

    /**
     * draw intersection point between two line segments.
     * @param p Point - point of intersection.
     */
    private void drawIntersection(Point p) {
        drawSurface.setColor(Color.RED);
        int radius = 3;
        drawSurface.drawCircle((int) p.getX(), (int) p.getY(), radius);
    }
}
