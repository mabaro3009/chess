package domain;

public class King extends Piece {

    public King(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("K" + Integer.toString(team));
    }

    public void updatePiece(Board b) {

    }
}