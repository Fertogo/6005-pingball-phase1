package phase1;
import physics.*;

public class OuterWalls implements Gadget {
    private LineSegment leftWall;
    private LineSegment rightWall;
    private LineSegment topWall;
    private LineSegment bottomWall;
    private int height; 
    private int width; 
    
    private void checkRep(){
        //TODO: Check that all of the walls are connected
    }
    
    public OuterWalls(int width, int height){ 
        leftWall = new LineSegment(0,0,0,height-1); 
        rightWall = new LineSegment(width-1,0,width-1,height-1); 
        topWall = new LineSegment(0,0,width-1,0); 
        bottomWall = new LineSegment(0,height-1, width-1,height-1);
        this.height = height; 
        this.width = width; 
        checkRep(); 
    }

    public int getHeight(){ 
        return this.height; 
    }
    
    public int getWidth(){ 
        return this.width; 
    }
    
    @Override
    public void action(Gadget outerWalls) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString(Gadget OuterWalls) {
        int height = ((phase1.OuterWalls) OuterWalls).getHeight(); 
        int width = ((phase1.OuterWalls) OuterWalls).getWidth(); 
        char [][] wallArray; 
        wallArray = new char[height][width];
      //Populate with space
        for (int x = 0; x<width; x++){ 
            for (int y = 0; y<height; y++){ 
                wallArray[x][y] = ' '; 
            }
        }
        
        //Draw the walls
        //Top wall
        for (int i=0; i<width; i++){ 
            wallArray[i][0] = '.'; 
        }
        //Bottom wall
        for (int i=0; i<width; i++){ 
            wallArray[i][height-1] = '.';
        }
        //Left wall
        for (int y=0; y<width; y++){ 
           wallArray[0][y] = '.'; 
        }
        //Right wall
        for (int y=0; y<width; y++){ 
           wallArray[width-1][y] = '.'; 
        }
        
        //Convert 2D Array to String
        String board = "";

        for(int i=0; i<wallArray.length;i++){
            for(int j=0; j< wallArray[0].length;j++){
            board += Character.toString(wallArray[i][j]);
            }
            board += "\n";
        }

     return board;
    }
}
