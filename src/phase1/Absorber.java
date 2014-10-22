package phase1;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Rectangle;

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
    }

    
    /**
     * Occurs when a ball hits it
     */
    public void trigger(){
//        if(collisionDetected()){
//            action(this);
//        }
    }
    /**
     * Detects collsions
     * @return true if collision
     */
    private void collision(){
        this.ballsStored+=1;
    }
    
    //*
    
    @Override
    public void rotateGadget(int degrees) {
        //Empty
    }
   
    @Override
    public void collision(Ball ball) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void step() {
       if(ballsStored>0){
           shootBall(); 
       }
        
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
     * Store ball in one iteration and shoots it back in the next
     * 
     */
    @Override
    public void action() {
        // TODO Auto-generated method stub
        
    }
    
    public void createAbsorber(){
        Board board = new Board(boardWidth, boardHeight);
        char [][] wallArray = board.getArray();
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
    public String toString(int boardWidth, int boardHeight) {
        Board board = new Board(boardWidth, boardHeight);
        char [][] wallArray = board.getArray();
        this.createAbsorber();
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
       return this.positionPoint;
    }
    @Override
    public Vect getNext() {
       return positionPoint;
    }
    
    
    @Override
    public boolean contains(Vect pos) {
        return absorberArea.contains(pos.x(), pos.y()); 
    }
    
    
    

}
    
