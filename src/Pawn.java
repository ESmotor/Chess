public class Pawn extends ChessPiece {
//    Реализуйте конструктор, который будет принимать цвет фигуры.
//    Реализуйте метод getColor() так, чтобы он возвращал цвет фигуры.
//    Реализуйте метод canMoveToPosition() так, чтобы пешка не могла выйти за доску и могла ходить только вперед. Помните, что первый ход пешка может сдвинуться на 2 поля вперед, сделать это можно, например, сравнив координаты. То есть, если пешка белая (color.equals("White")) и находится в line == 1, то она может пойти на 2 поля вперед, иначе — нет, аналогично с черными пешками. Также фигура не может сходить в точку, в которой она сейчас находится. Если пешка может пройти от точки (line, column) до точки (toLine, toColumn) по всем правилам (указанным выше), то функция вернет true, иначе — false.
//    Реализуйте метод getSymbol так, чтобы он возвращал символ фигуры, в нашем случае пешка — это P.

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
            } else if (this.getColor().equals("Black") && x == -1 && y == 0) {
                return true;
            }
            return false;
        } else return false;
    }

    @Override
    String getSymbol() {
        return "P";
    }
}
