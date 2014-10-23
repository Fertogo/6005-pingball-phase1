package phase1;

import java.util.ArrayList;
import java.util.Arrays;

import physics.*;

public class Ball implements Gadget {
    
    private Vect position;
    private Vect velocity;
    private double gravity;
    private long time = System.currentTimeMillis();
    private long timeDiff;
    private double mu = .025;
    private double mu2 = .025;
    
    public Ball(Vect position, Vect velocity) {
        this.position = new Vect(position.x(), position.y());//Add 1 to account for walls
        this.velocity = velocity;
        this.gravity = 25; 
    }
    
    public Ball(Vect position, Vect velocity, double gravity) {
        this.position = new Vect(position.x(), position.y());//Add 1 to account for walls
        this.velocity = velocity;
        this.gravity = gravity;
    }
    
    public Ball(Vect position){ 
        this.position = new Vect(position.x(), position.y());//Add 1 to account for walls
        this.velocity = new Vect(0,0); 
    }

    @Override
    public void action() {

    }
    public Circle ballReturnCircle(){
        return new Circle(position, .25);
    }
    @Override
    public void rotateGadget(int degrees) {

    }

    @Override
    public String toString(int width, int height) {
        char [][] wallArray = Gadget.getArray(height,width); 
        if(this.position.y()>19){
            this.position = new Vect(this.position.x(), 19);
        }else if(this.position.y()<0){
            this.position = new Vect(this.position.x(), 0);
        }
        if(this.position.x()>19){
            this.position = new Vect(19,this.position.y());
        }else if(this.position.x()<0){
            this.position = new Vect(0, this.position.y());
        }
        wallArray[(int) Math.round(this.position.y()+1)][(int) Math.round(this.position.x()+1)] = '*'; //Add 1 to account for walls
        
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
        Vect center = new Vect(.5, .5);
        Vect centerBall1 = ball.getPosition().plus(center);
        Vect centerBall2 = this.getPosition().plus(center);
        int massBall1 = 1, massBall2 = 1;
        Geometry.VectPair ballCollision = Geometry.reflectBalls(centerBall1, massBall1 , ball.getVelocity()
                ,centerBall2 , massBall2, this.getVelocity());
        ball.updateBall(ball.getPosition(), ballCollision.v1);
        
    }
    public void updateBall(Vect position, Vect velocity) {
        this.position = new Vect(position.x(), position.y());//Add 1 to account for walls
        this.velocity = velocity;
        
    }
    public void updatePosition(Vect position){
        this.position = position;
    }
    
    public void updateVelocity(Vect velocity) {
        this.velocity = velocity;
    }
    
    public void updateBall(double time){
        this.updatePosition(this.getPosition().plus(this.getVelocity().times(time)));
        this.updateVelocity(this.getVelocity().plus(Vect.Y_HAT.times(time*gravity)));
        double delta = 1 - mu*time - mu2*this.getVelocity().length()*time;
        this.updateVelocity(this.getVelocity().times(delta));
        
    }
    
    public void step(){
        Vect gravity = new Vect(0, this.gravity*(System.currentTimeMillis() - this.time));
        System.out.println("Velocity in step" + velocity);
        System.out.println("Gravity in step" + gravity);
        Vect delta = new Vect(velocity.plus(gravity).angle().cos(), velocity.plus(gravity).angle().sin());
        System.out.println("Delta in step" + delta);
        this.position = this.position.plus(delta);
    }

    public Vect getPosition(){
        return this.position;
    }
    

    public Vect getVelocity(){
        return this.velocity;
    }

    
    @Override
    public Vect getNext(double time) {
        return this.getPosition().plus(this.getVelocity().times(time));

    }

    @Override
    public boolean contains(Vect pos) {
        // TODO Auto-generated method stub
        return false;
    }

    public void setTime(long time){
        this.time = time;
    }

    @Override
    public boolean willColide(Ball ball) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double timeToCollision(Ball ball) {
        double timeToWallCollision = Double.POSITIVE_INFINITY;
        double minimumTime = Geometry.timeUntilBallBallCollision(ball.ballReturnCircle(), ball.getVelocity(), 
                this.ballReturnCircle(), this.getVelocity());
            if(minimumTime < timeToWallCollision){
                timeToWallCollision = minimumTime;
            }
        return timeToWallCollision;
    }
   
}
