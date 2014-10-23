package phase1;
import physics.*; 
import static org.junit.Assert.*;

import org.junit.Test;

public class AbsorberTest {

    @Test public void holdBall(){ 
        Board board = new Board(20,20); 
        Ball ball1 = new Ball(new Vect(1.25,1.25), new Vect(1,0)); 
        Absorber absorber = new Absorber(new Vect(10,10), 3, 2);
        board.addGadget(absorber); 
        board.addBall(ball1); 
        absorber.shootBall(ball1);
//        absorber.toString();
        
        
        //Test with CircleBumper
       
       
//        board.step(100); //TODO Ball goes through Bumper instead of hitting it. 
//        assertTrue(ball1.getPosition().x() > 1.25); 
        System.out.print(absorber.toString(20,20));
       

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
        @Test public void absorberHeight20Test(){ 
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
        @Test public void absorberHeight1Test(){ 
            //TODO
        }
    // Trigger Storage 
    // Trigger A ball Storage with no balls in the 
    // Trigger another BallStorage one time step later 
        @Test public void ballStorageTest(){ 
            //TODO
        }
        @Test public void multipleBallsStorageTest(){ 
            //TODO
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
