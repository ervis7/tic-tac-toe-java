import java.util.List;

public class RuleBasedStrategy implements AiStrategy {
    public Move chooseMove(Board board, char aiMark, char humanMark) {
        Move winningMove = findWinningMove(board, aiMark);
        if (winningMove != null) {
            return winningMove;
        }

        Move blockingMove = findWinningMove(board, humanMark);
        if (blockingMove != null) {
            return blockingMove;
        }

        if (board.isCellEmpty(1, 1)) {
            return new Move(1, 1);
        }

        List<Move> moves = MoveOrdering.preferredMoves(board);
        if (!moves.isEmpty()) {
            return moves.get(0);
        }

        throw new IllegalStateException("Δεν υπάρχουν διαθέσιμες κινήσεις.");
    }

    public String getName() {
        return "Rule-based";
    }

    private Move findWinningMove(Board board, char mark) {
        List<Move> moves = MoveOrdering.preferredMoves(board);
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            board.placeMove(move, mark);
            boolean wins = board.isWinner(mark);
            board.clearCell(move);
            if (wins) {
                return move;
            }
        }
        return null;
    }
}
