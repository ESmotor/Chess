public class Horse extends ChessPiece {
    public Horse(String color) {
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

            double x = toLine - line;
            double y = toColumn - column;
            if ((Math.sqrt(x) + Math.sqrt(y) == 5)) {
                return true;
            } else return false;
        } else return false;
    }

    @Override
    String getSymbol() {
        return "H";
    }
}
