import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * An Abstract Art Drawing .
 * drawing lines segments in the screen.
 *
 * @author : Hosny Ganaiem
 */
public class AbstractArtDrawing {

    /**
     * Drawing random lines-segments in ths screen.
     * using biuoop package , in particular biuoop.GUI and
     * biuoop.DrawSurface classes.
     */
    public void drawRandomLines() {
        int width = 550;
        int height = 400;
        int linesNum = 10; // lines to draw.
        Random rand = new Random(); // create a random-number generator

        /* Create a window with title "Random Lines Example"
         which is certain pixels wide and  high. */
        GUI gui = new GUI("Random Lines Example", width, height);
        DrawSurface d = gui.getDrawSurface();
        Line[] linesArr = new Line[linesNum]; //lines keeper
        for (int i = 0; i < linesNum; ++i) {
            int x1 = rand.nextInt(width) + 1; //get integer in range 1-width
            int x2 = rand.nextInt(width) + 1; //get integer in range 1-width
            int y1 = rand.nextInt(height) + 1; //get integer in range 1-height
            int y2 = rand.nextInt(height) + 1; //get integer in range 1-height
            Line line = new Line(x1, y1, x2, y2); //with (x1,y1) start point,(x2,y2) end point.
            linesArr[i] = line;
            int midX = (int) line.middle().getX(); //x-coordinate of the midPoint of the current line.
            int midY = (int) line.middle().getY(); //y-coordinate of the midPoint of the current line.

            d.setColor(Color.blue); // midPoint's color.
            d.fillCircle(midX, midY, 3); //draw the midPoint of the line with radius 3.
            d.setColor(Color.black); // line's color.
            d.drawLine(x1, y1, x2, y2); //draw the line.
        }

        /*
        drawing red-circle in the screen where every line intersect with
        other line segment (intersection point).
        by check intersection between one line and each other line in
        the array.
        */
        for (int i = 0; i < linesArr.length; i++) {
            for (int j = 0; j < linesArr.length; j++) {

                //first , checking if they intersect
                if (linesArr[i].isIntersecting(linesArr[j]) && i != j) {
                    //get the intersection point.
                    Point intersectionPoint = linesArr[i].intersectionWith(linesArr[j]);
                    int x = (int) intersectionPoint.getX();
                    int y = (int) intersectionPoint.getY();
                    d.setColor(Color.red); // intersection point's color
                    d.fillCircle(x, y, 3);  // draw the point with radius 3.
                }
            }
        }
        gui.show(d); // show the figures in the screen
    }

    /**
     * Main method: Initialize and run the AbstractArtDrawing.
     *
     * @param args : ignored.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}