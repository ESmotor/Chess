

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
            // Если в точку можно пройти данной фигурой
            if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
                //определим единичный вектор для направления движения
                int unitLine, unitColumn;
                if (toLine == line) {
                    unitLine = 0;
                } else unitLine = (toLine - line) > 0 ? 1 : -1;

                if (toColumn == column) {
                    unitColumn = 0;
                } else unitColumn = (toColumn - column) > 0 ? 1 : -1;

                // проверяем клетки по пути движения на возможность перемещения
                int cellLine = line;
                int cellColumn = column;
                do {
                    cellLine += unitLine;
                    cellColumn += unitColumn;
                    if (chessBoard.board[cellLine][cellColumn] != null &&
                            !(cellLine == toLine && cellColumn == toColumn)) {
                        return false;
                    }
                } while (!(cellLine == toLine && cellColumn == toColumn));

                return true;
            }
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "B";
    }
}
