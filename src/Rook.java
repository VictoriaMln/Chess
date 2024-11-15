public class Rook extends ChessPiece {

    public Rook (String color) {
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

        if (line != toLine && column != toColumn){ //проверка возможности хода для ладьи
            return false;
        }
            //проверка наличия фигур на пути
        if (line == toLine) { //если ход по горизонтали
            int step = Integer.signum(toColumn - column);
            for (int i = column + step; i != toColumn; i += step) {
                if (board.board[line][i] != null) {
                    return false;
                }
            }
        } else { //если ход по вертикали
           int step = Integer.signum(toLine - line);
           for (int i = line + step; i != toLine; i += step) {
               if (board.board[i][column] != null) {
                   return false;
               }
           }
        }
        //проверка доступности конечной точки
        if (board.board[toLine][toColumn] == null) {
            return true;

        } else {
            if (!board.board[toLine][toColumn].getColor().equals(board.board[line][column].getColor())) {
                return true; //фигура съедает противника

            } else {
                return false; //в ячейке стоит союзник, ход невозможен
            }
        }


    }

    @Override
    public String getSymbol () {
        return "R";
    }
}