public class Rook extends ChessPiece {

    public Rook(String color) {
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
            return x == 0 || y == 0;
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "R";
    }
}
