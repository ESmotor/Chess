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
        // Если начальная и конечная позиции принадлежат доске
        if (chessBoard.checkPos(line) && chessBoard.checkPos(column)
                && chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn)) {
            // Если начальная и конечная позиции совпадают, то нет
            if (line == toLine && column == toColumn) {
                return false;
            }
            // Если в конечной позиции стоит фигура того же цвета, то нет
            if (chessBoard.board[toLine][toColumn] != null &&
                    chessBoard.board[line][column].getColor().equals(chessBoard.board[toLine][toColumn].getColor())) {
                return false;
            }
            //определим вектор для направления движения
            int vectorLine = Math.abs(toLine - line);
            int vectorColumn = Math.abs(toColumn - column);
            return (vectorLine == 1 && vectorColumn == 2) || (vectorLine == 2 && vectorColumn == 1);
        } else return false;
    }

    @Override
    String getSymbol() {
        return "H";
    }
}
