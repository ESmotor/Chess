public class Queen extends ChessPiece {
    public Queen(String color) {
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
            if (Math.abs(toLine - line) == Math.abs(toColumn - column) || line == toLine || column == toColumn) {
                //определим единичный вектор для направления движения
                int unitLine, unitColumn;
                if (toLine == line) {
                    unitLine = 0;
                } else unitLine = (toLine - line) > 0 ? 1 : -1;

                if (toColumn == column) {
                    unitColumn = 0;
                } else unitColumn = (toColumn - column) > 0 ? 1 : -1;

                // проверяем клетки по пути движения на возможность перемещения
                int nextСellLine = line;
                int nextСellColumn = column;
                do {
                    nextСellLine += unitLine;
                    nextСellColumn += unitColumn;
                    if (chessBoard.board[nextСellLine][nextСellColumn] != null &&
                            !(nextСellLine == toLine && nextСellColumn == toColumn)) {
                        return false;
                    }
                } while (!(nextСellLine == toLine && nextСellColumn == toColumn));
                return true;
            }
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "Q";
    }
}
