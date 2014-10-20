package phase1;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Gadget[] gadgets; 
    private int height; 
    private int width; 
    
    private void checkRep(){ 
        //No two gadgets have same x and y 
        //Gadgets are not outside board. 
    }
    public void Board(int width, int height){ 
        //Create outer walls and add to list
        //checkRep()
    }
    
    public void addGadget(Gadget gadget){ 
        //Add gadget to board. 
        //checkRep()
    }
    
    /**
     * 
     * @param x - x position in board
     * @param y - y position in board
     * @return - A char representation of the topmost character when layers are merged. 
     */
    private char mergeBoard( List<String> layers, int x, int y){ 
//        List<String> layers = new ArrayList<String>(); 
//        for (Gadget gadget : this.gadgets){ 
//            layers.add(gadget.toString(this.width,this.height)); 
//        }
        
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
        for (int y=0; y < height-1; y++){ 
            for (int x=0; x<width-1; x++){        
                //Current position = x,y 
                board.append(mergeBoard(x,y)); 
            }
            board.append("\n"); 
        }
        return ""; 
        //Idea 1
               //Make empty array of size (width, height)
               // Add walls (see OuterWalls)
              // For each gadget:
                // Get String representation
                //if absorber?
                // array[gadget.getX(), gadget.getY()] = gadget.toString()
                // 
              // Convert array toString; 
        //Idea 2
                //toString(height,width) of every gadgets returns String representation of that gadget in an empty board. 
                //Call toString of every array, merge returned strings into board. 
    }
}
