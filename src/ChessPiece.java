abstract public class ChessPiece {
    //    строковая переменная color — цвет;
//    логическая переменная check, по умолчанию true, она понадобится нам сильно позже;
//    конструктор, принимающий в себя строковую переменную color.
//    абстрактный метод getColor(), возвращающий строку — должен вернуть цвет фигуры;
//    абстрактный метод canMoveToPosition(), возвращающий логическое (boolean) значение и паринимающий в себя параметры ChessBoard chessBoard, int line, int column, int toLine, int toColumn;
//    абстрактный метод getSymbol(), возвращающий строку — тип фигуры.
    String color;
    boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    abstract String getColor();

    abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    abstract String getSymbol();
}
