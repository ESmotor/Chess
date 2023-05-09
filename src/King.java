public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    String getColor() {
        return super.color;
    }

    @Override
    boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chessBoard.checkPos(line) && chessBoard.checkPos(column)
                && chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn)) {
            if (line == toLine && column == toColumn) {
                return false;
            }
            int x = Math.abs(toLine - line);
            int y = Math.abs(toColumn - column);
            return (x == 1 || x == 0) && (y == 1 || y == 0);
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (board.checkPos(line) && board.checkPos(column)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.board[i][j] != null && !(board.board[i][j].getColor().equals(this.getColor()))) {
                        ChessPiece chessPiece = board.board[i][j];
                        if (chessPiece.canMoveToPosition(board, i, j, line, column)) {
                            return true;
                        }

                    }


                }
            }

        }
        return false;
    }

    @Override
    String getSymbol() {
        return "K";
    }
}
