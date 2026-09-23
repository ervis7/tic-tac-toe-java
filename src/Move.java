public class Move {
    private final int row;
    private final int col;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isCenter() {
        return row == 1 && col == 1;
    }

    public boolean isCorner() {
        return (row == 0 || row == 2) && (col == 0 || col == 2);
    }

    public String toString() {
        return "(" + (row + 1) + ", " + (col + 1) + ")";
    }
}
