package domain;

public class King extends Piece {

    public King(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("K" + Integer.toString(team));
    }

    public void updatePiece(Board b) {
        this.possible_moves.clear();

        // Up
        if (b.readValue(i-1,j) == 0 || b.other_team(team, i-1,j))
            possible_moves.add(new Position(i-1,j));

        // Up-Right
        if (b.readValue(i-1,j+1) == 0 || b.other_team(team, i-1,j+1))
            possible_moves.add(new Position(i-1,j+1));

        // Right
        if (b.readValue(i,j+1) == 0 || b.other_team(team, i,j+1))
            possible_moves.add(new Position(i,j+1));

        // Down-Right
        if (b.readValue(i+1,j+1) == 0 || b.other_team(team, i+1,j+1))
            possible_moves.add(new Position(i+1,j+1));

        // Down
        if (b.readValue(i+1,j) == 0 || b.other_team(team, i+1,j))
            possible_moves.add(new Position(i+1,j));

        // Down-Left
        if (b.readValue(i+1,j-1) == 0 || b.other_team(team, i+1,j-1))
            possible_moves.add(new Position(i+1,j-1));

        // Left
        if (b.readValue(i,j-1) == 0 || b.other_team(team, i,j-1))
            possible_moves.add(new Position(i,j-1));

        // Up-Left
        if (b.readValue(i-1,j-1) == 0 || b.other_team(team, i-1,j-1))
            possible_moves.add(new Position(i-1,j-1));
    }

    public void delete_possible_move(int k) {
        possible_moves.remove(k);
    }
}