package phase1;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import physics.LineSegment;
import physics.Geometry;
import physics.Vect;
/*
 * String Representation: =
 * Size and Shape: A rectangle kL *mL where k and m are 0<k<=20
 * Orientation: Only one kind
 * Coef of Reflection: None, ball captured
 * Trigger: generated when the ball hits it 
 * Action: shoots out store ball
 * Description: absorber stops ball and holds it. Ball's center is .25L from bottom .25 from the right side,
 * Initial velocity of the ball should be 50L/sec with default gravity and default values for friction
 * if not holding the ball or if previously rejected ball hos not left the absorber, 
 * then the absorber takes no action when triggered.   
 */
public class Absorber implements Gadget {
    private int xPos; 
    private int yPos;
    private int width;
    private int height;
    private int ballsStored;
    private Rectangle absorberArea; 
    private Vect positionPoint; 
    private ArrayList<Ball> ballStorage;
    private List<Gadget> triggeredGadgets; 

    /**
     * 
     * @param x-xPosition
     * @param y-yPosition
     * @param size - General Size of the absorber. 
     */
    public Absorber(int x, int y, int width, int height ){ 
        this.xPos =x;
        this.yPos = y;
        this.width=width;
        this.height=height;
        this.positionPoint= new Vect(this.xPos, this.yPos);;
        this.absorberArea= new Rectangle(x, y, width, height);
        this.ballStorage= new ArrayList<>();
        this.ballsStored=0;
    }
    /**
     * Occurs when a ball hits it
     */
    public void trigger(){
      this.action();
    }
    /**
     * Detects collsions
     * @return true if collision
     */
    @Override
    public void collision(Ball ball){
       this.storeBall(ball);      
    }
    @Override
    public void step() {
       if(ballsStored>0 && this.ballStorage.size()>0){
           shootBall(this.ballStorage.remove(0)); 
       }
        
    }
    /**
     * Store ball in one iteration and shoots it back in the next
     * 
     */
    @Override
    public void action() {
 
        
    }
    
    public void createAbsorber(int boardWidth, int boardHeight){
        char [][] wallArray =  Gadget.getArray(boardHeight,boardWidth); 
        for(int i=0; i< this.width;i++){
//            stringBuilder+="=";
            wallArray[this.yPos][this.xPos+i] = '=';
        }
//        for(int i=0; i<height;i++){
//            stringBuilder+="\n";    
//        }
        for(int i=0; i< this.width;i++){
//            stringBuilder+="=";
            wallArray[this.yPos+this.height][this.xPos+i] = '=';
            if(this.height < 4 && i> this.width-1-this.ballsStored){
                wallArray[this.yPos+this.height][this.xPos+i] = '*';
            }
            if(this.height > 4 && i> this.width-1-this.ballsStored){
                wallArray[this.yPos+this.height-(int)(this.height/4.0)][this.xPos+i] = '*';
            }
        }
    }
    
    /**
     * return Absorber representation
     */
    @Override
    public String toString( int boardHeight, int boardWidth) {
        
        char [][] wallArray = Gadget.getArray(boardHeight,boardWidth); 
        this.createAbsorber(boardWidth, boardHeight);
        String boardToString = "";
        for(int i=0; i<wallArray.length;i++){
            for(int j=0; j< wallArray[0].length;j++){
            boardToString += Character.toString(wallArray[i][j]);
            }
            boardToString += "\n";
        }
        
        return boardToString;
      
    }
    
    public void shootBall(Ball ball){
        if(this.height < 4 ){
            ball.updateBall(new Vect(positionPoint.x()+this.width, positionPoint.y()+this.height), new Vect(0,50));
            
        }
        if(this.height > 4 ){
            ball.updateBall(new Vect(positionPoint.x()+this.width, positionPoint.y()+this.height-(int)(this.height/4.0)), new Vect(0,50));
        }
        this.ballsStored--;
    }
    /**
     * 
     * @param ball that is to be Stored 
     */
    public void storeBall(Ball ball){
        if(this.ballStorage.size()>0)this.ballStorage.add(ball);
        if(this.height < 4 ){
            ball.updateBall(new Vect(positionPoint.x()+this.width, positionPoint.y()+this.height), new Vect(0,0));
            
        }
        if(this.height > 4 ){
            ball.updateBall(new Vect(positionPoint.x()+this.width, positionPoint.y()+this.height-(int)(this.height/4.0)), new Vect(0,0));
        }
        this.ballsStored++;
    }
    
    @Override
    public Vect getPosition() {
       return this.positionPoint;
    }
    @Override
    public Vect getNext(double time) {
       return positionPoint;
    }

    @Override
    public double timeToCollision(Ball ball) {
        LineSegment topBorder= new LineSegment(this.xPos, this.yPos,this.xPos+this.width, this.yPos);
        LineSegment bottomBorder= new LineSegment(this.xPos, this.yPos,this.xPos+this.width, this.yPos);
        double  topTime   =   Geometry.timeUntilWallCollision(topBorder,ball.ballReturnCircle(), ball.getVelocity());
        double bottomTime =   Geometry.timeUntilWallCollision(bottomBorder,ball.ballReturnCircle(), ball.getVelocity());
        double timeToWallCollision = Double.POSITIVE_INFINITY;
        if(topTime<bottomTime){
            timeToWallCollision= topTime;
        }else if(topTime>bottomTime){
            timeToWallCollision = bottomTime;
        }
        return timeToWallCollision;
        
        
    }
    
    @Override
    public void rotateGadget(int degrees) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void addTriggeredGadget(Gadget triggeredGadget) {
        this.triggeredGadgets.add(triggeredGadget); 
    }
    
    
    

}
    
