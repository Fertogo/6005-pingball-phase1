package warmup;
import physics.*; 

public interface Pingball {

    /*
     * Playing Area:
     * playing area must be 20 L wide by 20 L high. That is, 400 square bumpers
     * The upper left corner is (0,0) and the lower right corner is (20,20).
     */
    public static Pingball ball(){ 
        return new Ball("sadf"); 
    }

    /*
     * Gadgets:
     * Every gadget has an (x,y) location, where x and y are integers in [0,19].
     * Every gadget has a width and height, also integers in [0,19]Others are configurable
     * Some gadgets have a coefficient of reflection, this is a multiplier applied to the ball's 
     *      velocity after it bounces off the gadget. Default multiplier is 1.0. 
     * Some gadgets have an orientation - how the gadget is rotated from its default orientation. 
     *      This is given in degrees (clockwise). Default orientation is 0. 
     * Some gadgets have triggers - an event that happens at the gadget. 
     * Some gadgets have actions - A response that a gadget can make to a trigger happening somewhere on the board. 
     */    
}

class Board implements Pingball{ 

     /**
      * Board Constructor  - Creates a 2D Array representing the board. 
      * @param width
      * @param height
      * 
      */
    
    private char[][] boardArray; 
   
    private LineSegment leftWall;
    private LineSegment rightWall;
    private LineSegment topWall;
    private LineSegment bottomWall;
    

    
    public Board(int width, int height){ 
        boardArray = new char[height][width]; //Access with x,y
        
       leftWall = new LineSegment(0,0,0,height-1); 
       rightWall = new LineSegment(width-1,0,width-1,height-1); 
       topWall = new LineSegment(0,0,width-1,0); 
       bottomWall = new LineSegment(0,height-1, width-1,height-1);
        
        //Populate with space
        for (int x = 0; x<width; x++){ 
            for (int y = 0; y<height; y++){ 
                boardArray[x][y] = ' '; 
            }
        }
        
        //Draw the walls
        //Top wall
        for (int i=0; i<width; i++){ 
            boardArray[i][0] = '.'; 
        }
        //Bottom wall
        for (int i=0; i<width; i++){ 
            boardArray[i][height-1] = '.';
        }
        //Left wall
        for (int y=0; y<width; y++){ 
           boardArray[0][y] = '.'; 
        }
        //Right wall
        for (int y=0; y<width; y++){ 
           boardArray[width-1][y] = '.'; 
        }
    }

    /**
     * Returns string representation of board. 
     */
    @Override
    public String toString(){ 

       String board = "";

          for(int i=0; i<boardArray.length;i++){
              for(int j=0; j< boardArray[0].length;j++){
              board += Character.toString(boardArray[i][j]);
              }
              board += "\n";
          }

       return board;
    }

    /**
     * Updates the board with the ball and changes balls fields
     * @param ball Current ball
     * @param x position where the ball wants to go 
     * @param y position where the ball wants to go 
     * Call updateBall at the end with new ball properties. 
     */
    public void updateBoard(Ball ball, int x, int y){ 
        Vect oldVelocity = ball.getVelocity();
        Vect newVelocity = null;
        
        if(boardArray[y][x] == '.'){
            switch (x){
            case 0: newVelocity = Geometry.reflectWall(leftWall, oldVelocity);
                    System.out.println("Hit left wall");
                    break;
            case 19: newVelocity = Geometry.reflectWall(rightWall, oldVelocity);
            System.out.println("Hit right wall");
                    break;
            }
            
            switch(y){
            case 19: newVelocity = Geometry.reflectWall(bottomWall, oldVelocity);
            System.out.println("Hit bottom wall");
                    break;
            case 0: newVelocity = Geometry.reflectWall(topWall, oldVelocity);
            System.out.println("Hit top wall");
                    break;
            }
            
            ball.updateBall(ball.getPostionX(), ball.getPostionY(), newVelocity);
        }else{
            boardArray[y][x] = '*';
            boardArray[ball.getPostionY()][ball.getPostionX()] = ' ';
            ball.updateBall(x, y, ball.getVelocity());
        }
    }


}

class Ball implements Pingball{ 

    private Vect velocity;  

    private int positionX; 
    private int positionY; 




    public Ball(String position){ 
    }
    

    public Ball(int x, int y, Vect velocity){ 
        this.positionX = x;
        this.positionY = y;
        this.velocity = velocity;

    }

    /**
     * Calls update Board with correct parameters. ; 
     */
    public void step(Board board){ 
        double deltaY = velocity.angle().sin();
        double deltaX = velocity.angle().cos();
        System.out.println(Math.round(deltaX));
        System.out.println(Math.round(deltaY));
        board.updateBoard(this, positionX + (int)Math.round(deltaX), positionY + (int)Math.round(deltaY));

    }

    /**
     * Updates fields of the ball
     * @param vel
     * @param posX
     * @param posY
     * @param theta
     */


    public void updateBall( int posX, int posY,Vect velocity){ 
     
        this.velocity = velocity;
        this.positionX = posX; 
        this.positionY = posY;  


    }
    public Vect getVector(){

        return this.velocity;
    }
    public int getPostionX(){
        return positionX;
    }
    
    public int getPostionY(){
        return positionY;
    }


    public Vect getVelocity() {
        return velocity;
    }

   
}
