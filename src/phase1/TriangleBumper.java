package phase1;

public class TriangleBumper implements Gadget {
    private int xPosition;
    private int yPosition;
    private int orientation = 0;
    public TriangleBumper(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
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
            wallArray[this.yPosition][this.xPosition] = '/';
        }else{
            wallArray[this.yPosition][this.xPosition] = '\\';
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
    public void collision(Ball ball) {
        // TODO Auto-generated method stub
        
    }

}
