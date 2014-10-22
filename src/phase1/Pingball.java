package phase1;

import physics.Vect;

public class Pingball {
    public static void main(String[] boardname){ 
        Board board; 
        if (boardname.length == 0)  board = defaultBoard(); 
        else{ 
            switch(boardname[0]){ 
            case("absorber"): board = absorberBoard(); 
            case("flippers"): board = flipperBoard(); 
            }
            
        while (true){ 
            board.step(); 
        }
        
        }
        
    }
    
    public static Board defaultBoard(){ 
        Board board = new Board(20,20); 
        Ball ball = new Ball(new Vect(1.25,1.25));
        board.addBall(ball); 
        
        Gadget circleBumper = new CircleBumper(new Vect(1,10));
        board.addGadget(circleBumper);
        
        Gadget triangleBumper = new TriangleBumper(new Vect(12,15), 180); //TODO: How to call this correctly
        board.addGadget(triangleBumper); 
        
        Gadget squareBumper1 = new SquareBumper(new Vect(0,17));
        Gadget squareBumper2 = new SquareBumper(new Vect(1,17));
        Gadget squareBumper3 = new SquareBumper(new Vect(2,17));
        board.addGadget(squareBumper1); 
        board.addGadget(squareBumper2); 
        board.addGadget(squareBumper3); 
        
        Gadget circleBumper1 = new CircleBumper(new Vect(7,18));
        Gadget circleBumper2 = new CircleBumper(new Vect(8,18));
        Gadget circleBumper3 = new CircleBumper(new Vect(9,18));
        board.addGadget(circleBumper1); 
        board.addGadget(circleBumper2); 
        board.addGadget(circleBumper3); 
        
        return board; 
    }
    
    public static Board absorberBoard(){ 
        Board board = new Board(20,20); 
        
        Ball ball1 = new Ball(new Vect(10.25, 15.25)); 
        Ball ball2 = new Ball(new Vect(19.25,3.25));
        Ball ball3 = new Ball(new Vect(1.25,5.25));
        board.addBall(ball1); 
        board.addBall(ball2); 
        board.addBall(ball3); 
        
        Gadget absorber = new Absorber(new Vect (0,18), 20, 2) ;//TODO: How to call this correctly
        board.addGadget(absorber); 
        
        Gadget triangleBumper = new TriangleBumper(new Vect(19,0), 90); //TODO: How to call this correctly
        board.addGadget(triangleBumper);
        
        Gadget circleBumper1 = new CircleBumper(new Vect(1,10));
        Gadget circleBumper2 = new CircleBumper(new Vect(2,10));
        Gadget circleBumper3 = new CircleBumper(new Vect(3,10));
        Gadget circleBumper4 = new CircleBumper(new Vect(4,10));
        Gadget circleBumper5 = new CircleBumper(new Vect(5,10));
        board.addGadget(circleBumper1);
        board.addGadget(circleBumper2);
        board.addGadget(circleBumper3);
        board.addGadget(circleBumper4);
        board.addGadget(circleBumper5);

        return board; 
    }
    
    public static Board flipperBoard(){ 
        Board board = new Board(20,20); 
        
        Ball ball1 = new Ball(new Vect(0.25, 3.25)); 
        Ball ball2 = new Ball(new Vect(5.25,3.25));
        Ball ball3 = new Ball(new Vect(10.25,3.25));
        Ball ball4 = new Ball(new Vect(15.25, 3.25)); 
        Ball ball5 = new Ball(new Vect(19.25,3.25))
        board.addBall(ball1); 
        board.addBall(ball2); 
        board.addBall(ball3); 
        board.addBall(ball4); 
        board.addBall(ball5); 
        
        Gadget leftFlipper1 = new Flipper (new Vect(0,8), 0); 
        //TODO: Finish this (I'm running out of batter"

       
    }
}
