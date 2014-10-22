package phase1;

import physics.Vect;

public class SquareBumper implements Gadget {
    private Vect position;
    
    public SquareBumper(Vect position) {
        this.position = position;
    }

    @Override
    public void action() {

    }

    @Override
    public void rotateGadget(int degrees) {
       
    }

    @Override
    public String toString(int height, int width) {
        
        Board board = new Board(width, height);
        char [][] wallArray = board.getArray();
        wallArray[(int) this.position.y()][(int) this.position.x()] = '#';
        
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
        return this.position;
    }

    @Override
    public Vect getNext() {
        this.step();
        return this.position;
    }

    @Override
    public void collision(Ball ball) {
        
    }

    @Override
    public void step() {
        
    }

    @Override
    public boolean contains(Vect pos) {
        // TODO Auto-generated method stub
        return false;
    }

}
