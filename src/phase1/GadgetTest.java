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
    
    /**
     * Circle Bumper Testing Strategy
     *      Make sure that ball is reflected at the correct angle
     *      Partitions: 
     *          Ball hits circle at straight angle
     *          Ball hits circle at angle
     *          
     */
    
    //Test a ball hitting the circleBumper at a straight angle
    @Test public void testCircleBumperDirectCollision(){ 
        Board board = new Board(20,20); 
        Gadget circleBumper = new CircleBumper(new Vect(5,5)); 
        board.addGadget(circleBumper);
        Ball ball = new Ball (new Vect(5,1), new Vect(0,1)); 
        board.addBall(ball); 
        
        int steps = 5; 
        for (int i=1; i<=steps; i++){ 
            board.step();
        }
        assertEquals(new Vect(5,2), ball.getPosition()); 
    }
    
    //Test a ball hitting the circleBumper at an angle
    @Test public void testCircleBumperAngledCollision(){ 
        Board board = new Board(20,20); 
        Gadget circleBumper = new CircleBumper(new Vect(5,5)); 
        board.addGadget(circleBumper);
        Ball ball = new Ball (new Vect(1,1), new Vect(1,1)); 
        board.addBall(ball); 
        
        int steps = 5; 
        for (int i=1; i<=steps; i++){ 
            board.step();
        }
        assertEquals(new Vect(2,2), ball.getPosition()); 
    }
    
    /**
     * Triangle Bumper Testing Strategy
     *      Make sure that rotateGadget works correctly. 
     *      Make sure that the ball is reflected at the correct angle
     *      Partitions:
     *         Rotate a triangle four times by 90 degrees
     *         Hit both of the straight walls 
     *         Hit the slanted wall (hypotenous)
     *         Try with different triangle orientations. 
     *         
     */
    
    //Test rotateGadget()
    @Test public void testTriangleBumperRotateGadget(){ 
        Board board = new Board(20,20); 
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        board.addGadget(triangleBumper);
        board.step();
        triangleBumper.rotateGadget(90);
        board.step(); 
        triangleBumper.rotateGadget(90); 
        board.step();
        triangleBumper.rotateGadget(90); 
        board.step();
        triangleBumper.rotateGadget(90); 
        board.step();
        triangleBumper.rotateGadget(180); 
        board.step();
        triangleBumper.rotateGadget(180); 
        board.step();
        triangleBumper.rotateGadget(360); 
        board.step();
        //TODO: Assert Something
    }
    
    @Test public void testTriangleBumperRotateGadgetInvalidDegree(){ 
        Board board = new Board(20,20); 
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        board.addGadget(triangleBumper);
        triangleBumper.rotateGadget(91);
        board.step(); 
        //This should fail checkRep()
        //TODO: Assert ^
    }
   
    
}
