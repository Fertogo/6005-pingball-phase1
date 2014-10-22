package phase1;

import static org.junit.Assert.*;
import static phase1.Gadget.*;

import org.junit.Test;

import physics.Vect;

public class GadgetTest {

    /**
     * OuterWalls testing Strategy: 
     *      Make sure ball bounces correctly from every wall (right angle, speed etc)
     *      Partitions: 
     *          Ball bounces on left wall
     *          Ball bounces on right wall
     *          Ball bounces on top wall
     *          Ball bounces on bottom wall
     *          Ball bounces directly onto top right corner
     *          Ball bounces directly onto bottom left corner
     *          Ball bounces directly onto top left corner
     *          Ball bounces directly onto bottom right corner
     */
    
    //Tests correct bounces on left,right,top and bottom walls. 
    @Test public void testOuterWallsAllSides() {
        Board board = new Board(20,20); 
        Ball ball = new Ball(new Vect(5,5), new Vect(5,-5)); 
        board.addBall(ball);
        
        int steps = 35; 
        
        for (int i = 0; i<steps; i++) board.step(); 
        //TODO: Assert something! Final ballPosition = 5,5
    }

    //Test ball bouncing between top right and bottom left corners
    @Test public void testOuterWallsTopRightBottomLeftCorners(){ 
        Board board = new Board(20,20); 
        Ball ball = new Ball(new Vect(1,1), new Vect(-1,-1)); 
        board.addBall(ball);
        
        int steps = 45; 
        for (int i = 0; i<steps; i++) board.step(); 
        //TODO: Assert Something! 
    }
    
    //Test ball bouncing between top left and bottom right corners
    @Test public void testOuterWallsTopLeftBottomRightCorners(){ 
        Board board = new Board(20,20); 
        Ball ball = new Ball(new Vect(1,19), new Vect(-1,1)); 
        board.addBall(ball);
        
        int steps = 65; 
        for (int i = 0; i<steps; i++) board.step(); 
        //TODO: Assert Something! 
    }
}
