package domain;

import java.util.ArrayList;

public abstract class Piece {
    protected int team;
    protected int i;
    protected int j;
    protected ArrayList<Position> possible_moves;
    protected boolean first_move;

    public Piece(int team, int i, int j){
        this.team = team;
        this.i = i;
        this.j = j;
        this.possible_moves = new ArrayList<>();
        this.first_move = true;
    }

    public int getTeam() {
        return team;
    }

    abstract String getPiece();

    public void updatePos(int d1, int d2) {
        i = d1;
        j = d2;
    }

    public boolean checkOriginPos(){
        System.out.println(this.possible_moves.size());
        return !this.possible_moves.isEmpty();
    }

    public abstract void updatePiece(Board board);

    public boolean checkDestPos(int x, int y) {
        for (Position p: this.possible_moves){
            if (x == p.getX() && y == p.getY()) return true;
        }
        return false;
    }

    protected boolean checkPosition(Board b, int i, int j) {
        if (b.readValue(i,j) == -1) return false;
        if (b.readValue(i, j) == 0) {
            possible_moves.add(new Position(i, j));
            return true;
        }
        if (!b.other_team(team,i,j)) return false;
        possible_moves.add(new Position(i, j));
        return false;

    }

    public int get_i(){
        return i;
    }

    public int get_j(){
        return j;
    }

    public boolean check_check(int i, int j) {
        for (Position move : possible_moves){
            if(i == move.getX() && j == move.getY()) return true;
        }
        return false;
    }

    public void update_first_move(){
        if(first_move){
            first_move = false;
        }
    }

    public boolean get_first_move(){
        return first_move;
    }
}
