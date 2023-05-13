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
            // Если конечная точка под атакой то нет
            if (this.isUnderAttack(chessBoard, toLine, toColumn)) {
                return false;
            }


            if (Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1) {
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
            } else return false;
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (board.checkPos(line) && board.checkPos(column)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board.board[i][j] != null && !(this.getColor().equals(board.board[i][j].getColor()))) {
                        if (board.board[i][j].getSymbol().equals("P")) {
                            if (board.board[i][j].getColor().equals("White")) {
                                if (line == i + 1 && (column == j - 1 || column == j + 1)) {
                                    System.out.printf("%s King под шахом от %s%s(%d,%d)!\n",
                                            this.getColor(),board.board[i][j].getSymbol(),
                                            String.valueOf(board.board[i][j].getColor().charAt(0)).toLowerCase(),i,j);
                                    return true;
                                }
                            } else if (board.board[i][j].getColor().equals("Black")) {
                                if (line == i - 1 && (column == j - 1 || column == j + 1)) {
                                    System.out.printf("%s King под шахом от %s%s(%d,%d)!\n",
                                            this.getColor(),board.board[i][j].getSymbol(),
                                            String.valueOf(board.board[i][j].getColor().charAt(0)).toLowerCase(),i,j);
                                    return true;
                                }
                            }
                        } else if (board.board[i][j].getSymbol().equals("K")) {
                            if (Math.abs(line - i) <= 1 && Math.abs(column - j) <= 1) {
                                System.out.printf("%s King под шахом от %s%s(%d,%d)!\n",
                                        this.getColor(),board.board[i][j].getSymbol(),
                                        String.valueOf(board.board[i][j].getColor().charAt(0)).toLowerCase(),i,j);
                                return true;
                            }
                        } else {
                            if (board.board[i][j].canMoveToPosition(board, i, j, line, column)) {
                                System.out.printf("%s King под шахом от %s%s(%d,%d)!\n",
                                        this.getColor(),board.board[i][j].getSymbol(),
                                        String.valueOf(board.board[i][j].getColor().charAt(0)).toLowerCase(),i,j);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }


    @Override
    String getSymbol() {
        return "K";
    }
}
