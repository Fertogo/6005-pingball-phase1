package phase1;
import physics.*; 
import static org.junit.Assert.*;

import org.junit.Test;

public class AbsorberTest {

    @Test public void holdBall(){ 
        Board board = new Board(20,20); 
        Ball ball1 = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
        Absorber absorber = new Absorber(new Vect(0,16), 20, 4);
        board.addGadget(absorber); 
        board.addBall(ball1); 
        
        
        //Test with CircleBumper
       
       
//        board.step(100); //TODO Ball goes through Bumper instead of hitting it. 
//        assertTrue(ball1.getPosition().x() > 1.25); 
        System.out.print(board.toString());
       

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
//    @Test public void holdBall(){ 
//        Board board = new Board(20,20); 
//        Ball ball1 = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
//        Absorber absorber = new Absorber(10,10, 3, 2);
//        board.addGadget(absorber); 
//        board.addBall(ball1); 
//        absorber.shootBall(ball1);
////        absorber.toString();
//        //Test with CircleBumper
////        board.step(100); //TODO Ball goes through Bumper instead of hitting it. 
////        assertTrue(ball1.getPosition().x() > 1.25); 
//        System.out.print(absorber.toString(20,20));
//
//    }
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
//        Collisions 
//                Ball Collides with Absorber from the top
//                Ball Collides with Absorber from the bottom
        @Test public void ballCollideTopOfAbsorberTest(){ 
            //TODO
        }
        @Test public void ballCollideBottomOfAbsorberTest(){ 
            //TODO
        }
  

}
