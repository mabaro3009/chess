package domain;

public class Bishop extends Piece {

    public Bishop(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("B" + Integer.toString(team));
    }


    public void updatePiece(Board b) {

    }
}