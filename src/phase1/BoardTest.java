package phase1;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void testMergeBoard() {
        Board board = new Board(20,20); 
        Gadget circleBumper = new CircleBumper(10,10); 
        Gadget absorber = new Absorber(13,13, 6, 3); 
        Gadget squareBumper = new SquareBumper(1,1); 
        Gadget flipper = new Flipper(3,3,0); 
        Gadget squareBumper2 = new SquareBumper(5,5); 
        board.addGadget(absorber);
        board.addGadget(flipper);
        board.addGadget(circleBumper);
        board.addGadget(squareBumper); 
        board.addGadget(squareBumper2);
        System.out.println(board.toString()) ;
        System.out.println(squareBumper.toString(20,20)); 
               
    }
    
    public void testCheckRep(){ 
        Board board = new Board(20,20); 
        Gadget circleBumper = new CircleBumper(10,10);
        Gadget circleBumper2 = new CircleBumper(10,10); 
        board.addGadget(circleBumper); 
        board.addGadget(circleBumper2); 
        //TODO: Assert that checkrep fails
    }

}
