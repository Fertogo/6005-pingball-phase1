package phase1;

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
    
    @Override
    public String toString(){ 
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
