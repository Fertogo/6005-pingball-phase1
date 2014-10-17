package warmup;
import java.lang.Thread; 
/**
 * TODO: put documentation for your class here
 */
public class Main {
    
    /**
     * TODO: describe your main function's command line arguments here
     */
    public static void main(String[] args) {
        //Create board
        Board pingBoard = new Board(20,20);
        //Create ball
        Ball pingBall = new Ball(10,10);
        //While (True)
        System.out.print(pingBoard.toString());
//        while(true){
//            pingBall.step(); 
//            Thread.sleep(100, 0);
//            
//        }
    }
    
}


