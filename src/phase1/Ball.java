package phase1;



import physics.*;

public class Ball implements Gadget {
    
    private Vect position;
    private Vect velocity;
    
    public void checkRep(){
      assert(position.x()>0);
      assert(position.y()>0);
      assert(position.x()<21);
      assert(position.y()<21);
    }
    /**
     * Ball Contructor 
     * @param position: position of ball
     * @param velocity: velocity of ball
     */
    public Ball(Vect position, Vect velocity) {
        this.position = new Vect(position.x(), position.y());//Add 1 to account for walls
        this.velocity = velocity;

    }
    /**
     * Adds Stationary ball
     * @param position- position of ball
     */
    public Ball(Vect position){ 
        this.position = new Vect(position.x(), position.y());//Add 1 to account for walls
        this.velocity = new Vect(0,0); 
        checkRep();
    }
    /*Unecessary in ball
     * (non-Javadoc)
     * @see phase1.Gadget#action()
     */
    @Override
    public void action() {
        //Empty
    }
    /**
     * Returns a circle representation of ball 
     * @return circle
     */
    public Circle ballReturnCircle(){
        return new Circle(position, .25);
    }
    /**
     * Unecessary in ball
     */
    @Override
    public void rotateGadget(int degrees) {
        //Empty
    }
    
    /**
     * 
     * @param width of board
     * @param height of board
     * @return string - representation of ball
     */
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
    /**
     * Method representing what happens in a collision
     * @param Ball
     * 
     */
    @Override
    public void collision(Ball ball) {
        Vect center = new Vect(.5, .5);
        Vect centerBall1 = ball.getPosition().plus(center);
        Vect centerBall2 = this.getPosition().plus(center);
        int massBall1 = 1, massBall2 = 1;
        Geometry.VectPair ballCollision = Geometry.reflectBalls(centerBall1, massBall1 , ball.getVelocity()
                ,centerBall2 , massBall2, this.getVelocity());
        ball.updateBall(ball.getPosition(), ballCollision.v1);
        checkRep();
    }
    /**
     * Updates Ball Properties 
     * @param position
     * @param velocity
     */
    public void updateBall(Vect position, Vect velocity) {
        this.position = new Vect(position.x(), position.y());//Add 1 to account for walls
        this.velocity = velocity;
        checkRep();
    }
    /**
     * Updates position
     * @param position
     */
    public void updatePosition(Vect position){
        this.position = position;
        checkRep();
    }
    /**
     * Updates Velocity
     * @param velocity
     */
    public void updateVelocity(Vect velocity) {
        this.velocity = velocity;
        checkRep();
    }
    
    /**
     * Gets the position of the ball
     * @return position Vect
     */
    public Vect getPosition(){
        checkRep();
        return this.position;
      

    }
    
    /**
     * gets the Velocity of the ball
     * @return Vect velocity 
     */
    public Vect getVelocity(){
        checkRep();
        return this.velocity;

    }
    /**
     * Gets the next position of the ball
     * @param time
     * @return next position
     */
    public Vect getNext(double time) {
        checkRep();
        return this.getPosition().plus(this.getVelocity().times(time));

    }
 
    /**
     * Returns time to collision
     * @param Ball
     * @return timeTill collides with other objects
     */
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
    /**
     * Adds a trigger
     * @param other gadget that serves as a trigger
     */
    @Override
    public void addTriggeredGadget(Gadget triggeredGadget) {
        // TODO Auto-generated method stub
        
    }


    /**
     * Trigger
     */
    @Override
    public void trigger() {
        // TODO Auto-generated method stub
        
    }
   
}
