import biuoop.DrawSurface;
import biuoop.GUI;

import java.util.Random;
import java.awt.Color;

/**
 * A Multiple Frames Bouncing  Balls Animation.
 * balls bouncing in two frames(rectangles).
 *
 * @author : Ganaiem Hosny
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * Main method of MultipleFramesBouncingBallsAnimation.
     * creating a new GUI object create a screen with dimensions,
     * drawing balls with certain sizes getting from the main
     * arguments, the balls get their velocity according to their
     * size ,big size=>less velocity ,small size=>big velocity.
     * the balls bouncing in two frames , yellow and grey rectangle
     * with different dimensions:
     * a grey rectangle from (50,50) to (500,500).
     * a yellow rectangle from (450,450) to (600,600)
     * the first half of the balls bounce inside the first frame,
     * and the second half of the balls bounce inside the second frame
     *
     * @param args : every argument represent ball's size , first
     *             half bounce in gray rectangle and the second
     *             half in the yellow rectangle.(even number of args).
     */
    public static void main(String[] args) {
        int width = 700;
        int height = 700;
        int x1 = 50, y1 = 50, x2 = 500, y2 = 500, width1 = x2 - x1, height1 = y2 - y1; //first frame dimensions
        int x3 = 450, y3 = 450, x4 = 600, y4 = 600, width2 = x4 - x3, height2 = y4 - y3; //second frame dimensions.
        Random rand = new Random(); // create a random-number generator.
        //create a window.
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", width, height);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        //there is no arguments
        if (args.length == 0) {
            System.out.println("there is no balls to draw!");
            return;
        }
        //an odd number of balls
        if ((args.length) % 2 == 1) {
            System.out.println("please put an even number of balls .");
            return;
        }
        Ball[] ballInFrame1 = new Ball[args.length / 2]; //keeper array for the first half.
        Ball[] ballInFrame2 = new Ball[args.length / 2]; //keeper array for the second half.

        //creating a balls in the first frame(grey rectangle).
        for (int i = 0; i < ballInFrame1.length; i++) {
            int x = rand.nextInt((x2 - x1)) + x1; //in range of the grey rectangle.
            int y = rand.nextInt((y2 - y1)) + y1; //in range of the grey rectangle.
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            //create a new random colour by creating random primary colours.
            Color randomColor = new Color(r, g, b);
            //creating the ball with appropriate characteristics.
            ballInFrame1[i] = new Ball(x, y, Integer.parseInt(args[i]), randomColor);
            //set the boundaries of the screen to the ball.
            ballInFrame1[i].setBoundaries(y1, y2, x2, x1);
        }

        //creating a balls in the first frame(yellow rectangle).
        for (int i = 0; i < ballInFrame2.length; i++) {
            int x = rand.nextInt((x4 - x3)) + x3; //in range of the yellow rectangle.
            int y = rand.nextInt((y4 - y3)) + y3; //in range of the yellow rectangle.
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            //create a new random colour by creating random primary colours.
            Color randomColor = new Color(r, g, b);
            //creating the ball with appropriate characteristics.
            ballInFrame2[i] = new Ball(x, y, Integer.parseInt(args[i]), randomColor);
            //set the boundaries of the screen to the ball.
            ballInFrame2[i].setBoundaries(y3, y4, x4, x3);
        }

        //sorting the balls in the first frame  according to their size, from bigger to smaller.
        for (int i = 0; i < ballInFrame1.length; i++) {
            for (int j = i + 1; j < ballInFrame1.length; j++) {
                if (ballInFrame1[i].getSize() < ballInFrame1[j].getSize()) {
                    Ball tempBall = ballInFrame1[i];
                    ballInFrame1[i] = ballInFrame1[j];
                    ballInFrame1[j] = tempBall;
                }
            }
        }

        //sorting the balls in the second frame  according to their size, from bigger to smaller.
        for (int i = 0; i < ballInFrame2.length; i++) {
            for (int j = i + 1; j < ballInFrame2.length; j++) {
                if (ballInFrame2[i].getSize() < ballInFrame2[j].getSize()) {
                    Ball tempBall = ballInFrame2[i];
                    ballInFrame2[i] = ballInFrame2[j];
                    ballInFrame2[j] = tempBall;
                }
            }
        }

        //setting the minimal velocity.
        Velocity minimalVelocity = new Velocity(3, 3);
        //the big ball in each frame will start in the minimal velocity.
        ballInFrame1[0].setVelocity(minimalVelocity);
        ballInFrame2[0].setVelocity(minimalVelocity);

        /*
        if the ball smaller , will get big velocity from it's former ball.
        every balls that their size more than 50 have the same velocity and
        that's the bigger's velocity.
        every two balls have the same size , will get the same velocity.
         */
        for (int i = 1; i < ballInFrame1.length; i++) {
            if (ballInFrame1[i].getSize() >= 50) {
                ballInFrame1[i].setVelocity(ballInFrame1[0].getVelocity());
                continue;
            }
            if (ballInFrame1[i].getSize() == ballInFrame1[i - 1].getSize()) {
                ballInFrame1[i].setVelocity(ballInFrame1[i - 1].getVelocity());
                continue;
            }
            //former ball's velocity+1<dx=dy<4
            int dx = rand.nextInt(4) + (int) ballInFrame1[i - 1].getVelocity().getDx() + 1;
            int dy = dx; //symmetrical change in y and x axes.
            ballInFrame1[i].setVelocity(dx, dy);
        }

        /*
        if the ball smaller , will get big velocity from it's former ball.
        every balls that their size more than 50 have the same velocity and
        that's the bigger's velocity.
        every two balls have the same size , will get the same velocity.
         */
        for (int i = 1; i < ballInFrame2.length; i++) {
            if (ballInFrame2[i].getSize() >= 50) {
                ballInFrame2[i].setVelocity(ballInFrame2[0].getVelocity());
                continue;
            }
            if (ballInFrame2[i].getSize() == ballInFrame2[i - 1].getSize()) {
                ballInFrame2[i].setVelocity(ballInFrame2[i - 1].getVelocity());
                continue;
            }
            //former ball's velocity+1<dx=dy<4
            int dx = rand.nextInt(4) + (int) ballInFrame2[i - 1].getVelocity().getDx() + 1;
            int dy = dx; //symmetrical change in y and x axes.
            ballInFrame2[i].setVelocity(dx, dy);
        }

        //draw the balls on the screen and moving according to their velocity.
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            //draw the first frame(grey rectangle) on the screen.
            d.setColor(Color.darkGray);
            d.fillRectangle(x1, y1, width1, height1);
            //draw the first frame(yellow rectangle) on the screen.
            d.setColor(Color.yellow);
            d.fillRectangle(y3, y3, width2, height2);
            for (int i = 0; i < args.length / 2; i++) { //balls moving.
                ballInFrame1[i].moveOneStep();
                ballInFrame1[i].drawOn(d);
                ballInFrame2[i].moveOneStep();
                ballInFrame2[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}