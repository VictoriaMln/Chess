public class Pawn extends ChessPiece {

    public Pawn (String color) {
        super(color);
    }

    @Override
    public String getColor(){
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition (ChessBoard board, int line, int column, int toLine, int toColumn) {

        if (!board.checkPos(line) || !board.checkPos(column) ||   //проверяем, что позиции внутри доски
                !board.checkPos(toLine) || !board.checkPos(toColumn)) {
            return false;
        }

        if (board.board[line][column] != this) { //проверка, что в начальной позиции действительно есть фигура
            return false;
        }

        if (line == toLine && column == toColumn) { //проверка, что конечная точка хода не совпадает с начальной
            return false;
        }

        int direction = board.board[line][column].getColor().equals("White") ? 1 : -1; //направление движения фигуры

        //первый ход на два шага
        if ((board.board[line][column].getColor().equals("White") && line == 1 && toLine == 3 && column == toColumn) ||
                (board.board[line][column].getColor().equals("Black") && line == 6 && toLine == 4 && column == toColumn)) {

            if ((board.board[line + direction][column] == null) && (board.board[toLine][toColumn] == null)){
                return true; //путь свободен
            } else {
                return false; //на пути другие фигуры, ход невозможен
            }
        }

        //одношаговый ход
        if (toLine - line == direction && column == toColumn) {
            if (board.board[toLine][toColumn] == null) {
                return true; //поле свободно, можно ходить
            } else {
                return false; //поле занято. ход невозможен
            }
        }

        //проверяем возможность атаковать по диагонали
        if (toLine - line == direction && Math.abs(toColumn - column) == 1) {
            if (board.board[toLine][toColumn] != null &&
                    !board.board[toLine][toColumn].getColor().equals(board.board[line][column].getColor())) {
                return true; //можно съесть фигуру противника
            } else {
                return false; //некого атаковать
            }
        }
        return false;
    }


    @Override
    public String getSymbol(){
        return "P";
    }
}
