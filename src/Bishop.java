public class Bishop extends ChessPiece {

    public Bishop (String color) {
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

            //проверяем возможность хода
        if ((Math.abs(line - toLine) == Math.abs(column - toColumn))) {

            int stepColumn = Integer.signum(toColumn - column);
            int stepLine = Integer.signum(toLine - line);

            //проверяем наличие других фигур на пути
            for (int i = line + stepLine, j = column + stepColumn; i != toLine; i += stepLine, j += stepColumn) {
                if (board.board[i][j] != null) {
                    return false;
                }
            }
            //проверяем доступность конечной точки
            if (board.board[toLine][toColumn] != null) {
                if (!board.board[toLine][toColumn].getColor().equals(board.board[line][column].getColor())) {
                    return true; //съедает вражескую фигуру
                } else {
                    return false; //конечная точка занята союзной фигурой
                }
            } else {
                return true; //конечная точка свободна
            }
        }
        return false;
    }

    @Override
    public String getSymbol () {
        return "B";
    }
}



