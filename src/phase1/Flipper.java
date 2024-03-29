package phase1;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import physics.*;

/*
 * String Representation: | or --
 *                        |
 * Size and Shape: Rectangular rotating shape with bounding box 2Lx2L
 * 
 * Orientation: 
 * for left- 0degrees, for pivot point in northwest corner, counterclockwise
 * right   - pivot point in northeast corner, clockwise 
 * 
 * Coeff of Reflection: .95
 * Trigger - when ball hits it
 * Action-rotates 90 degrees, angular velocity of 1080; account for the linear 
 * velocity of the part that contacts the ball
 * 
 */


public class Flipper implements Gadget{
    private Vect position; 
    //Int type  representing left(0) or right flipper(1)
    private int flipperType; 
    //    private final String [] typesOfFlipper = {"left", "right"} ;
    private boolean isHorizontal;
    private LineSegment lineSegment; 
    private Vect pivotPoint; 
    private Angle angle;
    private List<Gadget> triggeredGadgets = new ArrayList<Gadget>(); 

    
    /**
     * Constructor
     * @param x
     * @param y
     * @param type
     */
    public Flipper(Vect position, int type){ 
        this.position = position; 
        this.pivotPoint= new Vect(position.x(),position.y());
        this.lineSegment= new LineSegment(position.x(),position.y(), position.x(), position.y()-1);
//        this.flipperType=typesOfFlipper[type];
        this.flipperType=type;
        this.angle= new Angle(3/2*Math.PI);
        this.isHorizontal=false;
    }
    /**
     * Constructs the flipper
     * @param position- position 
     * @param type- 0 for left flipper; 1 for right flipper
     * @param isHorizontal- initial position 
     */
    public Flipper(Vect position, int type, boolean isHorizontal){ 
        this.position = position; 
        this.pivotPoint= new Vect(position.x(),position.y());
        this.lineSegment= new LineSegment(position.x(),position.y(), position.x(), position.y()-1);
//        this.flipperType=typesOfFlipper[type];
        this.flipperType=type;
        this.angle= new Angle(3/2*Math.PI);
        this.isHorizontal=isHorizontal;
    }
    /**
     * Trigger for gadget
     */
    @Override 
    public void trigger(){ 
        //Trigger Triggered Gadgets
        for (Gadget gadget : this.triggeredGadgets){ 
            gadget.action(); 
        }
    }
    
    /**
     * Method that returns a ball that has been hit by a flipper
     * @param ball
     * @return
     */
    public void hitBall(Ball ball){
        Ball newBall=ball;
        
        if(!isHorizontal){
            //left  --CounterClockwise 90
            if(this.flipperType ==0){ //Rotate wall at 1080 radians per second
                    newBall =new Ball(ball.getPosition(),
                            Geometry.reflectRotatingWall(lineSegment,pivotPoint, 1080.*Math.PI/180., 
                                    ball.ballReturnCircle(), ball.getVelocity()));
            }
            //right  -- Clockwise 90
            if(this.flipperType ==1){
                newBall =new Ball(ball.getPosition(), 
                        Geometry.reflectRotatingWall(lineSegment,pivotPoint, -1080.*Math.PI/180., 
                                ball.ballReturnCircle(), ball.getVelocity()));
            }
        }else if(isHorizontal){
            //left  -- Clockwise
            if(this.flipperType ==0){
                newBall =new Ball(ball.getPosition(), 
                        Geometry.reflectRotatingWall(lineSegment,pivotPoint, -1080.*Math.PI/180.,
                                ball.ballReturnCircle(), ball.getVelocity()));
            }
            //right  -- CounterClockwise
            if(this.flipperType ==1){
                newBall = new Ball(ball.getPosition(), 
                        Geometry.reflectRotatingWall(lineSegment,pivotPoint, 1080.*Math.PI/180., 
                                ball.ballReturnCircle(), ball.getVelocity()));
            }
        }
           ball.updateBall(newBall.getPosition(), newBall.getVelocity());
           this.flipFlipper(); 

    }

    
    /**
     * Switches the state of the flipper 
     */
    @Override
    public void action() {
       this.flipFlipper(); 
    }
    
    //Orientation
    /**
     * Rotates flipper
     * @param degrees 0-360
     */
    public void flipFlipper(){
        //Right Flipper always clockwise rotations 
        //Left counterclockwise rotations 
        if(!isHorizontal){
            //left  --CounterClockwise 90
            if(this.flipperType ==0){
                    this.angle = new Angle(0);
            }
            //right  -- Clockwise 90
            if(this.flipperType ==1){
                   this.angle = new Angle(Math.PI);
            }
        }else if(isHorizontal){
            //left  -- Clockwise
            if(this.flipperType ==0){
                this.angle = new Angle(3/2*Math.PI);
            }
            //right  -- CounterClockwise
            if(this.flipperType ==1){
                this.angle = new Angle(3/2*Math.PI);
            }
        }
        
        if(isHorizontal){
            isHorizontal=false;
        }
        else if(!isHorizontal){
            isHorizontal=true;
        }
        
        Geometry.rotateAround( this.lineSegment, this.pivotPoint, this.angle);    
        
    }
    
    /**
     * Returns the position of the pivot
     */
    @Override
    public Vect getPosition() {
       return this.pivotPoint;
    }

    /**
     * Defines the action that is to be committed upon collision
     */
    @Override
    public void collision(Ball ball) {
        this.hitBall(ball);
        this.trigger(); 
    }
    
    
    /**
     * Returns a string representation of flipper
     * 
     */
    @Override
    public String toString(int width, int height) {
        int yPos = (int) this.position.y(); 
        int xPos = (int) this.position.x(); 

        char [][] wallArray = Gadget.getArray(height,width); 
        
        if(isHorizontal){
            wallArray[yPos][xPos] = '-';
            wallArray[yPos][xPos+1] = '-';
        }
        else if(!isHorizontal){
            wallArray[yPos][xPos] = '|';
            wallArray[yPos+1][xPos] ='|';
        }
        String boardToString = "";
        for(int i=0; i<wallArray.length;i++){
            for(int j=0; j< wallArray[0].length;j++){
            boardToString += Character.toString(wallArray[i][j]);
            }
            boardToString += "\n";
        }
        
        return boardToString;

    }
     /**
      * Rotates the gadget 
      */
    @Override
    public void rotateGadget(int degrees) {
      //Uneccesary 
    }

    /**
     * Calculates the time to collision 
     * @param ball
     * @return time to collisio 
     */
    @Override
    public double timeToCollision(Ball ball) {
        double timeToWallCollision = Double.POSITIVE_INFINITY;
        double minimumTime = Geometry.timeUntilRotatingWallCollision(lineSegment, lineSegment.p1().plus(lineSegment.p2()).times(.5),
                6*Math.PI , ball.ballReturnCircle(), ball.getVelocity());
            if(minimumTime < timeToWallCollision){
                timeToWallCollision = minimumTime;
            }
        return timeToWallCollision;
    }
  
    /**
     * Gives object the ability to be triggered by other events 
     */
    @Override
    public void addTriggeredGadget(Gadget triggeredGadget) {
        this.triggeredGadgets.add(triggeredGadget); 
    }

    
    
}
