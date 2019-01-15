package domain;

public class Queen extends Piece {

    public Queen(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("Q" + Integer.toString(team));
    }

    public void updatePiece(Board b) {
        this.possible_moves.clear();
        boolean compleix = true;
        // Up
        for (int k = this.i - 1; k >= 0 && compleix; --k) {
            compleix = checkPosition(b, k, j);
        }
        compleix = true;

        // Up-Right
        for (int k = 1; compleix; ++k) {
            compleix = checkPosition(b, i-k, j+k);
        }
        compleix = true;

        // Down
        for (int k = this.i + 1; k < 8 && compleix; ++k) {
            compleix = checkPosition(b, k, j);
        }
        compleix = true;

        // Down-Right
        for (int k = 1; compleix; ++k) {
            compleix = checkPosition(b, i+k, j+k);
        }
        compleix = true;

        // Right
        for (int k = this.j + 1; k < 8 && compleix; ++k) {
            compleix = checkPosition(b, i, k);
        }
        compleix = true;

        // Down-Left
        for (int k = 1; compleix; ++k) {
            compleix = checkPosition(b, i+k, j-k);
        }
        compleix = true;

        // Left
        for (int k = this.j - 1; k >= 0 && compleix; --k) {
            compleix = checkPosition(b, i, k);
        }
        compleix = true;

        // Up-Left
        for (int k = 1; compleix; ++k) {
            compleix = checkPosition(b, i-k, j-k);
        }
    }
}