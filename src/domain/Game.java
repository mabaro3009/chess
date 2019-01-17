package domain;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Board b;
    private int turn;
    private int actual_turn;
    private int winner;
    private boolean king1_check;
    private boolean king2_check;

    public Game(){
        b = new Board();
        b.startBoard();
        turn = 1;
        actual_turn = 1;
        winner = 0;
        king1_check = false;
    }

    public void play(){
        while(winner == 0) {
            readBoard();
            System.out.println("Turn: " + Integer.toString(turn));
            if(turn%2 == 0) System.out.println("Player 2 has to move");
            else System.out.println("Player 1 has to move");
            if(king1_check) System.out.println("King 1 is in check");
            if(king2_check) System.out.println("King 2 is in check");
            int[] originPos = getOriginPos();
            int[] destPos = getDestPos(originPos);
            move(originPos[0], originPos[1], destPos[0], destPos[1]);
            ++turn;
            if(actual_turn == 1) actual_turn = 2;
            else actual_turn = 1;
        }
        readBoard();
        System.out.println("Player " + Integer.toString(winner) + " wins!");
    }

    private void updatePieces() {
        b.updatePieces();
    }

    private int[] getOriginPos() {
        int[] originPos = new int[2];
        boolean compleix = false;
        Scanner sca = new Scanner(System.in);
        updatePieces();
        while(!compleix){
            System.out.println("Enter origin postion:");
            originPos[0] = sca.nextInt();
            originPos[1] = sca.nextInt();
            compleix = checkOriginPos(originPos);
        }
        return originPos;
    }

    private boolean checkOriginPos(int[] originPos) {
        if(originPos[0] < 0 || originPos[0] > 7 || originPos[1] < 0 || originPos[1] > 7){
            System.out.println("This position is off-limits");
            return false;
        }
        else if(b.readValue(originPos[0],originPos[1]) != actual_turn){
            System.out.println("This piece is not yours");
            return false;
        }
        else if(!b.checkOriginPos(originPos[0],originPos[1])){
            System.out.println("This piece can't move");
            return false;
        }
        return true;
    }

    private int[] getDestPos(int[] originPos) {
        int[] destPos = new int[2];
        boolean compleix = false;
        Scanner sca = new Scanner(System.in);
        while(!compleix){
            System.out.println("Enter destination postion:");
            destPos[0] = sca.nextInt();
            destPos[1] = sca.nextInt();
            compleix = checkDestPos(destPos, originPos);
        }
        return destPos;
    }

    private boolean checkDestPos(int[] destPos, int[] originPos) {
        if(destPos[0] < 0 || destPos[0] > 7 || destPos[1] < 0 || destPos[1] > 7){
            System.out.println("This position is off-limits");
            return false;
        }
        else if(b.readValue(destPos[0],destPos[1]) == actual_turn){
            System.out.println("You already have a piece here");
            return false;
        }
        else if(!b.checkDestPos(originPos[0], originPos[1], destPos[0],destPos[1])){
            System.out.println("You can't move this piece here");
            return false;
        }
        return true;
    }

    private void readBoard() {
        b.readBoard();
    }

    private void move(int o1, int o2, int d1, int d2) {
        // Check if a king is about to be captured
        check_endgame(d1, d2);
        b.move(o1, o2, d1, d2);
        // Controlling pawns and checking en-passant and promotion rules
        pawns_controller(d1,d2);
        // Controlling rook's movement when castling occurs
        castling_controller(o1, o2, d1, d2);
        // Updating first move
        b.update_frist_move(d1, d2);
        // Update the position each piece can go
        updatePieces();
        // Check if a king is in check after the move
        check_check();
    }

    private void castling_controller(int o1, int o2, int d1, int d2) {
        // if its a king and it has moved 2 tiles horizontally, then it's castling
        if((b.getPiece(d1, d2).equals("K1") || b.getPiece(d1, d2).equals("K2")) &&
                (d2 == o2 + 2 || d2 == o2 -2)){
            // Right movement
            if(d2 > o2) b.move(d1, d2+1, d1, d2-1);

            // Left movement
            else b.move(d1, d2-2, d1, d2+1);
        }
    }

    private void check_check() {
        king1_check = king2_check = false;
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if (b.readValue(i, j) > 0) {
                    boolean aux = b.check_check(i, j);
                    if (aux && b.readValue(i, j) == 1) king2_check = true;
                    else if (aux && b.readValue(i, j) == 2) king1_check = true;
                }
            }
        }
        b.update_check(king1_check, king2_check);
    }

    private void check_endgame(int d1, int d2) {
        if (b.readValue(d1,d2) > 0 && ((actual_turn == 1 && b.getPiece(d1, d2).equals("K2")) || actual_turn == 2 && b.getPiece(d1, d2).equals("K1")))
            winner = actual_turn;
    }

    private void pawns_controller(int d1, int d2) {
        if (b.getPiece(d1, d2).equals("P1") || b.getPiece(d1, d2).equals("P2")){
            b.update_frist_move_en_passant(d1,d2);
            // en-passant
            if(b.readValue(d1, d2) == 1 && b.readValue(d1+1, d2) != 0 && b.getPiece(d1+1,d2).equals("P2") && b.get_en_passant(d1+1,d2))
                b.conquer(d1+1,d2);
            else if(b.readValue(d1, d2) == 2 && b.readValue(d1-1, d2) != 0 && b.getPiece(d1-1,d2).equals("P1") && b.get_en_passant(d1-1,d2))
                b.conquer(d1-1,d2);
            // promotion
            if((b.readValue(d1,d2) == 1 && d1 == 0) || (b.readValue(d1,d2) == 2 && d1 == 7)){
                Scanner sca = new Scanner(System.in);
                int t = b.readValue(d1,d2);
                boolean compleix = false;
                while(!compleix) {
                    System.out.println("Pick one of: Queen, Knight, Rook, Bishop");
                    switch(sca.nextLine()){
                        case "Queen":
                            b.writeValue(d1,d2, new Queen(t, d1, d2));
                            compleix = true;
                            break;
                        case "Knight":
                            b.writeValue(d1,d2, new Knight(t, d1, d2));
                            compleix = true;
                            break;
                        case "Rook":
                            b.writeValue(d1,d2, new Rook(t, d1, d2));
                            compleix = true;
                            break;
                        case "Bishop":
                            b.writeValue(d1,d2, new Bishop(t, d1, d2));
                            compleix = true;
                            break;
                        default:
                            System.out.println("Invalid piece");
                    }

                }

            }
        }
        update_en_passant(d1, d2);
    }

    private void update_en_passant(int d1, int d2) {
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if (b.readValue(i,j) != 0 && (i != d1 || j != d2) && (b.getPiece(i,j).equals("P1") || b.getPiece(i,j).equals("P2"))){
                    b.update_en_passant(i, j);
                }
            }
        }
    }
}
