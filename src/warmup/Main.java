package warmup;
import java.lang.Thread; 

import physics.Vect;
/**
 * TODO: put documentation for your class here
 */
public class Main {
    
    /**
     * TODO: describe your main function's command line arguments here
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
        //Create board
        Board pingBoard = new Board(20,20);
        //Create ball
        Vect velocity = new Vect(3.0, 3.0);
        Ball pingBall = new Ball(1,10, velocity);

        //While (True)
        System.out.print(pingBoard.toString());
        while(true){
            pingBall.step(pingBoard);
            System.out.print(pingBoard.toString());
            Thread.sleep(100, 0);
            
        }
    }
    
}


