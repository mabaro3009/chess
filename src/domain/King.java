package domain;

public class King extends Piece {

    private boolean check;

    public King(int team, int i, int j){
        super(team, i, j);
        this.check = false;
    }

    public String getPiece(){
        return ("K" + Integer.toString(team));
    }

    public void updatePiece(Board b) {
        this.possible_moves.clear();
        // Up
        try_move(i, j, i-1, j, b);

        // Up-Right
        try_move(i, j, i-1, j+1, b);

        // Right
        try_move(i, j, i, j+1, b);

        // Down-Right
        try_move(i, j, i+1, j+1, b);

        // Down
        try_move(i, j, i+1, j, b);

        // Down-Left
        try_move(i, j, i+1, j-1, b);

        // Left
        try_move(i, j, i, j-1, b);

        // Up-Left
        try_move(i, j, i-1, j-1, b);

        // Castling
        if (first_move && !check){
            // Right movement
            if(b.readValue(i, j+1) == 0 && b.readValue(i, j+2) == 0 && b.readValue(i, j+3) == team &&
                    b.get_instace(i, j+3) instanceof Rook && b.get_instace(i, j+3).get_first_move()) {
                try_move(i, j, i, j + 2, b);
            }

            // Left movement
            if(b.readValue(i, j-1) == 0 && b.readValue(i, j-2) == 0  && b.readValue(i, j-3) == 0 &&
                    b.readValue(i, j-4) == team && b.get_instace(i, j-4) instanceof Rook &&
                    b.get_instace(i, j-4).get_first_move())
                try_move(i, j, i, j-2, b);
        }
    }

    // Adds [d1][d2] to possible_moves if king won't be checked at [d1][d2]
    private void try_move(int o1, int o2, int d1, int d2, Board b){
        if (b.readValue(d1,d2) == 0 || b.other_team(team, d1,d2)) {
            boolean other_team_piece = false;
            Piece aux = b.savePiece(o1, o2);
            if(b.other_team(team, d1, d2)) {
                other_team_piece = true;
                aux = b.savePiece(d1, d2);
            }
            b.move(o1, o2, d1, d2);
            boolean compleix = true;
            for(int x = 0; x < 8; ++x){
                for(int y = 0; y < 8; ++y){
                    if(b.other_team(team, x,y) && b.check_check(x,y)) compleix = false;
                }
            }
            if(compleix) possible_moves.add(new Position(d1, d2));
            b.move(d1, d2, o1, o2);
            if(other_team_piece) b.writeValue(d1, d2, aux);
        }
    }

    public void updtate_check(boolean king_check) {
        check = king_check;
    }
}