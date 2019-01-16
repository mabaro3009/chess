package domain;

public class Bishop extends Piece {

    public Bishop(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("B" + Integer.toString(team));
    }


    public void updatePiece(Board b) {
        this.possible_moves.clear();
        boolean compleix = true;
        // Up-Right
        for (int k = 1; compleix; ++k) {
            compleix = checkPosition(b, i-k, j+k);
        }
        compleix = true;

        // Down-Right
        for (int k = 1; compleix; ++k) {
            compleix = checkPosition(b, i+k, j+k);
        }
        compleix = true;

        // Down-Left
        for (int k = 1; compleix; ++k) {
            compleix = checkPosition(b, i+k, j-k);
        }
        compleix = true;

        // Up-Left
        for (int k = 1; compleix; ++k) {
            compleix = checkPosition(b, i-k, j-k);
        }

    }

    public void update_first_move(){
        if(first_move){
            first_move = false;
        }
    }

}