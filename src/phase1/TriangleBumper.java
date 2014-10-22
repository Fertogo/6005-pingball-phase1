package phase1;

import physics.Vect;

public class TriangleBumper implements Gadget {
    private Vect position;
    private int orientation = 0;
    public TriangleBumper(Vect position) {
        this.position = position;
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub

    }

    @Override
    public void rotateGadget(int degrees) {
        this.orientation = degrees;
    }

    @Override
    public String toString(int height, int width) {
        Board board = new Board(width, height);
        char [][] wallArray = board.getArray();
        if(this.orientation == 0 || this.orientation == 180){
            wallArray[(int) this.position.y()][(int) this.position.x()] = '/';
        }else{
            wallArray[(int) this.position.y()][(int) this.position.x()] = '\\';
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
