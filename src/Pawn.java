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
            // Проверяем возможность двигать белую пешку на 2 клетки с 1 линии
            if (this.getColor().equals("White") && line == 1) {
                if ((toLine - line) == 2 && (toColumn - column) == 0 &&
                        chessBoard.board[line + 1][column] == null && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
            }
            // Проверяем возможность двигать черную пешку на 2 клетки с 6 линии
            if (this.getColor().equals("Black") && line == 6) {
                if ((toLine - line) == -2 && (toColumn - column) == 0 &&
                        chessBoard.board[line - 1][column] == null && chessBoard.board[toLine][toColumn] == null) {
                    return true;
                }
            }
            // Проверяем возможность двигать белую пешку и рубить по диагонали влево и вправо
            if (this.getColor().equals("White")) {
                if ((toLine - line) == 1 && (toColumn - column) == 0) {
                    return chessBoard.board[toLine][toColumn] == null;

                } else if ((toLine - line) == 1 && Math.abs(toColumn - column) == 1 &&
                        chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getColor().equals("Black")) {
                    return true;
                }
            }
            // Проверяем возможность двигать черную пешку и рубить по диагонали влево и вправо
            if (this.getColor().equals("Black")) {
                if ((toLine - line) == -1 && (toColumn - column) == 0) {
                    return chessBoard.board[toLine][toColumn] == null;

                } else return (toLine - line) == -1 && Math.abs(toColumn - column) == 1 &&
                        chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getColor().equals("White");
            }
        }
        return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }
}
