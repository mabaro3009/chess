package domain;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean en_passant;

    public Pawn(int team, int i, int j){
        super(team, i, j);
        this.en_passant = false;
    }

    public String getPiece(){
        return ("P" + Integer.toString(team));
    }


    public void updatePiece(Board b) {
        possible_moves.clear();
        if(team == 1){
            if(first_move){
                if(b.readValue(i-1,j) == 0 && b.readValue(i-2,j) == 0) possible_moves.add(new Position(i-2,j));
            }
            if(b.readValue(i-1,j) == 0) possible_moves.add(new Position(i-1,j));
            if(b.readValue(i-1,j-1) == 2 || (b.readValue(i-1,j-1) == 0 && b.readValue(i,j-1) == 2 && b.get_en_passant(i, j-1)))
                possible_moves.add(new Position(i-1,j-1));
            if(b.readValue(i-1,j+1) == 2 || (b.readValue(i-1,j+1) == 0 && b.readValue(i,j+1) == 2 && b.get_en_passant(i, j+1)))
                possible_moves.add(new Position(i-1,j+1));
        }
        else{
            if(first_move){
                if(b.readValue(i+1,j) == 0 && b.readValue(i+2,j) == 0) possible_moves.add(new Position(i+2,j));
            }
            if(b.readValue(i+1,j) == 0) possible_moves.add(new Position(i+1,j));
            if(b.readValue(i+1,j-1) == 1 || (b.readValue(i+1,j-1) == 0 && b.readValue(i,j-1) == 1 && b.get_en_passant(i, j-1)))
                possible_moves.add(new Position(i+1,j-1));
            if(b.readValue(i+1,j+1) == 1 || (b.readValue(i+1,j+1) == 0 && b.readValue(i,j+1) == 1 && b.get_en_passant(i, j+1)))
                possible_moves.add(new Position(i+1,j+1));
        }
    }

    public void update_first_move_en_passant(){
        if(first_move){
            en_passant = true;
        }
    }

    public void update_en_passant(){
        if(en_passant){
            en_passant = false;
        }

    }

    public boolean get_en_passant(){
        return this.en_passant;
    }
}
