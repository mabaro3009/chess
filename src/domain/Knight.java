package domain;

public class Knight extends Piece {

    public Knight(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("H" + Integer.toString(team));
    }


    public void updatePiece(Board b) {

    }
}