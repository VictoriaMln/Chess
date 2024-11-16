public class Horse extends ChessPiece {

    public Horse (String color) {
        super(color);
    }

    @Override
     public String getColor(){
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition (ChessBoard board, int line, int column, int toLine, int toColumn) {

        if (!board.checkPos(line) || !board.checkPos(column) ||   //проверка выхода точек за доску
                !board.checkPos(toLine) || !board.checkPos(toColumn)) {
            return false;
        }

        if (board.board[line][column] != this) { //проверка, что в начальной позиции действительно конь
            return false;
        }

        if (line == toLine && column == toColumn) {   //проверка, что конечная точка хода не совпадает с начальной
            return false;
        }

        int deltaLine = toLine - line;
        int deltaColumn = toColumn - column;

        if ((Math.abs(deltaLine) == 2) && (Math.abs(deltaColumn) == 1)) { //проверка возможности хода коня

            //клетки, через которые будет проходить фигура
            int line1 = line + Integer.signum(deltaLine);
            int line2 = line + 2 * Integer.signum(deltaLine);
            int column1 = column + Integer.signum(deltaColumn);

            //проверка, что на этих клетках нет других фигур
            if ((board.board[line1][column] != null || board.board[line2][column] != null) &&
                    (board.board[line][column1] != null || board.board[line1][column1] != null)) {
                return false;
            }

            //проверка доступности конечной точки
            if (board.board[toLine][toColumn] == null) {
                return true;
            } else if (!board.board[toLine][toColumn].getColor().equals(board.board[line][column].getColor())) {
                return true; //фигура съедает противника
            } else {
                return false;
            }
        }
        if ((Math.abs(deltaLine) == 1) && (Math.abs(deltaColumn) == 2)) {

            //клетки, через которые будет проходить фигура
            int line1 = line + Integer.signum(deltaLine);
            int column1 = column + Integer.signum(deltaColumn);
            int column2 = column + 2 * Integer.signum(deltaColumn);

            //проверка, что на этих клетках нет других фигур
            if ((board.board[line1][column] != null || board.board[line1][column1] != null) &&
                    (board.board[line][column1] != null || board.board[line][column2] != null)) {
                return false;
            }

            //проверка доступности конечной точки
            if (board.board[toLine][toColumn] == null) {
                return true;
            } else if (!board.board[toLine][toColumn].getColor().equals(board.board[line][column].getColor())) {
                return true; //фигура съедает противника
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public String getSymbol(){
        return "H";
    }
}
