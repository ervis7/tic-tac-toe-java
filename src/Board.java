import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int SIZE = 3;
    public static final char EMPTY = ' ';

    private final char[][] cells;

    public Board() {
        this.cells = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = EMPTY;
            }
        }
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public boolean isCellEmpty(int row, int col) {
        return isValidPosition(row, col) && cells[row][col] == EMPTY;
    }

    public char getCell(int row, int col) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Ανέγκυρη θέση στο ταμπλό.");
        }
        return cells[row][col];
    }

    public boolean placeMove(Move move, char mark) {
        if (!isCellEmpty(move.getRow(), move.getCol())) {
            return false;
        }
        cells[move.getRow()][move.getCol()] = mark;
        return true;
    }

    public void clearCell(Move move) {
        if (!isValidPosition(move.getRow(), move.getCol())) {
            throw new IllegalArgumentException("Ανέγκυρη θέση στο ταμπλό.");
        }
        cells[move.getRow()][move.getCol()] = EMPTY;
    }

    public List<Move> getAvailableMoves() {
        List<Move> moves = new ArrayList<Move>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (cells[row][col] == EMPTY) {
                    moves.add(new Move(row, col));
                }
            }
        }
        return moves;
    }

    public boolean isWinner(char mark) {
        for (int i = 0; i < SIZE; i++) {
            if (cells[i][0] == mark && cells[i][1] == mark && cells[i][2] == mark) {
                return true;
            }
            if (cells[0][i] == mark && cells[1][i] == mark && cells[2][i] == mark) {
                return true;
            }
        }

        return (cells[0][0] == mark && cells[1][1] == mark && cells[2][2] == mark)
                || (cells[0][2] == mark && cells[1][1] == mark && cells[2][0] == mark);
    }

    public boolean isDraw() {
        return getAvailableMoves().isEmpty() && !isWinner('X') && !isWinner('O');
    }

    public void print() {
        System.out.println();
        System.out.println("    1   2   3");
        for (int row = 0; row < SIZE; row++) {
            System.out.print(" " + (row + 1) + "  ");
            for (int col = 0; col < SIZE; col++) {
                System.out.print(cells[row][col]);
                if (col < SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < SIZE - 1) {
                System.out.println("   ---+---+---");
            }
        }
        System.out.println();
    }
}
