import java.util.ArrayList;
import java.util.List;

public final class MoveOrdering {
    private MoveOrdering() {
    }

    public static List<Move> preferredMoves(Board board) {
        int[][] order = {
                {1, 1},
                {0, 0}, {0, 2}, {2, 0}, {2, 2},
                {0, 1}, {1, 0}, {1, 2}, {2, 1}
        };

        List<Move> moves = new ArrayList<Move>();
        for (int i = 0; i < order.length; i++) {
            int row = order[i][0];
            int col = order[i][1];
            if (board.isCellEmpty(row, col)) {
                moves.add(new Move(row, col));
            }
        }
        return moves;
    }
}
