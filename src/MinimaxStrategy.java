import java.util.List;

public class MinimaxStrategy implements AiStrategy {
    public Move chooseMove(Board board, char aiMark, char humanMark) {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        List<Move> moves = MoveOrdering.preferredMoves(board);

        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            board.placeMove(move, aiMark);
            int score = minimax(board, aiMark, humanMark, false, 1);
            board.clearCell(move);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }

        if (bestMove == null) {
            throw new IllegalStateException("Δεν υπάρχουν διαθέσιμες κινήσεις.");
        }
        return bestMove;
    }

    public String getName() {
        return "Minimax";
    }

    private int minimax(Board board, char aiMark, char humanMark, boolean maximizing, int depth) {
        if (board.isWinner(aiMark)) {
            return 10 - depth;
        }
        if (board.isWinner(humanMark)) {
            return depth - 10;
        }
        if (board.isDraw()) {
            return 0;
        }

        List<Move> moves = MoveOrdering.preferredMoves(board);

        if (maximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                Move move = moves.get(i);
                board.placeMove(move, aiMark);
                int score = minimax(board, aiMark, humanMark, false, depth + 1);
                board.clearCell(move);
                if (score > bestScore) {
                    bestScore = score;
                }
            }
            return bestScore;
        }

        int bestScore = Integer.MAX_VALUE;
        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            board.placeMove(move, humanMark);
            int score = minimax(board, aiMark, humanMark, true, depth + 1);
            board.clearCell(move);
            if (score < bestScore) {
                bestScore = score;
            }
        }
        return bestScore;
    }
}
