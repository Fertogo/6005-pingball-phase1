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
        Ball ball = new Ball(new Vect(5,5), new Vect(10,-10),0); 
        board.addBall(ball);
        ball.setFriction(0, 0);
        board.step(200);

        //TODO: Assert something! Final ballPosition = 5,5
    }

    //Test ball bouncing between top right and bottom left corners
    @Test public void testOuterWallsTopRightBottomLeftCorners(){ 
        Board board = new Board(20,20); 
        Ball ball = new Ball(new Vect(1,1), new Vect(-10,-10),0); 
        board.addBall(ball);
        
        board.step(45); 
        //TODO: Assert Something! 
    }
    
    //Test ball bouncing between top left and bottom right corners
    @Test public void testOuterWallsTopLeftBottomRightCorners(){ 
        Board board = new Board(20,20); 
        Ball ball = new Ball(new Vect(0,19), new Vect(1,-1),0); 
        board.addBall(ball);
        board.step(65); 

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
        Ball ball = new Ball (new Vect(5,1), new Vect(0,1),0); 
        board.addBall(ball);
        
        board.step(5);
        
        assertEquals(new Vect(5,2), ball.getPosition()); 
    }
    
    //Test when the ball hits the Bottom of the Bumper
    @Test public void testSquareBumperBottomSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(5,8), new Vect(0,-1),0); 
        board.addBall(ball);
        

        board.step(3);
        
        assertEquals(new Vect(5,8), ball.getPosition()); 
    }
    
    //Test when the ball hits the Left of the Bumper
    @Test public void testSquareBumperLeftSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(1,5), new Vect(1,0),0); 
        board.addBall(ball);
        

        board.step(7);
        
        assertEquals(new Vect(2,5), ball.getPosition()); 
    }
    
    //Test when the ball hits the Right of the Bumper
    @Test public void testSquareBumperRightSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(8,5), new Vect(-1,0),0);
        board.addBall(ball);
        

        board.step(4);

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
        Ball ball = new Ball (new Vect(5,1), new Vect(0,1),0); 
        board.addBall(ball); 
        
        board.step(5); 
        assertEquals(new Vect(5,2), ball.getPosition()); 
    }
    
    //Test a ball hitting the circleBumper at an angle
    @Test public void testCircleBumperAngledCollision(){ 
        Board board = new Board(20,20); 
        Gadget circleBumper = new CircleBumper(new Vect(5,5)); 
        board.addGadget(circleBumper);
        Ball ball = new Ball (new Vect(1,1), new Vect(1,1),0); 
        board.addBall(ball); 
        
        board.step(8); 
        //assertEquals(new Vect(2,2), ball.getPosition()); 
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
    //TODO: Assert something in these tests
    //Test the walls of a Degree 0 TriangleBumper
    @Test public void testTriangleBumperHitEachWallDegree0(){ 
        Board board = new Board(20,20); 
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(new Vect(5,1), new Vect(0,1),0); 
        board.addBall(ball);
        board.step(7); 
        assertEquals(new Vect(5,2), ball.getPosition()); 
        //Should bounce back up 
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        ball.updateBall(new Vect(5,10), new Vect(0,-1));
        board.step(7); 
        assertEquals(new Vect(8,6), ball.getPosition()); 
        //Should hit the longWall and be deflected to the to the right
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        ball.updateBall(new Vect(10,5), new Vect (-1,0));
        board.step(7); 
        assertEquals(new Vect(6,8), ball.getPosition());
        //Should hit the longWall and be deflected to the bottom
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        ball.updateBall(new Vect(1,5), new Vect (1,0));
        board.step(7); 
        assertEquals(new Vect(2,5), ball.getPosition());
        //should bounce back to the left
    }
    
    @Test public void testTriangleBumperHitEachWallDegree90(){ 
        Board board = new Board(20,20); 
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        triangleBumper.rotateGadget(90);
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(new Vect(5,1), new Vect(0,1),0); 
        board.addBall(ball);
        board.step(7); 
        assertEquals(new Vect(6,1), ball.getPosition()); 
        //Should bounce back up 
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        ball.updateBall(new Vect(5,10), new Vect(0,-1));
        board.step(7); 
        assertEquals(new Vect(3,6), ball.getPosition()); 
        //Should hit the longWall and be deflected to the to the left
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        ball.updateBall(new Vect(10,5), new Vect (-1,0));
        board.step(7); 
        assertEquals(new Vect(10,6), ball.getPosition()); 
        //Should bounce back to the right
        
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        ball.updateBall(new Vect(1,5), new Vect (1,0));
        board.step(7); 
        assertEquals(new Vect(6,10), ball.getPosition()); 
        //should hit longWall and be deflected to the bottom
    }
    
    @Test public void testTriangleBumperHitEachWallDegree180(){ 
        Board board = new Board(20,20); 
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        triangleBumper.rotateGadget(180);
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(new Vect(5,1), new Vect(0,1),0); 
        board.addBall(ball);
        board.step(7); 
        assertEquals(new Vect(2,5), ball.getPosition()); 
        //Should hit longWall and be deflected to the left
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        ball.updateBall(new Vect(5,10), new Vect(0,-1));
        System.out.println(ball.getPosition());
        System.out.println(ball.getVelocity());
        System.out.println(ball.getNext()); 
        board.step(7); 
        assertEquals(new Vect(6,10), ball.getPosition()); 
        //Should bounce back to the bottom
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        ball.updateBall(new Vect(10,5), new Vect (-1,0));
        board.step(7); 
        assertEquals(new Vect(10,6), ball.getPosition()); 
        //Should bounce back to the right
        
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        ball.updateBall(new Vect(1,5), new Vect (1,0));
        board.step(7); 
        assertEquals(new Vect(6,2), ball.getPosition()); 
        //should hit longWall and be deflected up.
    }
    
    @Test public void testTriangleBumperHitEachWallDegree270(){ 
        Board board = new Board(20,20); 
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        triangleBumper.rotateGadget(270);
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(new Vect(5,1), new Vect(0,1),0); 
        board.addBall(ball);
        board.step(7); 
        assertEquals(new Vect(10,6), ball.getPosition()); 
        //Should hit longWall and be deflected to the right
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        ball.updateBall(new Vect(5,10), new Vect(0,-1));
        board.step(7); 
        assertEquals(new Vect(6,10), ball.getPosition()); 
        //Should bounce back to the bottom
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        ball.updateBall(new Vect(10,5), new Vect (-1,0));
        board.step(7); 
        assertEquals(new Vect(6,3), ball.getPosition());  
        //Should hit longWall and be deflected up
        
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        ball.updateBall(new Vect(1,5), new Vect (1,0));
        board.step(7); 
        assertEquals(new Vect(1,6), ball.getPosition()); 
        //should bounce back to the left
    }
   
    /**
     * Flipper Testing Strategy
     *      Make sure ball bounces off correctly on stable and moving flipper
     *      Partitions:
     *          Basics: 
     *              toString of horizontal and vertical flippers
     *              Trigger a horizontal and vertical flipper
     *          Hits: 
     *              Hit left stable vertical from left and right
     *              Hit left stable horizontal from up and down
     *              Hit right stable vertical from left and right
     *              Hit right stable horizontal from up and down
     *              Hit left rotating flipper
     *              Hit right rotating flipper
     */
    
    @Test public void testFlipperBasics(){ 
        Board board = new Board(20,20); 
        Gadget leftVerticalFlipper = new Flipper(new Vect(5,5),0); 
        Gadget rightVerticalFlipper = new Flipper(new Vect(10,5),1); 
        
        Gadget leftHorizontalFlipper = new Flipper(new Vect(5,10),0); 
        Gadget rightHorizontalFlipper = new Flipper(new Vect(10,10),0); 
        leftHorizontalFlipper.rotateGadget(0);
        rightHorizontalFlipper.rotateGadget(0);
        board.addGadget(leftVerticalFlipper);
        board.addGadget(rightVerticalFlipper);
        board.addGadget(leftHorizontalFlipper); 
        board.addGadget(rightHorizontalFlipper); 
        board.step(); 
        fail("Flippers did not rotate"); //TODO Implement rotateGadget
    }
    
    @Test public void testFlipperSimpleHits(){
        Board board = new Board(20,20); 
        Gadget leftVerticalFlipper = new Flipper(new Vect(5,5),0); 
        Gadget rightVerticalFlipper = new Flipper(new Vect(10,5),1);     
        Gadget leftHorizontalFlipper = new Flipper(new Vect(5,10),0); 
        Gadget rightHorizontalFlipper = new Flipper(new Vect(10,10),0); 
        leftHorizontalFlipper.rotateGadget(0);
        rightHorizontalFlipper.rotateGadget(0);
        board.addGadget(leftVerticalFlipper);
        board.addGadget(rightVerticalFlipper);
        board.addGadget(leftHorizontalFlipper); 
        board.addGadget(rightHorizontalFlipper); 
        
        //Test leftVerticalFlipper
        Ball ball = new Ball(new Vect(1,4), new Vect(1,0), 0); 
        board.addBall(ball); 
        board.step(8); 
        //TODO Assert that ball bounced off correctly
        
        //Test rightVericalFlipper
        ball.updateBall(new Vect(10,4), new Vect(-1,0)); 
        board.step(8); 
        //TODO Assert that the ball bounced off correctly
        
        //Test leftHorizontalFlipper
        ball.updateBall(new Vect(5,19), new Vect(0,-1)); 
        board.step(8); 
        //TODO Assert that the ball bounced off correctly
        
        //Test rightHorizontalFlipper
        ball.updateBall(new Vect(10,19), new Vect(0,-1)); 
        board.step(8); 
        //TODO Assert that the ball bounced off correctly 
    }
    
    @Test public void testFlipperMovingHits(){ 
        //TODO Implement this!
        fail("Tests not implemented"); 
    }
}
