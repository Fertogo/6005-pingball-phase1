package phase1;
import physics.*; 
import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testMergeBoard() {
        Board board = new Board(20,20); 
<<<<<<< HEAD
        Gadget circleBumper = new CircleBumper(10,10); 
        Gadget absorber = new Absorber(13,13, 6, 3); 

        Gadget squareBumper = new SquareBumper(1,1); 
        Gadget flipper = new Flipper(3,3,0); 
        Gadget squareBumper2 = new SquareBumper(5,5); 
        board.addGadget(absorber);
        board.addGadget(flipper);
        board.addGadget(circleBumper);


        //Gadget circleBumper = new CircleBumper(10,10);
        Vect vector = new Vect(1, 1);
        Vect vector2 = new Vect(5, 5);
        Gadget squareBumper = new SquareBumper(vector); 
        Gadget squareBumper2 = new SquareBumper(vector2); 
        //board.addGadget(circleBumper);

        board.addGadget(squareBumper); 
        board.addGadget(squareBumper2);
        board.step();     
    }
    
    @Test
    public void testBouncingBall(){ 
        Board board = new Board(20,20); 
        Vect position = new Vect(5,5); 
        Vect velocity = new Vect(2,1); 


        
        Ball ball = new Ball(position, velocity); 
        
        board.addBall(ball); 
        int steps = 1000; 
        for (int i=0; i<steps; i++){ 
            board.step(); 
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("Done");
    }
    
//    public void testCheckRep(){ 
//        Board board = new Board(20,20); 
//        Gadget circleBumper = new CircleBumper(10,10);
//        Gadget circleBumper2 = new CircleBumper(10,10); 
//        board.addGadget(circleBumper); 
//        board.addGadget(circleBumper2); 
//        //TODO: Assert that checkrep fails
//    }

}
