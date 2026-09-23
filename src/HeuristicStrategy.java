import java.util.List;

public class HeuristicStrategy implements AiStrategy {
    private static final int[][][] LINES = {
            {{0, 0}, {0, 1}, {0, 2}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{2, 0}, {2, 1}, {2, 2}},
            {{0, 0}, {1, 0}, {2, 0}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{0, 2}, {1, 2}, {2, 2}},
            {{0, 0}, {1, 1}, {2, 2}},
            {{0, 2}, {1, 1}, {2, 0}}
    };

    public Move chooseMove(Board board, char aiMark, char humanMark) {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        List<Move> moves = MoveOrdering.preferredMoves(board);

        for (int i = 0; i < moves.size(); i++) {
            Move move = moves.get(i);
            int score = scoreMove(board, move, aiMark, humanMark);
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
        return "Heuristic";
    }

    private int scoreMove(Board board, Move move, char aiMark, char humanMark) {
        int score = 0;

        board.placeMove(move, aiMark);
        if (board.isWinner(aiMark)) {
            score += 1000;
        }
        score += evaluateBoard(board, aiMark, humanMark);
        board.clearCell(move);

        board.placeMove(move, humanMark);
        if (board.isWinner(humanMark)) {
            score += 900;
        }
        board.clearCell(move);

        if (move.isCenter()) {
            score += 30;
        } else if (move.isCorner()) {
            score += 15;
        }

        return score;
    }

    private int evaluateBoard(Board board, char aiMark, char humanMark) {
        int score = 0;

        for (int i = 0; i < LINES.length; i++) {
            int aiCount = 0;
            int humanCount = 0;
            int emptyCount = 0;

            for (int j = 0; j < LINES[i].length; j++) {
                int row = LINES[i][j][0];
                int col = LINES[i][j][1];
                char cell = board.getCell(row, col);
                if (cell == aiMark) {
                    aiCount++;
                } else if (cell == humanMark) {
                    humanCount++;
                } else {
                    emptyCount++;
                }
            }

            if (aiCount > 0 && humanCount == 0) {
                score += lineScore(aiCount, emptyCount);
            } else if (humanCount > 0 && aiCount == 0) {
                score -= lineScore(humanCount, emptyCount);
            }
        }

        return score;
    }

    private int lineScore(int markCount, int emptyCount) {
        if (markCount == 3) {
            return 100;
        }
        if (markCount == 2 && emptyCount == 1) {
            return 25;
        }
        if (markCount == 1 && emptyCount == 2) {
            return 5;
        }
        return 0;
    }
}
