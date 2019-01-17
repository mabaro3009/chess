package domain;

public class Board {
    private Piece[][] board;
    private King king1;
    private King king2;

    public Board(){
        this.board = new Piece[8][8];
    }

    public Integer readValue(int i, int j){
        if(i < 0 || i > 7 || j < 0 || j > 7) return -1;
        if(board[i][j] == null) return 0;
        return board[i][j].getTeam();
    }

    public boolean other_team(int team, int i, int j){
        if(team == 1) return (readValue(i,j) == 2);

        else return (readValue(i,j) == 1);
    }

    public void writeValue(int i, int j, Piece p){
        board[i][j] = p;
    }

    public void conquer(int i, int j){
        board[i][j] = null;
    }

    public void readBoard(){
        System.out.print("  ");
        for (int i = 0; i < 8; ++i) System.out.print(Integer.toString(i) + "  ");
        System.out.println();
        for (int i = 0; i < 8; ++i) {
            System.out.print(Integer.toString(i) + " ");
            for (int j = 0; j < 8; ++j) {
                if(board[i][j] == null) System.out.print("-- ");
                else System.out.print(board[i][j].getPiece() + ' ');
            }
            System.out.println();
        }
    }

    public void startBoard(){
        board[0][0] = new Rook(2, 0, 0);
        board[0][1] = new Knight(2, 0, 1);
        board[0][2] = new Bishop(2, 0, 2);
        board[0][3] = new Queen(2, 0, 3);
        king2 =  new King(2, 0, 4);
        board[0][4] = king2;
        board[0][5] = new Bishop(2, 0, 5);
        board[0][6] = new Knight(2, 0, 6);
        board[0][7] = new Rook(2, 0, 7);

        board[7][0] = new Rook(1, 7, 0);
        board[7][1] = new Knight(1, 7, 1);
        board[7][2] = new Bishop(1, 7, 2);
        board[7][3] = new Queen(1, 7, 3);
        king1 = new King(1, 7, 4);
        board[7][4] = king1;
        board[7][5] = new Bishop(1, 7, 5);
        board[7][6] = new Knight(1, 7, 6);
        board[7][7] = new Rook(1, 7, 7);

        for (int i = 0; i < 8; ++i){
            board[1][i] = new Pawn(2, 1, i);
            board[6][i] = new Pawn(1, 6, i);
        }
    }

    public String getPiece(int i, int j) {
        int r = readValue(i, j);
        if (r == -1) return "off";
        if (r == 0) return "null";
        return board[i][j].getPiece();
    }

    public void move(int o1, int o2, int d1, int d2) {
        board[d1][d2] = board[o1][o2];
        board[o1][o2] = null;
        board[d1][d2].updatePos(d1,d2);

    }


    public void update_en_passant(int d1, int d2) {
        ((Pawn)board[d1][d2]).update_en_passant();
    }

    public boolean get_en_passant(int i, int j){
        if(i < 0 || i > 7 || j < 0 || j > 7) return false;
        if (board[i][j].getPiece().equals("P1") || board[i][j].getPiece().equals("P2")) return ((Pawn)board[i][j]).get_en_passant();
        return false;
    }

    public boolean checkOriginPos(int i, int j) {
        return board[i][j].checkOriginPos();
    }

    public boolean checkDestPos(int i, int j, int x, int y) {
        return board[i][j].checkDestPos(x, y);
    }

    public void updatePieces() {
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if(board[i][j] != null) board[i][j].updatePiece(this);
            }
        }
    }

    public void update_frist_move(int d1, int d2) {
        board[d1][d2].update_first_move();
    }


    public boolean check_check(int i, int j) {
        if (readValue(i,j) == 1)
            return board[i][j].check_check(king2.get_i(), king2.get_j());
        else
            return board[i][j].check_check(king1.get_i(), king1.get_j());
    }

    public Piece savePiece(int o1, int o2) {
        return board[o1][o2];
    }

    public void update_frist_move_en_passant(int d1, int d2) {
        ((Pawn)board[d1][d2]).update_first_move_en_passant();
    }

    public void update_check(boolean king1_check, boolean king2_check) {
        king1.updtate_check(king1_check);
        king2.updtate_check(king2_check);
    }

    public Piece get_instace(int i, int j){
        return board[i][j];
    }
}
