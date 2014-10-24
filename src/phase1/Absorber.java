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
    private ArrayList<Ball> ballStorage;
    private List<Gadget> triggeredGadgets; 
    
    private void checkRep(){
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
        this.ballStorage= new ArrayList<>();
        //checkRep();
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
        System.out.println("Ball hit absorber!");
       this.storeBall(ball);      
    }

    /**
     * Store ball in one iteration and shoots it back in the next
     * 
     */
    @Override
    public void action() {
        if(ballStorage.size() > 0){
            this.shootBall();
        }
    }
    
    public void createAbsorber(int boardWidth, int boardHeight){
        int yPos = (int)this.position.y(); 
        int xPos = (int)this.position.x(); 

        char [][] wallArray =  Gadget.getArray(boardHeight,boardWidth); 
        for(int i=0; i< this.width;i++){
            wallArray[yPos][xPos+i] = '=';
        }

        for(int i=0; i< this.width;i++){
            wallArray[yPos+this.height][xPos+i] = '=';
            if(this.height < 4 && i> this.width-1-this.ballStorage.size()){
                wallArray[yPos+this.height][xPos+i] = '*';
            }
            if(this.height > 4 && i> this.width-1-this.ballStorage.size()){
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
    
    public void shootBall(){
        Ball storedBall = this.ballStorage.remove(0);
        if(this.height < 4 ){
            storedBall.updateBall(new Vect(position.x()+this.width, 
                    position.y()+this.height), new Vect(0,50));
        }
        if(this.height > 4 ){
            storedBall.updateBall(new Vect(position.x()+this.width, 
                    position.y()+this.height-(int)(this.height/4.0)), new Vect(0,50));
        }
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
    }
    
    @Override
    public Vect getPosition() {
       return this.position;
    }
    @Override
    public Vect getNext(double time) {
       return this.position;
    }
   

    public boolean willCollide(Ball ball) {
        double timeVariable= 1.5;
        if(this.timeToCollision(ball)< timeVariable){
            return true;
        }
        return false; 
    }

    @Override
    public double timeToCollision(Ball ball) {
        int xPos = (int) this.position.x(); 
        int yPos = (int) this.position.y(); 
        
        LineSegment bottomBorder= new LineSegment(xPos, yPos,xPos+this.width, yPos);
        LineSegment topBorder= new LineSegment(xPos, yPos+this.height,xPos+this.width, yPos+this.height);
            
        double topTime =  Geometry.timeUntilWallCollision(topBorder,ball.ballReturnCircle(), ball.getVelocity());
        double bottomTime = Geometry.timeUntilWallCollision(bottomBorder,ball.ballReturnCircle(), ball.getVelocity());
        double firstCollsion;
        double timeToWallCollision = Double.POSITIVE_INFINITY;
        if(topTime < bottomTime){
            firstCollsion = topTime;
        }else {
            firstCollsion = bottomTime;
        }
        if(firstCollsion < timeToWallCollision){
            timeToWallCollision = firstCollsion;
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