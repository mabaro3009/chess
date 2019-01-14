package domain;

public class Rook  extends Piece {

    public Rook(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("R" + Integer.toString(team));
    }

    public void updatePiece(Board b) {

    }
}