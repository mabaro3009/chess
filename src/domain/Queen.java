package domain;

public class Queen extends Piece {

    public Queen(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("Q" + Integer.toString(team));
    }

    public void updatePiece(Board b) {

    }
}