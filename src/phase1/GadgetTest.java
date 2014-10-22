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
    
    /**
     * SquareBumper Testing Strategy: 
     *      Make sure ball is reflected correctly when it hits bumper.
     *      Partitions:
     *          Ball hits top side of Bumper
     *          Ball hits bottom side of Bumper
     *          Ball hits right side of Bumper
     *          Ball hits left side of Bumper
     *          Ball hits a side at an angle
     *          Ball hits corners of bumper
     *      
     */
    
    //Test when the ball hits the top of the Bumper
    @Test public void testSquareBumperTopSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(5,1), new Vect(0,1)); 
        board.addBall(ball);
        
        int steps = 5; 
        for (int i=1; i<steps; i++){ 
            board.step();
        }
        assertEquals(new Vect(5,3), ball.getPosition()); 
    }
    
    //Test when the ball hits the Bottom of the Bumper
    @Test public void testSquareBumperBottomSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(5,8), new Vect(0,-1)); 
        board.addBall(ball);
        
        int steps = 3; 
        for (int i=1; i<=steps; i++){ 
            board.step();
        }
        assertEquals(new Vect(5,8), ball.getPosition()); 
    }
    
    //Test when the ball hits the Left of the Bumper
    @Test public void testSquareBumperLeftSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(1,5), new Vect(1,0)); 
        board.addBall(ball);
        
        int steps = 7; 
        for (int i=1; i<=steps; i++){ 
            board.step();
        }
        assertEquals(new Vect(2,5), ball.getPosition()); 
    }
    
    //Test when the ball hits the Right of the Bumper
    @Test public void testSquareBumperRightSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(8,5), new Vect(-1,0)); 
        board.addBall(ball);
        
        int steps = 4; 
        for (int i=1; i<=steps; i++){ 
            board.step();
        }
        assertEquals(new Vect(8,5), ball.getPosition()); 
    }
    
    //TODO: Test ball collision at an angle
    //TODO: Test ball collision on corners. 
}
