public abstract class ChessPiece {
    private String color;
    private boolean check = true;

    void setCheck (boolean check) {
        this.check = check;
    }

    boolean getCheck () {
        return check;
    }

    public ChessPiece (String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }

    public abstract boolean canMoveToPosition (ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol ();

}
