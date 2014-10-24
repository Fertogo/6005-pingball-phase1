package phase1;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Point;
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
    private Vect position; 
    private int width;
    private int height;
    private int ballsStored;
    private Rectangle absorberArea; 
    private ArrayList<Ball> ballStorage;
    private List<Gadget> triggeredGadgets; 

    /**
     * 
     * @param Vect: position of absorber
     * @param int: width of the absorber. 
     * @param int: height of the absorber
     */
    public Absorber(Vect position, int width, int height ){ 

        this.position = position; 
        this.width=width;
        this.height=height;
        int x = (int)position.x(); 
        int y = (int)position.y(); 
        this.absorberArea= new Rectangle(x, y, width, height);
        this.ballStorage= new ArrayList<>();
        this.ballsStored=0;
    }
    public void checkRep(){
        if(this.position.x()<1){
            this.position= new Vect(1, this.position.y());
        }
        if(this.position.y()<1){
            this.position= new Vect(this.position.x(), 1);
        }
        if(this.position.x()+this.width>20){
            this.width = (int)(20- this.position.x());
            
        }
        if(this.position.y()+this.height>20){
            this.height = (int)(20 - this.position.y());
        }
    }
    
    /**
     * Occurs when a ball hits it
     */
    public void trigger(){
      this.action();
      this.checkRep();
    }
    /**
     * Detects collsions
     * @return true if collision
     */
    @Override
    public void collision(Ball ball){
        this.checkRep();
       this.storeBall(ball);  
       System.out.print("Collision");
    }
//    @Override
//    public void step() {
//       if(ballsStored>0 && this.ballStorage.size()>0){
//           shootBall(this.ballStorage.remove(0)); 
//       }
//        
//    }
    /**
     * Store ball in one iteration and shoots it back in the next
     * 
     */
    @Override
    public void action() {
 
        
    }
    
    public void createAbsorber(int boardWidth, int boardHeight){
        int yPos = (int)this.position.y(); 
        int xPos = (int)this.position.x(); 

        char [][] wallArray =  Gadget.getArray(boardHeight,boardWidth); 
        for(int i=0; i< this.width;i++){
//            stringBuilder+="=";
            wallArray[yPos][xPos+i] = '=';
        }
//        for(int i=0; i<height;i++){
//            stringBuilder+="\n";    
//        }
        for(int i=0; i< this.width;i++){
//            stringBuilder+="=";
            wallArray[yPos+this.height][xPos+i] = '=';
            if(this.height < 4 && i> this.width-1-this.ballsStored){
                wallArray[yPos+this.height][xPos+i] = '*';
            }
            if(this.height > 4 && i> this.width-1-this.ballsStored){
                wallArray[yPos+this.height-(int)(this.height/4.0)][xPos+i] = '*';
            }
        }
    }
    
    /**
     * return Absorber representation
     */
    @Override
    public String toString( int boardHeight, int boardWidth) {
        
        char [][] wallArray = Gadget.getArray(boardHeight,boardWidth); 
//        this.createAbsorber(boardWidth, boardHeight);
////        for(int j=0; j<boardHeight; j++){
////            for(int i=0; i<boardWidth; i++){
////                wallArray[(int) this.position.y()+ 1 + i + j][(int) this.position.x()+1 + i ] = '=';
////            }
////        }
//        
  
        
        for(int j=0; j<this.height; j++){
            for(int i=0; i<this.width; i++){
                wallArray[(int) this.position.y() + j + 1][(int) this.position.x() + 1 + i ] = '=';
            }
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
    
    public void shootBall(Ball ball){
        if(this.height < 4 ){
            ball.updateBall(new Vect(position.x()+this.width, position.y()+this.height), new Vect(0,50));
            
        }
        if(this.height > 4 ){
            ball.updateBall(new Vect(position.x()+this.width, position.y()+this.height-(int)(this.height/4.0)), new Vect(0,50));
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
            ball.updateBall(new Vect(position.x()+(int)(this.width*3.0/4.0), position.y()+this.height), new Vect(0,0));
            
        }
        if(this.height > 4 ){
            ball.updateBall(new Vect(position.x()+(int)(this.width*3.0/4.0), position.y()+this.height-(int)(this.height/4.0)), new Vect(0,0));
        }
        this.ballsStored++;
    }
    
    @Override
    public Vect getPosition() {
        this.checkRep();
       return this.position;
    }
    @Override
    public Vect getNext(double time) {
       return this.position;
    }
   
    public boolean contains(Vect pos) {
        boolean contains = false;
        this.checkRep();
        if(  (pos.x()>=this.position.x() && pos.x()<=(this.position.x()+this.width))  &&  (pos.y()>=this.position.y() && pos.y()<=(this.position.y()+this.height)) ){
            contains =true;
        }
        return contains;
    }


   
    public boolean willCollide(Ball ball) {
        double timeToWallCollision = Double.POSITIVE_INFINITY;
        if(this.timeToCollision(ball)< timeToWallCollision){
            return true;
        }
        return false; 
    }

    @Override
    public double timeToCollision(Ball ball) {
        int xPos = (int) this.position.x(); 
        int yPos = (int) this.position.x(); 

        LineSegment topBorder= new LineSegment(xPos, yPos,xPos+this.width, yPos);
        LineSegment bottomBorder= new LineSegment(xPos, yPos+this.height,xPos+this.width, yPos+this.height);
        double  topTime   =   Geometry.timeUntilWallCollision(topBorder,ball.ballReturnCircle(), ball.getVelocity());
        double bottomTime =   Geometry.timeUntilWallCollision(bottomBorder,ball.ballReturnCircle(), ball.getVelocity());
        double timeToWallCollision = Double.POSITIVE_INFINITY;
        double fasterOfBottomOrTop = Double.POSITIVE_INFINITY;
        if(topTime<bottomTime){
            fasterOfBottomOrTop= topTime;
        }else if(topTime>bottomTime){
            fasterOfBottomOrTop = bottomTime;
        }
        if(fasterOfBottomOrTop<timeToWallCollision){
            timeToWallCollision=fasterOfBottomOrTop;
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
    
