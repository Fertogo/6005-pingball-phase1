package phase1;
import physics.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import physics.Vect;

public class Board {
    private List<Gadget> gadgets = new ArrayList<Gadget>(); 
    private List<Ball> balls = new ArrayList<Ball>(); 
    private OuterWalls walls; 

    private int height; 
    private int width; 
    private double gravity = 25; 
    private double timestep = .16;
    private double mu = .025;
    private double mu2 = .025;
    /**
     * Checks the representation
     */
    private void checkRep(){ 
        Set<Vect> positions = new HashSet<Vect>(); 
        for (Gadget gadget : gadgets){ 
            //No two gadgets have same x and y 

            Vect position = gadget.getPosition(); 

            assert(!positions.contains(position)); 
            positions.add(position);          
            //Gadgets are not outside board.             
            assert (position.x() <= width); 
            assert(position.y() <= height); 
        }
        for (Gadget ball: balls){ 
            Vect positionBall = ball.getPosition(); 
            assert(!positions.contains(positionBall)); 
            positions.add(positionBall); 
            assert (positionBall.x() <= width); 
            assert(positionBall.y() <= height);
        }
    }
    /**
     * Checks constructor
     * @param width
     * @param height
     */
    
    public Board(int width, int height){ 
        this.height= height+2; //Add two to compensate for walls. 
        this.width = width+2;
        walls = new OuterWalls(width,height); 
    }

    /**
     * Step every gadget in the board and print it. Check if ball is going to collide. 
     */
    public void step(){ 
        for (Ball ball : balls){ 
            
            Vect newBallPosition = ball.getNext(timestep); 
            //Check for corner collisions
            if (Math.round(newBallPosition.y()) <= 0 && Math.round(newBallPosition.x()) <= 0){
                ball.updateVelocity(ball.getVelocity().times(-1));
            }else if(Math.round(newBallPosition.y()) <= 0 && Math.round(newBallPosition.x()) >= 19){
                ball.updateVelocity(ball.getVelocity().times(-1));
            }else if (Math.round(newBallPosition.y()) >= 19 && Math.round(newBallPosition.x()) <= 0){
                ball.updateVelocity(ball.getVelocity().times(-1));
            }else if (Math.round(newBallPosition.y()) >= 19 && Math.round(newBallPosition.x()) >= 19){
                ball.updateVelocity(ball.getVelocity().times(-1));
            }
            
            //Checks for wall collisions
            else{

                if (Math.round(newBallPosition.x()) <= 0) { 
                    //Left wall collision
                    //System.out.println("Hit Left Wall"); 
                    walls.collision(ball, 3);

                }
                else if (Math.round(newBallPosition.x()) >= width-2){ 
                    //Right wall collision
                    //System.out.println("Hit Right Wall"); 
                    walls.collision(ball, 1);
                    break; 
                }

                if (Math.round(newBallPosition.y()) <= 0) { 
                    //Top Wall collision
                    //System.out.println("Hit Top Wall"); 
                    walls.collision(ball, 0);
                }

                else if (Math.round(newBallPosition.y()) >= height-2){ 
                    //Bottom Wall collision
                    //System.out.println("Hit Bottom Wall"); 
                    walls.collision(ball, 2);           
                }
            }

            //Check for collisions with other gadgets

            for (Gadget gadget : gadgets){ 
                double timeToCollision = gadget.timeToCollision(ball);
                if(timeToCollision < timestep) {
                    gadget.collision(ball); 
                } 
            }
            //Checks for collisions with other balls


            Iterator<Ball> it = balls.iterator();
            while(it.hasNext()){
                Ball nextBall = it.next();
                if(ball.equals(nextBall)){continue;}     
                double timeToCollision = nextBall.timeToCollision(ball);
                if(timeToCollision < timestep) {
                    nextBall.collision(ball); 
                } 
            }
            



        }
        this.updateBalls(timestep);
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.toString()); //Print the board. 
    }

    /**
     * Steps the board mulipletimes
     * @param steps: numper of times to step the board
     */
    public void step(int steps){ 
        for (int i=0; i<steps; i++) step();  
    }
    /**
     * updates Balls on the board
     * @param time
     */
    private void updateBalls(double time){
        for(Ball ball: balls){
            ball.updatePosition(ball.getPosition().plus(ball.getVelocity().times(time)));
            ball.updateVelocity(ball.getVelocity().plus(Vect.Y_HAT.times(time*gravity)));
            double delta = 1 - mu*time - mu2*ball.getVelocity().length()*time;
            ball.updateVelocity(ball.getVelocity().times(delta));
        }
    }
    /**
     * Adds gadget to the board
     * @param gadget
     */
    public void addGadget(Gadget gadget){ 
        //Add gadget to board. 
        this.gadgets.add(gadget); 
        checkRep();
    }
    /**
     * Adds ball to the board
     * @param ball
     */
    public void addBall(Ball ball){ 
        this.balls.add(ball); 
        checkRep(); 
    }
    /**
     * runs game
     */
    public void run(){ 
        while (true) this.step(); 
    }

    /**
     * 
     * @param x - x position in board
     * @param y - y position in board
     * @return - A char representation of the topmost character when layers are merged. 
     */
    private char mergeBoard(int x, int y){ 
        List<String> layers = new ArrayList<String>(); 
        for (Gadget gadget : this.gadgets){ 
            layers.add(gadget.toString(this.width,this.height)); 
        }
        for (Gadget ball: this.balls){ 
            layers.add(ball.toString(this.width, this.height));
        }

        for (String layer : layers){ 
            String[] lines = layer.split("\n"); 
            if (lines[y].charAt(x) == ' '){ 
                continue; 
            }
            else return lines[y].charAt(x); 
        }
        return ' '; 
    }
    /**
     * To String method
     * Represents board 
     */
    @Override
    public String toString(){


        //Merge Layers
        StringBuilder board = new StringBuilder(""); 
        for (int y=0; y < height; y++){ 
            for (int x=0; x<width; x++){        
                //Current position = x,y 
                board.append(mergeBoard(x,y)); 
            }
            board.append("\n"); 
        }


        return board.toString(); 
    }
    /**
     * Returns gravity
     * @return  gravity 
     */
    public double getGravity() {
        return gravity;
    }
    /**
     * Sets the friction 
     * @param mu - of object one 
     * @param mu2- of object two
     */
    public void setFriction(double mu, double mu2){
        this.mu = mu;
        this.mu2 = mu2;
    }
    /**
     * Sets the gravity 
     * @param gravity
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
    /**
     * Sets the friction 
     * @param friction
     */
    public void setFriction(double friction){ 
        this.mu = friction; 
        this.mu2 = friction; 
    }
}
