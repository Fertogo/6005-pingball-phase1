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
        board.setGravity(0);
        board.setFriction(0, 0);
        board.step(50);

        assertTrue(ball.getPosition().y() < 5);
    }
    
    @Test public void testOuterWallsFastBall(){ 
        Board board = new Board(20,20); 

        Ball ball = new Ball(new Vect(5,5), new Vect(0,-150)); 
        board.addBall(ball);
        board.setGravity(0);
        board.setFriction(0, 0);
        board.step(50);

        fail("Doesn't seem to work"); 
    }

    //Test ball bouncing between top right and bottom left corners
    @Test public void testOuterWallsTopRightBottomLeftCorners(){ 
        Board board = new Board(20,20); 
        Ball ball = new Ball(new Vect(1,1), new Vect(-10,-10)); 
        board.addBall(ball);
        board.setGravity(0);
        board.setFriction(0, 0);
        
        board.step(45); 
        //fail("Not correct bounces"); //TODO Fix
        //TODO: Assert Something! 
    }
    
    //Test ball bouncing between top left and bottom right corners
    @Test public void testOuterWallsTopLeftBottomRightCorners(){ 
        Board board = new Board(20,20); 
        Ball ball = new Ball(new Vect(0,19), new Vect(10,-10)); 
        board.addBall(ball);
        board.setGravity(0);
        board.setFriction(0, 0);
        board.step(65); 
        fail("Not correct bounces"); //TODO Fix

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
        Ball ball = new Ball (new Vect(5,1), new Vect(0,5)); 
        board.addBall(ball);
        board.setGravity(0);
        board.setFriction(0, 0);
        board.step(5);
        
        assertTrue(ball.getPosition().y() < 5); 
    }
    
    //Test when the ball hits the Bottom of the Bumper
    @Test public void testSquareBumperBottomSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(5,8), new Vect(0,-5)); 
        board.addBall(ball);
        board.setGravity(0);
        board.setFriction(0, 0);

        board.step(7);
        
        assertTrue(ball.getPosition().y() > 5); 
    }
    
    //Test when the ball hits the Left of the Bumper
    @Test public void testSquareBumperLeftSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(1,5), new Vect(5,0)); 
        board.addBall(ball);
        board.setGravity(0);
        board.setFriction(0, 0);

        board.step(10);
        
        assertTrue(ball.getPosition().x() < 5); 
    }
    
    //Test when the ball hits the Right of the Bumper
    @Test public void testSquareBumperRightSideHit(){ 
        Board board = new Board (20,20); 
        Gadget squareBumper = new SquareBumper(new Vect(5,5)); 
        board.addGadget(squareBumper);
        Ball ball = new Ball (new Vect(10,5), new Vect(-5,0));
        board.addBall(ball);
        board.setGravity(0);
        board.setFriction(0, 0);

        board.step(15);

        assertTrue(ball.getPosition().x() > 5); 
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
        board.setGravity(0);
        board.setFriction(0);
        Gadget circleBumper = new CircleBumper(new Vect(5,5)); 
        board.addGadget(circleBumper);
        Ball ball = new Ball (new Vect(5,1), new Vect(0,5)); 
        board.addBall(ball); 
        board.setGravity(0);
        board.setFriction(0, 0);
        board.step(7); 
        assertTrue(ball.getPosition().y() < 5);  
    }
    
    //Test a ball hitting the circleBumper at an angle
    @Test public void testCircleBumperAngledCollision(){ 
        Board board = new Board(20,20); 
        board.setGravity(0);
        board.setFriction(0);
        Gadget circleBumper = new CircleBumper(new Vect(5,5)); 
        board.addGadget(circleBumper);
        Ball ball = new Ball (new Vect(3,3), new Vect(3,3)); 
        board.addBall(ball); 
        board.setGravity(0);
        board.setFriction(0, 0);
        board.step(8); 
        assertTrue(ball.getPosition().x() < 5); 
        assertTrue(ball.getPosition().y() < 5); 
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
        assertTrue(board.toString().contains("/"));
        triangleBumper.rotateGadget(90);
        assertTrue(board.toString().contains("\\"));
        triangleBumper.rotateGadget(90); 
        assertTrue(board.toString().contains("/"));
        triangleBumper.rotateGadget(90); 
        assertTrue(board.toString().contains("\\"));
        triangleBumper.rotateGadget(90); 
        assertTrue(board.toString().contains("/"));
        triangleBumper.rotateGadget(180); 
        assertTrue(board.toString().contains("/"));
        triangleBumper.rotateGadget(180); 
        assertTrue(board.toString().contains("/"));
        triangleBumper.rotateGadget(360); 
        assertTrue(board.toString().contains("/"));
    }
    
    //Test the walls of a Degree 0 TriangleBumper
    @Test public void testTriangleBumperHitEachWallDegree0(){ 
        Board board = new Board(20,20); 
        board.setGravity(0); 
        board.setFriction(0); 
        
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(new Vect(5,1), new Vect(0,5)); 
        board.addBall(ball);
        board.step(10); 
        assertTrue(ball.getPosition().y() < 5); 
        //Should bounce back up 
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        ball.updateBall(new Vect(5,10), new Vect(0,-5));
        board.step(10); 
        assertTrue(ball.getPosition().x() > 5); 
        //Should hit the longWall and be deflected to the to the right
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        ball.updateBall(new Vect(10.5,5.5), new Vect (-5,0));
        board.step(10); 
        assertTrue(ball.getPosition().y() > 5);
        //Should hit the longWall and be deflected to the bottom
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        ball.updateBall(new Vect(1,5), new Vect (5,0));
        board.step(10); 
        assertTrue(ball.getPosition().x() < 5);        
        //should bounce back to the left
    }
    
    @Test public void testTriangleBumperHitEachWallDegree90(){ 
        Board board = new Board(20,20); 
        board.setGravity(0); 
        board.setFriction(0); 
        
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        triangleBumper.rotateGadget(90);
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(new Vect(5,1), new Vect(0,5)); 
        board.addBall(ball);
        board.step(10); 
        assertTrue(ball.getPosition().y() < 5);    
        //Should bounce back up 
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        ball.updateBall(new Vect(5,10), new Vect(0,-5));
        board.step(10); 
        assertTrue(ball.getPosition().x() < 5);
        //Should hit the longWall and be deflected to the to the left
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        ball.updateBall(new Vect(10,5), new Vect (-5,0));
        board.step(10); 
        assertTrue(ball.getPosition().x() > 5); 
        //Should bounce back to the right
        
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        ball.updateBall(new Vect(1,5.5), new Vect (5,0));
        board.step(10); 
        assertTrue(ball.getPosition().y() > 5);
        //should hit longWall and be deflected to the bottom
    }
    
    @Test public void testTriangleBumperHitEachWallDegree180(){ 
        Board board = new Board(20,20); 
        board.setGravity(0); 
        board.setFriction(0); 
        
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        triangleBumper.rotateGadget(180);
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(new Vect(5.5,1), new Vect(0,5)); 
        board.addBall(ball);
        board.step(10); 
        assertTrue(ball.getPosition().x() < 5);
        //Should hit longWall and be deflected to the left
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        ball.updateBall(new Vect(5.5,10), new Vect(0,-5));
        System.out.println(ball.getPosition());
        System.out.println(ball.getVelocity());
        System.out.println(ball.getNext(.1)); 
        board.step(7); 
        assertTrue(ball.getPosition().y() > 5);
        //Should bounce back to the bottom
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        ball.updateBall(new Vect(10.5,5), new Vect (-5,0));
        board.step(7); 
        assertTrue(ball.getPosition().x() > 5); 
        //Should bounce back to the right
        
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        ball.updateBall(new Vect(1.5,5), new Vect (5,0));
        board.step(7); 
        assertTrue(ball.getPosition().y() < 5); 
        //should hit longWall and be deflected up.
    }
    
    @Test public void testTriangleBumperHitEachWallDegree270(){ 
        Board board = new Board(20,20); 
        board.setGravity(0); 
        board.setFriction(0); 
        
        Gadget triangleBumper = new TriangleBumper(new Vect(5,5)); 
        triangleBumper.rotateGadget(270);
        board.addGadget(triangleBumper); 
        //Hit top wall
        System.out.println("Hitting Top Wall"); 
        Ball ball = new Ball(new Vect(5.5,1), new Vect(0,5)); 
        board.addBall(ball);
        board.step(7); 
        assertTrue(ball.getPosition().x() > 5); 
        //Should hit longWall and be deflected to the right
        
        //Hit Bottom Wall
        System.out.println("Hitting Bottom Wall"); 
        ball.updateBall(new Vect(5.5,10), new Vect(0,-5));
        board.step(7); 
        assertTrue(ball.getPosition().y() > 5);
        //Should bounce back to the bottom
        
        //Hit RightWall(long)
        System.out.println("Hitting Right Wall"); 
        ball.updateBall(new Vect(10.5,5), new Vect (-5,0));
        board.step(7); 
        assertTrue(ball.getPosition().y() < 5); 
        //Should hit longWall and be deflected up
        
        
        //Hit leftWall
        System.out.println("Hitting Left Wall"); 
        ball.updateBall(new Vect(1.5,5), new Vect (5,0));
        board.step(7); 
        assertTrue(ball.getPosition().x() < 5);
        //should bounce back to the left
    }
   
    /**
     * Flipper Testing Strategy
     *      Make sure ball bounces off correctly on stable and moving flipper
     *      Partitions:
     *          Basics: 
     *              toString of horizontal and vertical flippers
     *              Trigger a horizontal and vertical flipper
     *          Flips:
     *              Trigger Left Flipper
     *              Trigger Right Flipper 
     *          Hits: 
     *              Hit left stable vertical from left and right
     *              Hit left stable horizontal from up and down
     *              Hit right stable vertical from left and right
     *              Hit right stable horizontal from up and down
     *              Hit left rotating flipper
     *              Hit right rotating flipper
     *          
     */
    //Flips
        @Test public void testLeftFlipperTrigger(){ 
            //TODO
        }
        @Test public void testRightFlipperTrigger(){ 
            //TODO
        }
        
   //Hits
        @Test public void testLeftVerticalHits(){ 
            //TODO
        }
        @Test public void testRightVerticalHits(){ 
            //TODO
        }
        @Test public void testLeftHorizontalHits(){ 
            //TODO
        }
        @Test public void testRightHorizontalHits(){ 
            //TODO
        }
       
       
   //Previous Tests 
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
            board.setGravity(0);
            board.setFriction(0, 0);
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
            Ball ball = new Ball(new Vect(1,4), new Vect(1,0)); 
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
        
        /**
         * Absorber Testing Strategy
         *      Make sure ball bounces off correctly on stable and moving flipper
         *      Partitions:
         *          Basics: 
         *             Construction of Absorber of Multiple Widths and Height
         *                  Try a Width of 1, 20, 10
         *                  Try a Height of 1, 20
         *             Trigger a Ball Storage
         *                  Trigger A ball Storage with no balls in the 
         *                  Trigger another BallStorage one time step later 
         *             Trigger a Ball Release
         *                  With One Ball Already in the Absorber
         *                  With Two Balls in Absorber      
         *             
         *          Collisions 
         *             Ball Collides with Absorber from the top
         *             Ball Collides with Absorber from the bottom
         *              
         *             
         */
        //TODO
//        @Test public void holdBall(){ 
//            Board board = new Board(20,20); 
//            Ball ball1 = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
//            Absorber absorber = new Absorber(10,10, 3, 2);
//            board.addGadget(absorber); 
//            board.addBall(ball1); 
//            absorber.shootBall(ball1);
////            absorber.toString();
//            //Test with CircleBumper
////            board.step(100); //TODO Ball goes through Bumper instead of hitting it. 
////            assertTrue(ball1.getPosition().x() > 1.25); 
//            System.out.print(absorber.toString(20,20));
    //
//        }
        //Construction 
            @Test public void absorberWidthOneTest(){ 
                Board board = new Board(20,20); 
                Ball ball = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
                Absorber absorber = new Absorber(new Vect(10,10), 1, 2);
                board.addGadget(absorber); 
                board.addBall(ball); 
                assertTrue(absorber!=null);
                assertTrue(absorber.getPosition().equals(new Vect(10,10)));
                assertTrue(absorber.contains(new Vect(11,12)));
                assertFalse(absorber.contains(new Vect(10,13)));
                assertFalse(absorber.contains(new Vect(9,9)));
                assertFalse(absorber.contains(new Vect(9,12)));
               
            }
            @Test public void absorberWidth20Test(){ 
                Board board = new Board(20,20); 
                Ball ball = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
                Absorber absorber = new Absorber(new Vect(1,1), 20, 2);
                board.addGadget(absorber); 
                board.addBall(ball); 
                assertTrue(absorber!=null);
                assertTrue(absorber.getPosition().equals(new Vect(1,1)));
                assertTrue(absorber.contains(new Vect(1,1)));
                assertTrue(absorber.contains(new Vect(20,1)));
                assertTrue(absorber.contains(new Vect(10,2)));
                assertTrue(absorber.contains(new Vect(13,3)));
                assertFalse(absorber.contains(new Vect(1,4)));
                assertFalse(absorber.contains(new Vect(20,5)));
                assertFalse(absorber.contains(new Vect(10,19)));
                assertFalse(absorber.contains(new Vect(13,7)));
            }
            @Test public void absorberWidth10Test(){ 
                Board board = new Board(20,20); 
                Ball ball = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
                Absorber absorber = new Absorber(new Vect(1,1), 10, 2);
                board.addGadget(absorber); 
                board.addBall(ball); 
                assertTrue(absorber!=null);
                assertTrue(absorber.getPosition().equals(new Vect(1,1)));
                assertTrue(absorber.contains(new Vect(1,1)));
                assertFalse(absorber.contains(new Vect(20,1)));
                assertTrue(absorber.contains(new Vect(10,2)));
                assertTrue(absorber.contains(new Vect(11,3)));
                assertFalse(absorber.contains(new Vect(1,4)));
                assertFalse(absorber.contains(new Vect(20,5)));
                assertFalse(absorber.contains(new Vect(10,19)));
                assertFalse(absorber.contains(new Vect(13,7)));
            }
            @Test public void absorberWidth1Test(){ 
                Board board = new Board(20,20); 
                Ball ball = new Ball(new Vect(1.25,1.25), new Vect(1,1)); 
                Absorber absorber = new Absorber(new Vect(1,1), 1, 2);
                board.addGadget(absorber); 
                board.addBall(ball); 
                assertTrue(absorber!=null);
                assertTrue(absorber.getPosition().equals(new Vect(1,1)));
                assertTrue(absorber.contains(new Vect(1,1)));
                assertFalse(absorber.contains(new Vect(2,6)));
                assertTrue(absorber.contains(new Vect(1,2)));
                assertTrue(absorber.contains(new Vect(1,3)));
                assertFalse(absorber.contains(new Vect(12,1)));
                assertFalse(absorber.contains(new Vect(20,5)));
                assertFalse(absorber.contains(new Vect(1,19)));
                assertFalse(absorber.contains(new Vect(13,2)));
            }
            @Test public void absorberHeight20Test(){ 
                Board board = new Board(20,20); 
                Ball ball = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
                Absorber absorber = new Absorber(new Vect(1,1), 10, 20);
                board.addGadget(absorber); 
                board.addBall(ball); 
                assertTrue(absorber!=null);
                assertTrue(absorber.getPosition().equals(new Vect(1,1)));
                assertTrue(absorber.contains(new Vect(1,1)));
                assertFalse(absorber.contains(new Vect(20,1)));
                assertTrue(absorber.contains(new Vect(10,2)));
                assertTrue(absorber.contains(new Vect(11,3)));
                assertTrue(absorber.contains(new Vect(1,4)));
                assertFalse(absorber.contains(new Vect(20,20)));
                assertFalse(absorber.contains(new Vect(10,21)));
                assertTrue(absorber.contains(new Vect(10,9)));
            }
            @Test public void absorberHeight1Test(){ 
                Board board = new Board(20,20); 
                Ball ball = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
                Absorber absorber = new Absorber(new Vect(1,1), 10, 1);
                board.addGadget(absorber); 
                board.addBall(ball); 
                assertTrue(absorber!=null);
                assertTrue(absorber.getPosition().equals(new Vect(1,1)));
                assertTrue(absorber.contains(new Vect(1,1)));
                assertTrue(absorber.contains(new Vect(2,1)));
                assertTrue(absorber.contains(new Vect(10,2)));
                assertFalse(absorber.contains(new Vect(11,3)));
                assertFalse(absorber.contains(new Vect(2,4)));
                assertFalse(absorber.contains(new Vect(2,3)));
                assertFalse(absorber.contains(new Vect(3,5)));
                assertFalse(absorber.contains(new Vect(2,5)));
            }
        // Trigger Storage 
        // Trigger A ball Storage with no balls in the 
        // Trigger another BallStorage one time step later 
            @Test public void ballStorageTest(){ 
                Board board = new Board(20,20); 
                Ball ball = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
                Absorber absorber = new Absorber(new Vect(1,1), 10, 1);
                Absorber absorber2 = new Absorber(new Vect(1,6), 12, 8);
                board.addGadget(absorber); 
                board.addBall(ball); 
                assertTrue(absorber!=null);
                assertTrue(absorber.getPosition().equals(new Vect(1,1)));
                absorber.storeBall(ball);
                assertTrue(ball.getPosition().x()>=8 && ball.getPosition().x()<=9 );
                absorber2.storeBall(ball);
                assertTrue(ball.getPosition().equals(new Vect(10.0, 12.0)));
            }
            @Test public void multipleBallsStorageTest(){ 
                Board board = new Board(20,20); 
                Ball ball = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
                Ball ballTwo = new Ball(new Vect(3.5,6.7), new Vect(6,7)); 
                Ball ballThree = new Ball(new Vect(4,10), new Vect(-1,-3)); 
                Absorber absorber = new Absorber(new Vect(1,1), 10, 1);
                board.addGadget(absorber); 
                board.addBall(ball);
                board.addBall(ballTwo);
                board.addBall(ballThree);
                absorber.storeBall(ball);
                absorber.storeBall(ballTwo);
                absorber.storeBall(ballThree);
                assertTrue(ball.getPosition().equals(new Vect(8.0,2.0)));
                assertTrue(ballTwo.getPosition().equals(new Vect(8.0,2.0)));
                assertTrue(ballThree.getPosition().equals(new Vect(8.0,2.0)));
                assertTrue(ball.getVelocity().equals(new Vect(0,0)));

                
            }
        
        


    

}
