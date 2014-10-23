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

            //            System.out.println("Rounded position x: " + Math.round(newBallPosition.x())); 
            //            System.out.println("Rounded position y: " + Math.round(newBallPosition.y()));
            //            System.out.println("Height: " + height);
            //            System.out.println("Width: " + width);
            //Check for wall collisions
            if (Math.round(newBallPosition.x()) == 0) { 
                //Left wall collision
                System.out.println("Hit Left Wall"); 
                walls.collision(ball, 3);

            }
            else if (Math.round(newBallPosition.x()) == width-2){ 
                //Right wall collision
                System.out.println("Hit Right Wall"); 

                walls.collision(ball, 1);
                break; 
            }

            if (Math.round(newBallPosition.y()) == 0) { 
                //Top Wall collision
                System.out.println("Hit Top Wall"); 

                walls.collision(ball, 0);
            }

            else if (Math.round(newBallPosition.y()) == height-2){ 
                //Bottom Wall collision
                System.out.println("Hit Bottom Wall"); 

                walls.collision(ball, 2);
            }

            //System.out.println(newBallPosition.toString());
            //Check for collisions in other gadgets
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
            //            System.out.println("Ball is allowed to move to position "+ newBallPosition.toString()); 
            //ball.updateBall(timestep);

        }
        this.updateBalls(timestep);
        System.out.println(this.toString()); //Print the board. 
    }

    /**
     * Steps the board mulipletimes
     * @param steps: numper of times to step the board
     */
    public void step(int steps){ 
        for (int i=0; i<steps; i++) step();  
    }

    private void updateBalls(double time){
        for(Ball ball: balls){
            ball.updatePosition(ball.getPosition().plus(ball.getVelocity().times(time)));
            ball.updateVelocity(ball.getVelocity().plus(Vect.Y_HAT.times(time*gravity)));
            double delta = 1 - mu*time - mu2*ball.getVelocity().length()*time;
            ball.updateVelocity(ball.getVelocity().times(delta));
        }
    }

    public void addGadget(Gadget gadget){ 
        //Add gadget to board. 
        this.gadgets.add(gadget); 
        checkRep();
    }

    public void addBall(Ball ball){ 
        this.balls.add(ball); 
        checkRep(); 
    }

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

    public double getGravity() {
        return gravity;
    }

    public void setFriction(double mu, double mu2){
        this.mu = mu;
        this.mu2 = mu2;
    }
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
    
    public void setFriction(double friction){ 
        this.mu = friction; 
        this.mu2 = friction; 
    }
}
