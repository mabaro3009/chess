package domain;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int team, int i, int j){
        super(team, i, j);
    }

    public String getPiece(){
        return ("H" + Integer.toString(team));
    }


    public void updatePiece(Board b) {
        possible_moves.clear();
        if(b.readValue(i-2,j+1) == 0 || b.other_team(team, i-2,j+1))
            possible_moves.add(new Position(i-2,j+1));
        if(b.readValue(i-1,j+2) == 0 || b.other_team(team, i-1,j+2))
            possible_moves.add(new Position(i-1,j+2));
        if(b.readValue(i+1,j+2) == 0 || b.other_team(team, i+1,j+2))
            possible_moves.add(new Position(i+1,j+2));
        if(b.readValue(i+2,j+1) == 0 || b.other_team(team, i+2,j+1))
            possible_moves.add(new Position(i+2,j+1));
        if(b.readValue(i+2,j-1) == 0 || b.other_team(team, i+2,j-1))
            possible_moves.add(new Position(i+2,j-1));
        if(b.readValue(i+1,j-2) == 0 || b.other_team(team, i+1,j-2))
            possible_moves.add(new Position(i+1,j-2));
        if(b.readValue(i-1,j-2) == 0 || b.other_team(team, i-1,j-2))
            possible_moves.add(new Position(i-1,j-2));
        if(b.readValue(i-2,j-1) == 0 || b.other_team(team, i-2,j-1))
            possible_moves.add(new Position(i-2,j-1));
    }

    public void update_first_move(){
        if(first_move){
            first_move = false;
        }
    }
}