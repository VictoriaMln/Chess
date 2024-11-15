public class King extends ChessPiece {

    public King (String color) {
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

        if (board.board[line][column] != this) { //проверяем, что король действительно находится в начальной позиции
            return false;
        }

        if (line == toLine && column == toColumn) { //проверка, что конечная точка хода не совпадает с начальной
            return false;
        }

        int deltaLine = Math.abs(line - toLine);
        int deltaColumn = Math.abs(column - toColumn);

        if (deltaLine <= 1 && deltaColumn <= 1) { //проверка возможности хода короля

            if (board.board[toLine][toColumn] == null || //проверяем, что конечная позиция пустая или занята противником
                    !board.board[toLine][toColumn].getColor().equals(board.board[line][column].getColor())) {

                if (!this.isUnderAttack(board, toLine, toColumn)) { //проверяем, что король не под угрозой атаки в новой позиции
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public String getSymbol () {
        return "K";
    }

    public boolean isUnderAttack (ChessBoard board, int line, int column) {

        for (int i = 0; i < 8; i++) {  //циклы, в которых перебираем все ряды и столбцы
            for (int j = 0; j < 8; j++) {

                if (board.board[i][j] == null) { //если клетка пустая - король в безопасности
                    return false;
                }
                    //проверяем, что в клетке фигура врага и она моет переместится на клетку короля
                if (!board.board[line][column].getColor().equals(board.board[i][j].getColor())) {
                    if (board.board[i][j].canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
