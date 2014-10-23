package phase1;

import java.awt.Rectangle;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.Vect;

public class CircleBumper implements Gadget {
    private Vect position;
    private Circle circle;
    private Rectangle gadgetArea; 
    private List<Gadget> triggeredGadgets; 

    

    public CircleBumper(Vect position) {
        int diameter = 1; 
        this.circle = new Circle(position.x(), position.y(), diameter/2); 
        int posX = (int)(position.x());
        int posY = (int)(position.y()); 
        this.position = new Vect(posX, posY); 
        this.gadgetArea = new Rectangle(posX, posY, 1, 1);

    }

    @Override
    public void action() {
    }

    @Override
    public void rotateGadget(int degrees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(int height, int width) {
        
        char [][] wallArray = Gadget.getArray(height,width); 
        wallArray[(int) this.position.y()+1][(int) this.position.x()+1] = 'O'; //Add 1 to account for walls
        
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
    public void collision(Ball ball) {
        System.out.println("Collision with a CircleBumper"); 
        Vect newBallVelocity = Geometry.reflectCircle(circle.getCenter(), ball.getPosition(), ball.getVelocity());
        ball.updateBall(ball.getPosition(), newBallVelocity);
    }

    @Override
    public Vect getPosition() {
        return this.position;
    }



    @Override
    public void step() {
    }


    @Override
    public Vect getNext(double time) {
        return null;
    }

    @Override
    public double timeToCollision(Ball ball) {
        double timeToWallCollision = Double.POSITIVE_INFINITY;
        double minimumTime = Geometry.timeUntilCircleCollision(circle, ball.ballReturnCircle(), ball.getVelocity());
            if(minimumTime < timeToWallCollision){
                timeToWallCollision = minimumTime;
            }
        return timeToWallCollision;
    }

    @Override
    public void addTriggeredGadget(Gadget triggeredGadget) {
        this.triggeredGadgets.add(triggeredGadget); 
    }

    @Override
    public void trigger() {
        //Trigger Triggered Gadgets
        for (Gadget gadget : this.triggeredGadgets){ 
            gadget.action(); 
        }  
    }

}
