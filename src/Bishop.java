public class Bishop extends ChessPiece {

    public Bishop(String color) {
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
            return Math.abs(toLine - line) == Math.abs(toColumn - column);
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "B";
    }
}
