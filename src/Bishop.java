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

            if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
                int unitLine = (toLine - line) / Math.abs(toLine - line);
                int unitColumn = (toColumn - column) / Math.abs(toLine - line);
                int nextСellLine = line + unitLine;
                int nextСellColumn = column + unitColumn;
                while (true) {
                    if (nextСellLine == toLine && nextСellColumn == toColumn) {
                        if (!chessBoard.board[nextСellLine][nextСellColumn].getColor().equals("White")) {
                            return true;
                        } else return false;
                    } else if (chessBoard.board[nextСellLine][nextСellColumn] != null) {
                        return false;
                    }
                    nextСellLine += unitLine;
                    nextСellColumn += unitColumn;
                }
            }
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "B";
    }
}
