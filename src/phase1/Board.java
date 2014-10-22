package phase1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private List<Gadget> gadgets = new ArrayList<Gadget>(); 
    private int height; 
    private int width; 

    private void checkRep(){ 
        
        for (Gadget gadget : gadgets){ 
            //No two gadgets have same x and y 
            Set<String> positions = new HashSet<String>(); 
            String position = "("+gadget.getX() +","+gadget.getY() + ")"; 
            
            assert(!positions.contains(position)); 
            positions.add(position);
            
            //Gadgets are not outside board.             
            assert (gadget.getX() <= width); 
            assert(gadget.getY() <= height); 
        }

    }
    public Board(int width, int height){ 
        this.height= height;
        this.width = width;
    }
    
    /**
     * Step every gadget in the board. 
     */
    public void step(){ 
        
    }
    
    public char [][] getArray(){
        char [][] wallArray = new char[height][width];

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
        return wallArray;
    }

    public void addGadget(Gadget gadget){ 
        //Add gadget to board. 
        this.gadgets.add(gadget); 
        checkRep();
    }

    /**
     * 
     * @param x - x position in board
     * @param y - y position in board
     * @return - A char representation of the topmost character when layers are merged. 
     */
    private char mergeBoard(int x, int y){ 
        List<String> layers = new ArrayList<String>(); 
        for (Gadget gadget : this.gadgets){ 
            layers.add(gadget.toString(this.width,this.height)); 
             }

        for (String layer : layers){ 
            String[] lines = layer.split("\n"); 
            if (lines[y].charAt(x) == ' '){ 
                continue; 
            }
            else return lines[y].charAt(x); 
        }
        return ' '; 
    }

    @Override
    public String toString(){

        //Merge Layers
        StringBuilder board = new StringBuilder(""); 
        for (int y=0; y < height; y++){ 
            for (int x=0; x<width; x++){        
                //Current position = x,y 
                board.append(mergeBoard(x,y)); 
            }
            board.append("\n"); 
        }
        return board.toString(); 
        
    }
}
