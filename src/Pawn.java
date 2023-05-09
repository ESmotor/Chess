public class Pawn extends ChessPiece {
    public Pawn(String color) {
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
            if ((this.getColor().equals("White") || this.getColor().equals("Black")) && (line == 0 || line == 7)) {
                return false;
            }

            int x = toLine - line;
            int y = toColumn - column;

            if (this.getColor().equals("White") && line == 1 && x == 2 && y == 0) {
                return true;
            } else if (this.getColor().equals("White") && x == 1 && y == 0) {
                return true;
            }

            if (this.getColor().equals("Black") && line == 6 && x == -2 && y == 0) {
                return true;
            } else return this.getColor().equals("Black") && x == -1 && y == 0;
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }
}
