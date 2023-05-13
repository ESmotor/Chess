public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) { //Если клетка внутри доски
            System.out.println();
            if (!nowPlayer.equals(board[startLine][startColumn].getColor()))
                return false; //если взяли фигугу не того цвета

            // Если возможно сделать ход то
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                ChessPiece chessPiece;
                boolean changePtoQ = false;
                if (this.board[startLine][startColumn].getSymbol().equals("P") &&
                        ((this.board[startLine][startColumn].getColor().equals("White") && endLine == 7) ||
                                (this.board[startLine][startColumn].getColor().equals("Black") && endLine == 0))) {
                    chessPiece = this.board[endLine][endColumn];
                    board[endLine][endColumn] = new Queen(this.board[startLine][startColumn].getColor()); // перемещаем фигуру в конечную клетку (копируем)
                    board[startLine][startColumn] = null; // удаляем предыдущую фигуру
                    changePtoQ = true;
                } else {
                    chessPiece = this.board[endLine][endColumn];
                    board[endLine][endColumn] = board[startLine][startColumn]; // перемещаем фигуру в конечную клетку (копируем)
                    board[startLine][startColumn] = null; // удаляем предыдущую фигуру
                }


                //Проверка местонахождения королей
                King whiteKing = new King("White"); //создаем белого короля
                int whiteKingLine = 0;
                int whiteKingColumn = 0;
                King blackKing = new King("Black");//создаем черного короля
                int blackKingLine = 0;
                int blackKingColumn = 0;

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {

                        if (this.board[i][j] != null && this.board[i][j].getSymbol().equals("K")) {
                            if (this.board[i][j].getColor().equals("White")) { // нашли белого короля сохранили в него нашего
                                whiteKingLine = i;
                                whiteKingColumn = j;
                                this.board[i][j] = whiteKing;
                            } else if (this.board[i][j].getColor().equals("Black")) {// нашли черного короля сохранили в него нашего
                                blackKingLine = i;
                                blackKingColumn = j;
                                this.board[i][j] = blackKing;
                            }
                        }

                    }
                }

                // если игрок пошел и его король остался под шахом, то ход отменяется
                if ((this.nowPlayerColor().equals("White") && whiteKing.isUnderAttack(this, whiteKingLine, whiteKingColumn)) ||
                        (this.nowPlayerColor().equals("Black") && blackKing.isUnderAttack(this, blackKingLine, blackKingColumn))) {
                    board[startLine][startColumn] = board[endLine][endColumn];
                    board[endLine][endColumn] = chessPiece;
                    if (changePtoQ) {
                        this.board[startLine][startColumn] = new Pawn(this.board[startLine][startColumn].getColor());
                    }

                    return false;
                }

                // если игрок пошел и король соперника под шахом
                if (this.nowPlayerColor().equals("White")) {
                    blackKing.isUnderAttack(this, blackKingLine, blackKingColumn);
                } else if (this.nowPlayerColor().equals("Black")) {
                    whiteKing.isUnderAttack(this, whiteKingLine, whiteKingColumn);
                }

                if (board[endLine][endColumn].getSymbol().equals("K") ||  // check position for castling
                        board[endLine][endColumn].getSymbol().equals("R")) {
                    board[endLine][endColumn].check = false;
                }
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                return true;

            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              // never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)
                        && !new King("White").isUnderAttack(this, 0, 3)
                        && !new King("White").isUnderAttack(this, 0, 4)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][2] = new King("White");   // move King
                    board[0][2].check = false;
                    board[0][0] = null;
                    board[0][3] = new Rook("White");   // move Rook
                    board[0][3].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)
                        && !new King("Black").isUnderAttack(this, 7, 3)
                        && !new King("Black").isUnderAttack(this, 7, 4)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][2] = new King("Black");   // move King
                    board[7][2].check = false;
                    board[7][0] = null;
                    board[7][3] = new Rook("Black");   // move Rook
                    board[7][3].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][5] == null && board[0][6] == null) {              // never moved
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 6)
                        && !new King("White").isUnderAttack(this, 0, 5)
                        && !new King("White").isUnderAttack(this, 0, 4)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][6] = new King("White");   // move King
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");   // move Rook
                    board[0][5].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][5] == null && board[7][6] == null) {              // never moved
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)
                        && !new King("Black").isUnderAttack(this, 7, 5)
                        && !new King("Black").isUnderAttack(this, 7, 4)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][6] = new King("Black");   // move King
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");   // move Rook
                    board[7][5].check = false;
                    nowPlayer = "White";  // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }
}
