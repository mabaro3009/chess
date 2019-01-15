package domain;

import java.util.ArrayList;

public class Rook  extends Piece {

    public Rook(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("R" + Integer.toString(team));
    }

    public void updatePiece(Board b) {
        this.possible_moves.clear();
        boolean compleix = true;
        // Up
        for (int k = this.i - 1; k >= 0 && compleix; --k) {
            compleix = checkPosition(b, k, j);
        }
        compleix = true;

        // Down
        for (int k = this.i + 1; k < 8 && compleix; ++k) {
            compleix = checkPosition(b, k, j);
        }
        compleix = true;

        // Right
        for (int k = this.j + 1; k < 8 && compleix; ++k) {
            compleix = checkPosition(b, i, k);
        }
        compleix = true;

        // Left
        for (int k = this.j - 1; k >= 0 && compleix; --k) {
            compleix = checkPosition(b, i, k);
        }

    }
}