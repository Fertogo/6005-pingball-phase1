package phase1;

//import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * 
 * Represents a PingBall Square Bumper
 *
 */
public class SquareBumper implements Gadget {
    private Vect position;

    private LineSegment leftWall; 
    private LineSegment rightWall; 
    private LineSegment bottomWall; 
    private LineSegment topWall; 
    private List<Gadget> triggeredGadgets; 
    
    public SquareBumper(Vect position) {
        int x = (int)(position.x()); //Adding 1 to compensate for the walls
        int y = (int)(position.y()); 

        this.position = new Vect(x,y);

        
        //Create walls representing square bumper. 
        this.topWall = new LineSegment(x,y,x+1,y); 
        this.rightWall = new LineSegment(x+1,y,x+1,y+1); 
        this.bottomWall = new LineSegment(x,y+1,x+1,y+1); 
        this.leftWall = new LineSegment(x,y,x,y+1); 
    }

    @Override
    public void trigger(){ 
        //Trigger Triggered Gadgets
        for (Gadget gadget : this.triggeredGadgets){ 
            gadget.action(); 
        }
    }
    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rotateGadget(int degrees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(int height, int width) {
        
        char [][] wallArray = Gadget.getArray(height,width); 
        wallArray[(int) this.position.y()+1][(int) this.position.x()+1] = '#';
        
        String boardToString = "";
        for(int i=0; i<wallArray.length;i++){
            for(int j=0; j< wallArray[0].length;j++){
            boardToString += Character.toString(wallArray[i][j]);
            }
            boardToString += "\n";
        }
        
        return boardToString;
    }

    @Override
    public Vect getPosition() {
        return this.position;
    }

    @Override
    public Vect getNext(double time) {
        return this.position;
    }

    @Override
    public void collision(Ball ball) {
        System.out.println("Ball collision with Square Bumper"); 
        Vect currentBallVelocity = ball.getVelocity(); 
        Vect currentBallPosition = ball.getPosition(); 
        Vect newBallVelocity = new Vect(0,0); 
        double ballX = currentBallPosition.x(); 
        double ballY = currentBallPosition.y(); 
        //Check Corners
        if (ballX > this.position.x() + 1 && ballY > this.position.y()){ 
            //Hitting the top right corner of Bumper
            System.out.println("Hit TopRight corner of Square Bumper"); 

            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX < this.position.x() && ballY > this.position.y()){ 
            //Hitting the top left corner of Bumper
            System.out.println("Hit TopLeft corner of Square Bumper"); 
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX < this.position.x() && ballY > this.position.y()+1){ 
            //Hitting the bottom left corner of Bumper
            System.out.println("Hit BottomLeft corner of Square Bumper"); 
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        else if (ballX > this.position.x()+1 && ballY > this.position.y()+1){ 
            //Hitting the bottom right corner of Bumper
            System.out.println("Hit BottomRight corner of Square Bumper"); 
            newBallVelocity = new Vect(currentBallVelocity.x()*-1, currentBallVelocity.y()*-1); //Reflect Ball (Turn around)
        }
        //Check which wall was hit
        else if(ballY < this.position.y()){ 
            //Hitting top wall
            System.out.println("Hit Top Wall of Square Bumper"); 
            newBallVelocity = Geometry.reflectWall(topWall, currentBallVelocity);
        }
        else if(ballY > this.position.y()+1){ 
            //Hitting bottom wall
            System.out.println("Hit Bottom Wall of Square Bumper"); 
            newBallVelocity = Geometry.reflectWall(bottomWall, currentBallVelocity);
        }
        else if(ballX > this.position.x()+1){ 
            //Hitting right wall
            System.out.println("Hit Right Wall of Square Bumper"); 
            newBallVelocity = Geometry.reflectWall(rightWall, currentBallVelocity);
        }
        else if(ballX < this.position.x()){ 
            //Hitting left wall
            System.out.println("Hit Left Wall of Square Bumper"); 
            newBallVelocity = Geometry.reflectWall(leftWall, currentBallVelocity);
        }

        ball.updateBall(currentBallPosition, newBallVelocity); 
        
        this.trigger(); 
    }

   

    @Override

    public double timeToCollision(Ball ball) {
        ArrayList<LineSegment> bumperWalls = new ArrayList<LineSegment>();
        bumperWalls.addAll(Arrays.asList(topWall,rightWall,bottomWall,leftWall));
        double timeToWallCollision = Double.POSITIVE_INFINITY;
        
        for(LineSegment l: bumperWalls){
            double minimumTime = Geometry.timeUntilWallCollision(l, ball.ballReturnCircle(), ball.getVelocity());
            if(minimumTime < timeToWallCollision){
                timeToWallCollision = minimumTime;
            }
        }
        return timeToWallCollision;
    }

    @Override
    public void addTriggeredGadget(Gadget triggeredGadget) {
        this.triggeredGadgets.add(triggeredGadget); 
    }
}
